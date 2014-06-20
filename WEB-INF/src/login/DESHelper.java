/**
 * DESHelper.java
 * cn.com.songjy.test
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2012-6-29 		songjianyong
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
 */

package login;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * ClassName:DESHelper
 * 
 * @author songjianyong
 * @version 1.0
 * @since v1.0
 * @Date 2012-6-29 下午1:36:05
 */
public class DESHelper {

	/**
	 * @method main
	 * @param args
	 * @throws
	 * @since v1.0
	 */

	public static void main(String[] args) {

		try {
			// 明文
			String str = "mobile:13757352769|type:w|content:测试";
			// 密钥(客户拥有)
			String password = "01010101";
			String desc = DESHelper.DoDES(str, password, 0);
			System.out.println("密文：" + desc);
			// 解密
			str = DESHelper.DoDES(desc, password, 1);
			System.out.println("明文：" + str);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 
	 * @method DoDES
	 * @param data
	 *            需要加密解密的数据
	 * @param password
	 *            密钥 必须8位字节
	 * @param flag
	 *            加密解密标志 0：加密 ，1：解密
	 * @return
	 * @throws
	 * @since v1.0
	 */
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
