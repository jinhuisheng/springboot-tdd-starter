package com.test.common.utils;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

/**
 * @author huisheng.jin
 * @version 2019/12/3.
 */
public final class IdGenerator {

    private static final Base64.Encoder encoder = Base64.getUrlEncoder();

    public static String newBase64Uuid() {
        UUID uuid = UUID.randomUUID();
        byte[] src = ByteBuffer.wrap(new byte[16])
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits())
                .array();
        return encoder.encodeToString(src).substring(0, 22);
    }

    public static String newId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

