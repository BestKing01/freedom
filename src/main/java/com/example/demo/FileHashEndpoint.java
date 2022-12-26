package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Endpoint
public class FileHashEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/filehash";

    private FileHashService fileHashService;

    @Autowired
    public FileHashEndpoint(FileHashService fileHashService) {
        this.fileHashService = fileHashService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CalculateFileHashRequest")
    @ResponsePayload
    public CalculateFileHashResponse calculateFileHash(@RequestPayload CalculateFileHashRequest request) throws IOException, NoSuchAlgorithmException {
        File file = request.getFile();
        String hash = fileHashService.calculateHash(file);

        CalculateFileHashResponse response = new CalculateFileHashResponse();
        response.setHash(hash);
        return response;
    }
}