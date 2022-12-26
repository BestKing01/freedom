package com.example.demo;

import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "CalculateFileHashResponse", namespace = "http://example.com/filehash")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculateFileHashResponse {
    @XmlElement(name = "hash", namespace = "http://example.com/filehash")
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
