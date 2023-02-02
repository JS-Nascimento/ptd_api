package br.gov.mapa.ptd.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Translations {
    public static String encode(String plainText) {
        byte[] encodedBytes = Base64.getEncoder().encode(plainText.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
    public static String decode(String encodedText) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }


}
