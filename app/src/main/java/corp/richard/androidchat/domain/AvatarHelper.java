package corp.richard.androidchat.domain;

import android.os.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

/**
 * Created by carlos on 10/06/2016.
 */
public class AvatarHelper {
    private final static  String GRAVITY_URL="http://www.gravatar.com/avatar/";
    public  static String getAvatarUrl(String email)
    {
        return GRAVITY_URL+md5(email)+"?s=72";
    }
    private static final String md5(final String s)
    {

        final String MD5 ="MD5";
        try
        {
            // CREARTE MD5 HASH
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // create hex String
            StringBuilder  hexString =new StringBuilder();
            for(byte aMessageDigest:messageDigest)
            {
                String h= Integer.toHexString(0xFF & aMessageDigest);
                while (h.length()<2)
                    h="0"+h;
                hexString.append(h);
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
