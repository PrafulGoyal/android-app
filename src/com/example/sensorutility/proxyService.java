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

public class proxyService extends Service implements SensorEventListener{
	SensorManager sm;
	Sensor proxy;
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
		 proxy=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		sp=getSharedPreferences("user",MODE_PRIVATE);
		boolean status = sp.getBoolean("checkArray[0]", false);
        if(status==true){
        	sm.registerListener(this, proxy,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        	sm.unregisterListener(this,proxy);
		return START_STICKY;
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		
	}
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		float[] sp = arg0.values;
		if (sp[0] <= 3) {
			if(doesPackageExist("com.whatsapp")){
			Intent launchIntent = getPackageManager()
					.getLaunchIntentForPackage("com.whatsapp");
			startActivity(launchIntent);
		}
			else 
				Toast.makeText(getBaseContext(), "app is not installed in your phone",Toast.LENGTH_SHORT).show();
		}
		}
	
	
	}

