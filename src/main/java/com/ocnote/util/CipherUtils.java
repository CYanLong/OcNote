package com.ocnote.util;

public class CipherUtils {
	
	public static String cipher(String sourceStr){
		
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(sourceStr.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	throw new RuntimeException("MD5 Cipher" + e.getMessage());
	    }		
	}
	
	
}
