package login;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESHelper {

	/**
	 * @method main
	 * @param args
	 * @throws
	 * @since v1.0
	 */

	public static void main(String[] args) {

		try {
			String str = "mobile:13757352769|type:w|content:luoguoping";
			// encrypt
			String password = "01010101";
			String desc = DESHelper.DoDES(str, password, 0);
			System.out.println("encypted" + desc);
			// decrypt
			str = DESHelper.DoDES(desc, password, 1);
			System.out.println("decypted" + str);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public static String DoDES(String data, String password, int flag) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");

			if (flag == 0) {
				BASE64Encoder base64encoder = new BASE64Encoder();
				cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
				return base64encoder.encode(cipher.doFinal(data
						.getBytes("UTF-8")));
			} else {
				BASE64Decoder base64decoder = new BASE64Decoder();
				byte[] encodeByte = base64decoder.decodeBuffer(data);
				cipher.init(Cipher.DECRYPT_MODE, securekey, random);
				byte[] decoder = cipher.doFinal(encodeByte);
				return new String(decoder, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
