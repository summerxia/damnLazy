package com.voodoo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Environment;
import android.util.Log;

public class Util {
	
	 
	private static final String ip = "10.6.11.24";
	private static final String dir = Environment.getExternalStorageDirectory().getPath();

	public static void receiceXML() {
		Socket socket = null;
		try {
			 socket = new Socket(Util.ip, 8821);
			InputStream in = socket.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String temp = "";
			StringBuilder sb = new StringBuilder();

			while ((temp = reader.readLine()) != null) {
				sb.append(temp);
			}

			
			File fdir = new File(dir+"/damnLazy");
			if(!fdir.exists()){
				fdir.mkdirs();
			}
			
			
			//Log.d("----------", "-----" + dir + "/damnLazy");
			
			
			File file = new File(dir+"/damnLazy/"+"info.xml");
			Log.d("----------", "-----"+file.getAbsolutePath());
			FileOutputStream out = new FileOutputStream(file);
			
			//Log.d("----------------", "-----"+sb.toString());
			
			out.write(sb.toString().getBytes());
			out.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void readXML() {

	}

}
