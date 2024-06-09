package org.zjy.diveintoive.tokenbackup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjy.diveintoive.tokenbackup.request.EncryptDecryptRequest;
import org.zjy.diveintoive.tokenbackup.service.TokenBackupService;

@RestController
@RequestMapping("/token")
@Slf4j
public class TokenBackupController {

    @Autowired
    TokenBackupService tokenBackupService;

    @GetMapping("/account")
    public ResponseEntity<Object> getAccountTokens() {
        try{
            return new ResponseEntity<>(tokenBackupService.readAndDecrypt().stream().sorted(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/wallet/{type}")
    public ResponseEntity<Object> getWalletRecoveryPhrases(@PathVariable("type") String walletType) {
        try{
            return new ResponseEntity<>(tokenBackupService.recoverWalletToken(walletType), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/encrypt")
    public ResponseEntity<Object> encrypt(@RequestBody EncryptDecryptRequest request) {
        try{
            return new ResponseEntity<>(tokenBackupService.encryptDes(request.getPlaintext()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Object> decrypt(@RequestBody EncryptDecryptRequest request) {
        try{
            return new ResponseEntity<>(tokenBackupService.decryptDES(request.getCipher()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validate(@RequestBody EncryptDecryptRequest request) {
        try{
            return new ResponseEntity<>(tokenBackupService.validate(request.getPlaintext(), request.getCipher()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/encrypt-wallet")
    public ResponseEntity<Object> encryptWallet(@RequestBody EncryptDecryptRequest request) {
        try{
            return new ResponseEntity<>(tokenBackupService.encryptWalletToken(request.getPlaintext()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
