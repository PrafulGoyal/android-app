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

public class accelerometer2 extends Service implements SensorEventListener{
	SensorManager sm;
	Sensor acc2;
    SharedPreferences sp;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
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
public int onStartCommand(Intent intent, int flags, int startId) {
	 sm=(SensorManager) getSystemService(SENSOR_SERVICE);
	 acc2=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	 sp=getSharedPreferences("user2",MODE_PRIVATE);
	 boolean status = sp.getBoolean("checkArray[2]", false);
	 if(status==true){
     	sm.registerListener(this,acc2,SensorManager.SENSOR_DELAY_NORMAL);
     } else
    	 sm.unregisterListener(this,acc2);
		return START_STICKY;
	
}
@Override
public void onAccuracyChanged(Sensor arg0, int arg1) {
	
	
}
@Override
public void onSensorChanged(SensorEvent arg0) {
		
		float[] sp = arg0.values;
		if (sp[1] > 13) {
			if(doesPackageExist("com.google.android.gm")){
			Intent launchIntent = getPackageManager()
					.getLaunchIntentForPackage("com.google.android.gm");
			startActivity(launchIntent);
		}
			else
				Toast.makeText(getBaseContext(), "app is not installed in your phone",Toast.LENGTH_SHORT).show();
		}
}
}
