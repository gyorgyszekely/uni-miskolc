package hu.miskolc.uni.iit.hydrominder;

import android.util.Log;

import java.nio.charset.Charset;

/**
 * Jelszó string kódolására használt osztály
 */
public class PasswordEncoder {
    private static final String TAG = "PasswordEncoder";

    //MD5 hash-t ad vissza a megadott stringből
    public static String EncodePassword(String password){
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes(Charset.forName("UTF-8")));
            StringBuffer sb = new StringBuffer();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            Log.d(TAG, e.getMessage());
        }
        return null;
    }
}
