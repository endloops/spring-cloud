package com.cesi.actuatorfirst.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DesUtils {
	/**
	 * log
	 */
	private static Logger logger = LoggerFactory.getLogger(DesUtils.class);

	private static byte[] secureKey = new byte[] { 21, 47, -15, 32, -108, -27, -122, 107, -63, 61, -33, -85, -83, -123,
			62, -60, -92, 81, -125, 35, -108, -29, 2, -105 };

	private static byte[] iv = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };

	/**
	 * 初始化参数
	 */

	private static final String KEYALG = "DESede/CBC/NoPadding";
	/**
	 * 算法
	 */
	private static final String KEY_ALGORITHM = "DESede";

	/**
	 * 
	 * 解密
	 * 
	 * @param string
	 *            加密字符串 string
	 * @return string string 解密的字符串
	 * @throws DesedeException
	 *             DesedeException Desede的异常
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decode(String string) throws InvalidAlgorithmParameterException {

		logger.info("******encry string********:{}", string);
		Key key = toKey(secureKey);
		Cipher cipher = null;
		String result = null;
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			cipher = Cipher.getInstance(KEYALG);

			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

			byte[] resByte = cipher.doFinal(DatatypeConverter.parseHexBinary(string));

			result = new String(resByte, StandardCharsets.UTF_8).trim();

		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error(e.getMessage(), e);

			
		}
		logger.info("********decode string********: {} ", result);
		return result;
	}

	/**
	 * 加密： 因为是NoPadding 所以 加密字符串必须熟8位的整数倍
	 * 
	 * @param string
	 * @return
	 * @throws DesedeException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encode(String string) throws InvalidAlgorithmParameterException {

		logger.info("******encry string********:{}", string);
		Key key = toKey(secureKey);
		Cipher cipher = null;
		String result = null;
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			cipher = Cipher.getInstance(KEYALG);

			cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
			byte[] firstSource = string.getBytes();

			int remainder = firstSource.length % 8;

			byte[] source = null;
			if (remainder == 0) {
				source = firstSource;
			} else {
				source = ArrayUtils.addAll(firstSource, new byte[8 - remainder]);
			}

			byte[] resByte = cipher.doFinal(source);
			result = DatatypeConverter.printHexBinary(resByte);

		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error(e.getMessage(), e);

			
		}
		logger.info("********decode string********: {} ", result);
		return result;
	}

	/**
	 * 加密规则
	 * 
	 * @param key
	 *            密钥
	 * @return Key key
	 */
	private static Key toKey(byte[] key) {
		SecretKey deskey = new SecretKeySpec(key, KEY_ALGORITHM);
		return deskey;
	}

	public static void main(String[] args) throws  UnsupportedEncodingException, Exception {

		System.out.println(DatatypeConverter.printHexBinary(secureKey));

		System.out.println(DatatypeConverter.printHexBinary(iv));

		String enString = encode("VT01,1534935887690");
		System.out.println(enString);
		System.out.println("username " + encode("VT01"));
		System.out.println("password "+ encode("password"));
		System.out.println("timestamp "+ encode("1524146887912"));
		System.out.println(decode("75DCC4B865A09C93"));
//		
		File file = new File("C:/Users/wang/Desktop/ActiveMQ in Action.pdf");
		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream byteArrayOutputStream = new  ByteArrayOutputStream();
		FileCopyUtils.copy(inputStream, byteArrayOutputStream);
		MessageDigest digest = MessageDigest.getInstance("sha-256");
		digest.update(byteArrayOutputStream.toByteArray());
		System.out.println(DatatypeConverter.printHexBinary(digest.digest()));
		Date date = new Date(1536162529028l);
		System.out.println(date);
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		
		TestBean bean = new TestBean();
		System.out.println(mapper.writeValueAsString(bean));
		System.out.println(dateFormat.format(date));
	}
}
