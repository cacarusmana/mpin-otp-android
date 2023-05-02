package com.dwidasa.mpinotp;

import android.content.Context;
import android.content.SharedPreferences;

import com.dwidasa.otp.aes.AesCrypto;

import java.util.Objects;

public class Database {

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.MPIN_OTP_DB_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    public static void saveSecretKey(Context context, String secretKey, String pin) {
        SharedPreferences.Editor editor = getEditor(context);

        String secret1 = secretKey.substring(5);
        String secret2 = secretKey.substring(0, 5);

        editor.putString(Constants.SECRET_1_KEY, AesCrypto.encrypt(getPassword(Constants.SECRET_1_KEY + pin), secret1));
        editor.putString(Constants.SECRET_2_KEY, AesCrypto.encrypt(getPassword(Constants.SECRET_2_KEY + pin), secret2));
        editor.apply();
    }

    public static String getSecretKey(Context context, String pin) {
        try {
            String secret1 = getSharedPreferences(context).getString(Constants.SECRET_1_KEY, null);
            String secret2 = getSharedPreferences(context).getString(Constants.SECRET_2_KEY, null);

            String originalSecret1 = AesCrypto.decrypt(getPassword(Constants.SECRET_1_KEY + pin), secret1);
            String originalSecret2 = AesCrypto.decrypt(getPassword(Constants.SECRET_2_KEY + pin), secret2);

            return originalSecret2 + originalSecret1;
        } catch (Exception e) {
            System.out.println("unable to getSecretKey");
            e.printStackTrace();
        }

        return "01234567890";
    }

    public static void changePIN(Context context, String oldPin, String newPin) {
        SharedPreferences.Editor editor = getEditor(context);
        String secretKey;

        try {
            secretKey = getSecretKey(context, oldPin);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("invalid old pin");
        }

        String secret1 = secretKey.substring(5);
        String secret2 = secretKey.substring(0, 5);

        editor.putString(Constants.SECRET_1_KEY, AesCrypto.encrypt(getPassword(Constants.SECRET_1_KEY + newPin), secret1));
        editor.putString(Constants.SECRET_2_KEY, AesCrypto.encrypt(getPassword(Constants.SECRET_2_KEY + newPin), secret2));
        editor.apply();
    }

    public static Long getCounter(Context context) {
        String encryptedCounter = getSharedPreferences(context).getString(Constants.COUNTER_KEY, null);
        long currentCounter = 0L;
        if (!Objects.isNull(encryptedCounter)) {
            currentCounter = Long.parseLong(AesCrypto.decrypt(getPassword(Constants.COUNTER_KEY), encryptedCounter));
        }

        currentCounter++;

        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(Constants.COUNTER_KEY, AesCrypto.encrypt(getPassword(Constants.COUNTER_KEY), Long.toString(currentCounter)));
        editor.apply();

        return currentCounter;
    }

    public static void clearData(Context context) {
        getSharedPreferences(context).edit().clear().apply();
    }

    private static String getPassword(String key) {
        return String.format("%s:%s:%s", Constants.PASSWORD, key, Constants.PASSWORD);
    }
}
