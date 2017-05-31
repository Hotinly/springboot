package com.boot.spring;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.codec.binary.Base64;

import org.apache.tomcat.util.codec.binary.Base64;

public class ApiSecurityExample {
	public static void main(String[] args) {
		try {
			String secret = "secret";
			String message = "Message";

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);

			String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
			System.out.println(hash);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
}

//qnR8UCqJggD55PohusaBNviGoOJ67HC6Btry4qXLVZc=