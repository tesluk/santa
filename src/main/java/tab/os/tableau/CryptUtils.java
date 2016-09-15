package tab.os.tableau;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

/**
 * @author andrew.tesliuk
 */
public class CryptUtils {

    public static String getEncryptedPass(String modulusStr, String exponentStr, String password) {
        try {
            BigInteger modulus = new BigInteger(modulusStr, 16);
            BigInteger exponent = new BigInteger(exponentStr, 16);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec pub = new RSAPublicKeySpec(modulus, exponent);
            PublicKey pubkey = keyFactory.generatePublic(pub);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);

            // Encrypt the password with the created public key
            byte[] cipherData = cipher.doFinal(password.getBytes());
            String encryptedPass = Hex.encodeHexString(cipherData);

            return encryptedPass;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException e) {
            //            log.error("RSA Cipher error. {}", e.getMessage());
            throw new RuntimeException("RSA Cipher error.");
        } catch (InvalidKeySpecException | InvalidKeyException e) {
            String msg = String.format("Malformed auth parameters. %s", e.getMessage());
            //            log.error(msg);
            throw new RuntimeException(msg, e);
        }
    }

}
