package com.example.sensorutility;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

public class accelerometer3 extends Service implements SensorEventListener{
	SensorManager sm;
	Sensor acc3;
    SharedPreferences sp;
    Intent launchIntent;
    @Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) { 
		sm=(SensorManager) getSystemService(SENSOR_SERVICE);
		 acc3=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sp=getSharedPreferences("user3",MODE_PRIVATE);
		boolean status = sp.getBoolean("checkArray[3]", false);
		 if(status==true){
	        	sm.registerListener(this,acc3,SensorManager.SENSOR_DELAY_NORMAL);
	        } else
	        	sm.unregisterListener(this,acc3);
			return START_STICKY;
	}
	public boolean doesPackageExist(String targetPackage) {
		PackageManager pm = getPackageManager();
		try {
			PackageInfo info=pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			return false;
		}
		return true;
		
	}
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		
	}
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		float[] sp = arg0.values;
		if (sp[2] >13) {
			if(doesPackageExist("com.motorola.MotGallery2")){
			launchIntent = getPackageManager()
					.getLaunchIntentForPackage("com.motorola.MotGallery2");
			startActivity(launchIntent);}
			else if(doesPackageExist("com.android.gallery3d")){
				launchIntent = getPackageManager()
						.getLaunchIntentForPackage("com.android.gallery3d");
				startActivity(launchIntent);}
			else if(doesPackageExist("com.htc.album")){
				launchIntent = getPackageManager()
						.getLaunchIntentForPackage("com.htc.album");
				startActivity(launchIntent);}
			else if(doesPackageExist("com.google.android.gallery3d")){
				launchIntent = getPackageManager()
						.getLaunchIntentForPackage("com.google.android.gallery3d");
				startActivity(launchIntent);}
			else if(doesPackageExist("com.sec.android.gallery3d")){
				launchIntent = getPackageManager()
						.getLaunchIntentForPackage("com.sec.android.gallery3d");
				startActivity(launchIntent);}
			else
				Toast.makeText(this,"app is not installed in your phone",Toast.LENGTH_SHORT).show();
		}
		
	}
	
	}

