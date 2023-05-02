package com.dwidasa.mpinotp;

import android.content.Context;

import com.dwidasa.otp.aes.AesCrypto;
import com.dwidasa.otp.hotp.HotpGenerator;
import com.dwidasa.otp.key.KeyGenerator;
import com.dwidasa.otp.key.KeyGeneratorException;

public final class Otp {

    private Otp() {
    }

    public static void activateOtp(Context context, String deviceId, String code, String pin) {
        Database.clearData(context);

        generateSecretKey(context, deviceId, code, pin);
    }

    private static void generateSecretKey(Context context, String deviceId, String code, String pin) {
        String secretKey;
        try {
            secretKey = KeyGenerator.generateKey(deviceId, code);
        } catch (KeyGeneratorException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to generate secret key");
        }

        Database.saveSecretKey(context, secretKey, pin);
    }

    public static String generateToken(Context context, String pin) {
        String secretKey = Database.getSecretKey(context, pin);
        Long counter = Database.getCounter(context);

        return HotpGenerator.computeHotpResponseOnly(secretKey, counter);
    }
}
