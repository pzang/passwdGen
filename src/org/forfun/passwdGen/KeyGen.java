package org.forfun.passwdGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class KeyGen {
	private static char charArray[];
	private static int n;
	
	static {
		charArray = genChars();
	}

	// useless now.
	public KeyGen(int num){
		n = num;
	}
	
	public static char[] genChars(){
		char arr[] = new char[62] ;
		for(char i='a', j='A'; i <= 'z'; i++, j++){
			arr[i-'a'] = i;
			arr[26+j-'A'] = j;
		}
		
		for(char i='0'; i<='9'; i++){
			arr[52 + i-'0'] = i;
		}
		return arr;
	}
	
	
	/**
	 * Main
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("args length:" + args.length);
		int n = 16;
		if(args.length == 1){
			try{
				n = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex){
				System.out.println("ERROR: Input a number to indicate password length.");
				return;
			}
		}
		
		String path ;
		if(args.length == 2){
			path = args[1];
			KeyGen.genAndSaveFile(n, path);
		}else {
			KeyGen.genAndSaveFile(n);
		}
		
	}
	
	public static String genAndSaveFile(int n) throws IOException{
		String path = "./" + new Date().toString();
		
		return genAndSaveFile(n , path.replaceAll("\\s+", "_"));
		
	}
	
	public static String genAndSaveFile(int n, String path) throws IOException{
		String result = KeyGen.genKey(n);
		System.out.println("Generated password: " + result);
		File f = new File(path);
		if(!f.exists())
			f.createNewFile();
		PrintWriter out = new PrintWriter(path);
		out.write(result);
		out.close();
		System.out.println("Write to file: " + path);
		return result;
	}
	
	public static String genKey(int num){
		n = num;
		int index;
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i<n; i++){
			index = (int) Math.round(Math.random()*61);
			builder.append(KeyGen.charArray[index]);
		}
		return new String(builder);
	}

}
