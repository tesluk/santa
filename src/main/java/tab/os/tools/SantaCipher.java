package tab.os.tools;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by andrey.tesluk on 12.11.2014.
 */
public class SantaCipher {

    private static final int KEY_LENGTH = 1024;
    //    private static final String ALGORITHM = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
//    private static final String ALGORITHM = "RSA/ECB/PKCS1Padding";
//    private static final String ALGORITHM = "RSA/ECB/NoPadding";
//    private static final String ALGORITHM = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final String ALGORITHM = "RSA";

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(KEY_LENGTH);

        return generator.genKeyPair();
    }

    public static PublicKey parsePublicKey(String keyInBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bKey = Base64.decodeBase64(keyInBase64);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bKey);
        return factory.generatePublic(keySpec);
    }

    public static PrivateKey parsePrivateKey(String keyInBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bKey = Base64.decodeBase64(keyInBase64);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bKey);
        return factory.generatePrivate(spec);
    }

    public static String encrypt(String text, String key) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        return encryptRSA(text, parsePublicKey(key));
    }

    public static String decrypt(String text, String key) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        return decryptRSA(text, parsePrivateKey(key));
    }

    public static String encryptRSA(String text, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IOException {
        return encryptPart(text, publicKey);
//        int partSize = 12;
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < text.length(); i += partSize) {
//            String tmp = text.substring(i, (i + partSize) > text.length() ? text.length() : (i + partSize));
//            tmp = encryptPart(tmp, publicKey);
//            builder.append(tmp).append(";");
//        }
//        return builder.toString();
    }

    private static String encryptPart(String string, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        ByteArrayInputStream bis = new ByteArrayInputStream(string.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        CipherOutputStream cos = new CipherOutputStream(bos, cipher);

        int c;
        byte[] buf = new byte[(KEY_LENGTH / 8) - 11];
        while ((c = bis.read(buf)) != -1) {
            cos.write(buf, 0, c);
        }
        bis.close();
        cos.close();

        return Base64.encodeBase64String(bos.toByteArray());
    }

    private static String decryptRSA(String str, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IOException {
        return decryptPart(str, privateKey);
//        String[] parts = str.split(";");
//        StringBuilder builder = new StringBuilder();
//        for (String part : parts) {
//            builder.append(decryptPart(part, privateKey).trim());
//        }
//        return builder.toString();
    }

    private static String decryptPart(String str, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(str));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        CipherInputStream cis = new CipherInputStream(bis, cipher);

        int c;
        byte[] buf = new byte[(KEY_LENGTH / 8) - 11];
        while ((c = cis.read(buf)) != -1) {
            bos.write(buf, 0, c);
        }
        cis.close();
        bos.close();

        return new String(bos.toByteArray());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IOException {
        KeyPair key = generateKeyPair();

        System.out.println(Base64.encodeBase64String(key.getPrivate().getEncoded()));
        System.out.println(Base64.encodeBase64String(key.getPublic().getEncoded()));

        String text = "a123456789012345678901234567890a";

        String enc = encryptRSA(text, key.getPublic());
        System.out.println(enc);
        String dec = decryptRSA(enc, key.getPrivate());
        System.out.println(dec);


        String priv = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJMznOkmrN4ucDCpBjbqQDk9wJY1rQh057X5l9aLtX3ESSq8Kb3z8l7UKbKlmXTX0ElB//SXB9U4UEB4QsX7puHe/NRqmFmgicwcaViDxs5X6GVawG0kM4MC9DQ8KgAsIjZoMqkr4fuJijsAyW+tzvIyOnTBvy7Soq7S5w3V6uc3AgMBAAECgYBpgoJDAAzCiKizyEODEy8LmH61bC3ltN3tKsUx7Bwf1JTZQ9vrorKkVBC7U0WGYbaS+/K535OmumGThF/gRzva7krKnc1myV/ZRYB8Tw02kG2Q2PUl5UEFjzKrx1Y+YrlGPO8ffxmkBPfSWyUSgAMjl4fHPEmcifkzYS6h4ziD6QJBAMR3UmF6kgsNicaptjI/3suDPI8clxf6FzYBeafEhr6XTFOWIm9fKWDpT7BqCktYhO3rtaijbzAacC2v/MuD/+0CQQC/zqS91uRe2T5z7izzXTyWYj4E7AB/sA0ktuWIwtnjTUBLMv2J7RQj9B+hnrfWlkFZdLXdrLpjmxlJGUA2zzczAkEAgTg+DfFRV0W7RdhgBqLtxgt2bGaQlmuekp3bx1XCjl7zC9MmqPv9OkmaaV0FklMRc29iFx4PxqLM/6/N5InYbQJBAIVEb6RTTcM1EDZ+cw74ZiNayRqkW0hcqxUqWtsl/zv2LcjR+eU+KzM76EpkO/lF1svv0njeDXctiWkHuV0bpo8CQCqrY12H2C1dhLuZlR+4HKXF/UXSdsc/kZION/OfcHIuDI4kfmfn+N+FsgXQzJ0hxXIKho+dfMgbah4L1cdHu0w=";
//        String et = "CWeMkKCTAwhXoxLRYsTQ9hMHjd8xrRWzMrUMM53dgWEuwOHdy0VaxY/bx9KSYPHVcrGAPAtBSQx442nfdL4NbV0nwk0shB7aZeHWARZxvkyio9hVzAAjDwwtX+rRVM58o5lgLs0VctmYepfNbW66OxZbPt5obFk2anQ9wXonQRE=";
//        String et = "Be4MF9GgqNBW+d/fm8Ai23eOuOvxbw7JUespqg30Bx2dhVkTFwFXvu6mzdCgPUC9B06eXSGlgX2Wos1tN2e3tYJM/wVvueDRU0ZZz5VoL/2VHv6zqhedgdVZhts7rgatWmhYODGz5s3aQYx0Rk9l48mHhqTpCKuTShmnzQeRomI=";
//        String et = "mobU04OPHJgvKZswFfT2s6K+xYEgupAoZy4SARZkgUnTsUUFFBSRl1BHLIAXDjuf01YYKGjmhcYfE/hheddDGKuBXYguykaeAqpY3hKoQh8q2Tcd+jR7C/LgdF86q5S3Cp5HPT0GIW5awDAuJ8jjSb25lkDbMIEgD2sAmF5+nWE=";
//        String et = "dgS05vaRLkJxiLrbfcisMx9x/bDYiB5bLYrO/lUW4J3uqnGMMGT5K4zw6iP7ssbOkNIq63je2s3Id3ka8s6Tz1qXuPJCpvhQuw1bHRtVYDeCHteANpusu6fVym6pBJja+b3R5LMn4Ezm+ie3/lrk0Ot/hLJOcTGKjPQnIRqd/JE=";
//        String et = "TSfhxV2JAyY2eH7h0ZlbhcSUT8wl9ubn8+EXkm0hOPYdT/KqrFHLqaFpS81gIYvagnFBCTdvuOU3XFZH4xuUSpTMy0KXFz/Hyay/ZokrEAclYkiO+xst8xsZefglr8LFKtc4KstqHRJ3qT6IrV1cde8lVKp/ECw23SaXuvK2KYU=";
//        String et = "L4zplfysERtcwhRTAKVGuA9CkN7f5YiFV5GFeaQLw+fH8tX4tjab/zhD1dChMkzB0E1qzF35XAX8R/5AQvTixzPSO1dNpqlOJ7KAg5hmyrJqWziIhG62UX45UDGDcDZOchU9BLGRTPaprI78SqQfSX7C2iCeZmlYEVbU+ViTFvE=";
//        String et = "FVJYX4tJENMd0Zo8cgx/1w/2oacFhsEMXcSxYH+QU/nJC8ypH6bfYf2m6zzaeocC2Y+074Dgk9IEaiz+i78l7oypfJD3HzPBKZwKqf3ar6cjjOiVw72M+dnzTO00bkIeiVrukTqb5mH5hMF8JZgHe7hR3OL/wjvBMgQwHNdNkWg=";
//        String et = "oBytT8mroHplMeYG38jW+2Z0tPSMQKDBQLTe20WU7rgroC1CBAVNtEiTSd5KcgfqC+0cTzkZ0YZVLX089Y0cUwkOffRbv51lQHptkOPrl1dZmXk8UJlee9LxVeuEOAv9vW9JTHoS2uvaCFhWBeXPE9R3Hy5Zub07497GfI5kQGc=";
        String et = "TYS1uxVIiVcy9AW1g2m4yMi1FGf7Fh6lVIQWG9IG9kIWp6Uiribp93EmlTYW83CXQlcj8FjaiNoz6fqOHvFaEuo6mv3MqMBGcXRjOBmmQYW/p/dxaf7K8HQ5a9DqNGVeYujVVTXCUOBZNvsidgyTPY9LzKUUs3LJ0LLT7oDPiuI=";


        System.out.println(decrypt(et, priv));

    }

}
