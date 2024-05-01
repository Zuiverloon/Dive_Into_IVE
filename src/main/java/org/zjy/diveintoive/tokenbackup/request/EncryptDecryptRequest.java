package org.zjy.diveintoive.tokenbackup.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EncryptDecryptRequest {
    @JsonProperty("plaintext")
    String plaintext;
    @JsonProperty("cipher")
    String cipher;
}
