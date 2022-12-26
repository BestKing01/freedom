package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import java.io.File;

public class FileHashController {
    @FXML
    private TextField hashField;

    @FXML
    private Button calculateButton;

    private File selectedFile;

    @FXML
    private void initialize() {
        calculateButton.setOnAction(event -> calculateFileHash());
    }

    private void calculateFileHash() {
        // Show a FileChooser to let the user select a file
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(calculateButton.getScene().getWindow());
        if (selectedFile == null) {
            return;
        }

        // Call the web service to calculate the hash code of the selected file
        try {
            String hash = callWebService(selectedFile);
            hashField.setText(hash);
        } catch (Exception e) {
            e.printStackTrace();
            hashField.setText("Error: " + e.getMessage());
        }
    }

    private String callWebService(File file) throws Exception {
        // Create a SOAP connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "http://localhost:8080/ws/filehash";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(file), url);

        // Process the SOAP Response
        String hash = processSOAPResponse(soapResponse);

        soapConnection.close();
        return hash;
    }

    private SOAPMessage createSOAPRequest(File file) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://example.com/filehash";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("filehash", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement calculateFileHashRequest = soapBody.addChildElement("CalculateFileHashRequest", "filehash");
        SOAPElement fileElement = calculateFileHashRequest.addChildElement("file", "filehash");
        fileElement.addTextNode(file.getAbsolutePath());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "CalculateFileHashRequest");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
    private String processSOAPResponse(SOAPMessage soapResponse) throws Exception {
        SOAPBody body = soapResponse.getSOAPBody();
        NodeList nodeList = body.getElementsByTagName("hash");
        String hash = nodeList.item(0).getTextContent();
        return hash;
    }
}