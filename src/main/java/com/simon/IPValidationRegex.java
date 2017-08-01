package com.simon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * For tpg question 1.
 * 
 * 
 * @author simon sew
 * Note : This util only supports ipv4 ip addresses.
 * 
 * Flow:
 * 1. Read from resource file
 * 2. Read line by line
 * 3. Loop each line, 1st remove all leading zeros then only feed it to ipv4 regex validation
 * 4. If not valid, print out the invalid ip address
 */
public class IPValidationRegex {

	private static final String FILE_PATH = "Question1.txt";
//	private Pattern pattern = Pattern.compile("([0-255](?:\\.[0-255]{3}))");
	private Pattern ipPattern = Pattern.compile("^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$");	
	private Pattern matchLeadingZeroPattern = Pattern.compile("(?<=^|\\.)0+(?!\\.|$)");
				
	private boolean validateIp(String line) {
		boolean match=false;
		
		if(line != null && line.trim().length() > 0) {
			Matcher matcher = ipPattern.matcher(line);
			if(matcher.lookingAt()) {
				match=true;
			}
		}		
		return match;
	}
	
	public void readFile(String path) {
		BufferedReader bfr = null;
		try {
			
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			bfr = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(path)));
			String line="";
			
			while((line = bfr.readLine()) != null) {
				
				//remove leading zero
				line = matchLeadingZeroPattern.matcher(line).replaceAll("");				
				
				//validate ip pattern
				if(!validateIp(line)) {
					System.err.println("Invalid Ip Address : " + line);
				}			
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(bfr != null) {
				try {
					bfr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new IPValidationRegex().readFile(IPValidationRegex.FILE_PATH);
	}
}
