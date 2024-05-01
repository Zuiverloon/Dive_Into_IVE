package org.zjy.diveintoive.tokenbackup.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zjy.diveintoive.utils.RootToken;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.zjy.diveintoive.utils.WalletRecoverPhrase.*;

@Component
public class TokenBackupService {

    private String key;

    @Autowired
    private RootToken rootToken;

    private String path = "src/main/resources/tokens.txt";

    @PostConstruct
    public void post() {
        key = rootToken.getRoot();
    }

    public List<String> readAndDecrypt() {
        List<String> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line, items);
            }
            return items;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Process a line in tokens.txt
     *
     * @param line a line (domain + [account] + {cipher})
     */
    public void processLine(String line, List<String> items) {
        StringBuilder sb = new StringBuilder();
        StringBuilder cipherText = new StringBuilder();
        int state = 0;
        for (char c : line.toCharArray()) {
            if (state == 0) {
                if (c == '{') {
                    state = 1;
                } else {
                    sb.append(c);
                }
            } else {
                if (c == '}') {
                    break;
                } else {
                    cipherText.append(c);
                }
            }
        }
        sb.append(decryptDES(cipherText.toString()));
        // System.out.println(sb.toString());
        items.add(sb.toString());

    }

    /**
     * Decipher the ciphertext by key
     *
     * @param cipherText cipher text
     * @return plaintext
     */
    public String decryptDES(String cipherText) {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decode = Base64.decodeBase64(cipherText);
            byte[] decipherByte = cipher.doFinal(decode);
            String decipherText = new String(decipherByte);
            return decipherText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypt the plaintext by key
     *
     * @param plaintext plain text
     * @return cipher text
     */
    public String encryptDes(String plaintext) {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "DES");
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encipherByte = cipher.doFinal(plaintext.getBytes());
            String encode = Base64.encodeBase64String(encipherByte);
            return encode;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public String recoverWalletToken(String type) {
        String capitalType = type.toUpperCase();
        String[] token = null;
        switch (capitalType) {
            case "METAMASK":
                token = metaMaskToken;
                break;
            case "PHANTOM":
                token = phantomToken;
                break;
            case "UNISAT":
                token = unisatToken;
                break;
            case "ERA":
                token = eraToken;
                break;
            case "VENOM":
                token = venomToken;
                break;
            case "ECOCHAINLESS":
                token = ecochainlessToken;
                break;
            case "BITGET":
                token = bitgetToken;
                break;
        }
        if (token == null) {
            System.out.println("Invalid wallet type");
            throw new RuntimeException("Invalid Wallet Type");
        }
        return Arrays.stream(token).map(this::decryptDES).collect(Collectors.joining(" "));
//        for (String s: token){
//            System.out.println(decryptDES(s));
//        }
    }

    public boolean validate(String plaintext, String cipher) {
        return cipher.equals(encryptDes(plaintext)) && plaintext.equals(decryptDES(cipher));
    }

}
