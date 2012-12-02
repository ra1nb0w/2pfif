/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hipifif.pfif;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author ra1nb0w
 */
public class HTTPRequest {

	/*
	 * TODO: return check value
	 */
	public static boolean SendPostData(String postData) throws MalformedURLException, IOException {
		boolean ret=true;
		String request = "https://rhoktrento.appspot.com/haiti/api/write?key=agxzfnJob2t0cmVudG9yGQsSDUF1dGhvcml6YXRpb24iBmhhaXRpOgw";
		URL url = new URL(request);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));
		connection.setUseCaches(false);

		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(postData);
		
		// display error
		if (connection.getResponseCode() >= 300 ) {
			JOptionPane error = new JOptionPane();
			JOptionPane.showMessageDialog(null, "Connection Error. Code: "+connection.getResponseCode(), "Connection error", JOptionPane.ERROR_MESSAGE);
			System.out.println("Resp Code:"+connection.getResponseCode()); 
			System.out.println("Resp Message:"+ connection.getResponseMessage());
			ret = false;
		}
		
		// get ready to read the response from the cgi script 
		DataInputStream input = new DataInputStream( connection.getInputStream() );
		
		// analyse the response to check bad behaviour
		// for now return 1
		for( int c = input.read(); c != -1; c = input.read() ) {
			System.out.print( (char)c ); 
		}
		
		wr.flush();
		wr.close();
		connection.disconnect();
		return(ret);
	}
}
