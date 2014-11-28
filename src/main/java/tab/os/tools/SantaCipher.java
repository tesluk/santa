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

    private static final int KEY_LENGTH = 512;

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
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        ByteArrayInputStream bis = new ByteArrayInputStream(text.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        CipherOutputStream cos = new CipherOutputStream(bos, cipher);

        int c;
        byte[] buf = new byte[32];
        while ((c = bis.read(buf)) != -1) {
            cos.write(buf, 0, c);
        }
        bis.close();
        cos.close();

        return Base64.encodeBase64String(bos.toByteArray());
    }

    public static String decryptRSA(String str, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(str));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        CipherInputStream cis = new CipherInputStream(bis, cipher);

        int c;
        byte[] buf = new byte[32];
        while ((c = cis.read(buf)) != -1) {
            bos.write(buf, 0, c);
        }
        cis.close();
        bos.close();

        return new String(bos.toByteArray());
    }


}
