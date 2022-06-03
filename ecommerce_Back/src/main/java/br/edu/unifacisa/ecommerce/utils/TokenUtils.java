package br.edu.unifacisa.ecommerce.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenUtils {

	public static String createToken(String seed) {
		String hashtext = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] messageDigest = md.digest(seed.getBytes(StandardCharsets.UTF_8));
			hashtext = convertToHex(messageDigest);
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
	
	private static String convertToHex(final byte[] messageDigest) {
	    BigInteger bigint = new BigInteger(1, messageDigest);
	    String hexText = bigint.toString(16);
	    while (hexText.length() < 32) {
	       hexText = "0".concat(hexText);
	    }
	    return hexText;
	}
}
