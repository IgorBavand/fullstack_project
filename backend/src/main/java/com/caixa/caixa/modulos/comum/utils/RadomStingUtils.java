package com.caixa.caixa.modulos.comum.utils;

import java.util.UUID;

public class RadomStingUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();

    }

    public static String generateRandomString() {
        return generateUUID().replace("-", "");
    }
}
