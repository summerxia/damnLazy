package com.voodoo.damnlazy;

import com.voodoo.util.Util;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Util.receiceXML();
			}
		};
		
		new Thread(run).start();

	}

}
