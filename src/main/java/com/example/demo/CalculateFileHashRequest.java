package com.example.demo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

@XmlRootElement(name = "CalculateFileHashRequest", namespace = "http://example.com/filehash")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculateFileHashRequest {
    @XmlElement(name = "file", namespace = "http://example.com/filehash")
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
