package com.example.demo;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class FileHashService {
    private static final String HASH_ALGORITHM = "SHA-256";

    public String calculateHash(File file) throws IOException, NoSuchAlgorithmException {
        try (InputStream inputStream = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);

            byte[] bytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(bytes)) != -1) {
                digest.update(bytes, 0, bytesRead);
            }
            byte[] hashedBytes = digest.digest();

            return Base64.getEncoder().encodeToString(hashedBytes);
        }
    }
}
