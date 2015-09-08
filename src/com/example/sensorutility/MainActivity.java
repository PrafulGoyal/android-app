package com.example.sensorutility;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
     Intent i;
     ActionBar ab;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ab=getActionBar();
		ab.hide();
		i=new Intent(getBaseContext(),MainActivity2.class);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
			try{
				Thread.sleep(1000);
				startActivity(i);
			}catch (Exception e) {
				e.printStackTrace();
			}
				finish();
			}
		}).start();
	}



}
