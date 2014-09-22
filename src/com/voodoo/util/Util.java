package com.voodoo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Environment;
import android.util.Log;

public class Util {

	private static final String ip = "172.29.201.1";
	private static final String dir = Environment.getExternalStorageDirectory()
			.getPath();

	public static void receiceXML() {
		Socket socket = null;
		try {
			socket = new Socket(Util.ip, 8821);
			InputStream in = socket.getInputStream();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			String temp = "";
			StringBuilder sb = new StringBuilder();

			while ((temp = reader.readLine()) != null) {
				sb.append(temp);
			}

			File fdir = new File(dir + "/damnLazy");
			if (!fdir.exists()) {
				fdir.mkdirs();
			}

			// Log.d("----------", "-----" + dir + "/damnLazy");

			File file = new File(dir + "/damnLazy/" + "info.xml");

			FileWriter writer = new FileWriter(file);
			writer.write("");
			writer.close();

			Log.d("----------", "-----" + file.getAbsolutePath());
			FileOutputStream out = new FileOutputStream(file);

			// Log.d("----------------", "-----"+sb.toString());

			out.write(sb.toString().getBytes());
			out.close();

			// Notice here!!!!
			sb = null;
			fdir = null;
			file = null;

		} catch (UnknownHostException e) {
			System.err.println("Host Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<String> readXML() throws Exception {

		List<String> results = new ArrayList<String>();

		File file = new File(Util.dir + "/damnLazy/" + "info.xml");
		if (!file.exists()) {
			throw new FileNotFoundException("list.xml Not Found");
		}
		InputStream in = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				"utf-8"));
		StringBuilder sb = new StringBuilder();

		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		reader.close();

		Pattern pattern = Pattern.compile("<info.*?url=\"(.*?)\".*?>");
		Matcher matcher = pattern.matcher(sb.toString());

		boolean isFind = matcher.find();
		while (isFind) {
			results.add(matcher.group(1));
			isFind = matcher.find();
		}
		
		for(String s : results){
			Log.d("------------", "------- " + s);
		}
		
		return results;

	}

	public static List<String> resolveXML(String xml) {
		List<String> urls = new ArrayList<String>();
		return urls;
	}

}
