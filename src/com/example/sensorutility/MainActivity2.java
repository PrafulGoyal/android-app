package com.example.sensorutility;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends Activity {
	CharSequence[] Item = { "whatsapp", "music", "gmail", "Gallery" };
    boolean status1,status2,status3,status4;
	boolean[] checkarray = new boolean[Item.length];
	boolean[] bl = { false, false, false, false };
	SensorManager sm;
	Sensor acc, light;
	ActionBar ab;
	SharedPreferences sp,sp1,sp2,sp3;
	SharedPreferences.Editor sped,sped1,sped2,sped3;
	Intent i,j,k,l;
	AlertDialog.Builder builder1;
	AlertDialog.Builder builder2;
	int counter=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout);
		ab = getActionBar();
		ab.setIcon(R.drawable.appicon);
		sp = getSharedPreferences("user", MODE_PRIVATE);
		sp1 = getSharedPreferences("user1", MODE_PRIVATE);
		sp2= getSharedPreferences("user2", MODE_PRIVATE);
		sp3 = getSharedPreferences("user3", MODE_PRIVATE);
		sped = sp.edit();
		sped1 = sp1.edit();
		sped2= sp2.edit();
		sped3 = sp3.edit();
		
status1=sp.getBoolean("checkArray[0]",false);
status2=sp1.getBoolean("checkArray[1]",false);
status3=sp2.getBoolean("checkArray[2]",false);
status4=sp3.getBoolean("checkArray[3]",false);}
	public void started(View v) {
		showDialog(2);
	}

	@SuppressWarnings("deprecation")
	public void showMe(View v) {
		showDialog(1);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mnu1 = menu.add(1, 1, 1, "About");
		MenuItem mnu2 = menu.add(1, 2, 2, "Developers");
		return true;
	}

	public void start(View v) {
		Toast.makeText(getBaseContext(), "APP STARTED ",Toast.LENGTH_SHORT).show();
		counter++;
		 i = new Intent(getBaseContext(), proxyService.class);
		startService(i);
		 j = new Intent(getBaseContext(), accelerometer1.class);
		startService(j);
	  k = new Intent(getBaseContext(), accelerometer2.class);
		startService(k);
	 l = new Intent(getBaseContext(), accelerometer3.class);
		startService(l);
	
		
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			showDialog(2);
			break;
		case 2:
		  showDialog(3);
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onBackPressed() {
		// Display alert message when back button has been pressed
		backButtonHandler();
		return;
	}

	public void backButtonHandler() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setIcon(R.drawable.exiticon);
		// Setting Dialog Title
		alertDialog.setTitle("Leave application?");
		// Setting Dialog Message
		alertDialog
				.setMessage("Are you sure you want to leave the application?");
		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event
						dialog.cancel();
					}
				});
		// Showing Alert Message
		alertDialog.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == 1) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Select");
			bl[0]=status1;
			bl[1]=status2;
			bl[2]=status3;
			bl[3]=status4;
			builder.setMultiChoiceItems(Item, bl,
					new OnMultiChoiceClickListener() {
			         
						@Override
						public void onClick(DialogInterface arg0, int arg1,
								boolean arg2) {
							
							CharSequence ch = Item[arg1];
							
							if (arg2 == true) {
								checkarray[arg1] = arg2;
								if (checkarray[0] == true) {
									sped.putBoolean("checkArray[0]", arg2);
									if(counter>=2){
										startService(i);
									}
								}
								if (checkarray[1] == true) {
									sped1.putBoolean("checkArray[1]", arg2);
									if(counter>=2){
										startService(j);
									}
								}
								if (checkarray[2] == true) {
									sped2.putBoolean("checkArray[2]", arg2);
									if(counter>=2){
										startService(k);
									}
								}
								if (checkarray[3] == true) {
									sped3.putBoolean("checkArray[3]", arg2);
									if(counter>=2){
										startService(l);
									}
								}
								sped.commit();
								sped1.commit();
								sped2.commit();
								sped3.commit();
							} else {
								checkarray[arg1] = arg2;
								if (checkarray[0] == false) {
									sped.putBoolean("checkArray[0]", arg2);
									if(counter>=2){
										startService(i);
									}
								}
								if (checkarray[1] == false) {
									sped.putBoolean("checkArray[1]", arg2);
									if(counter>=2){
										startService(j);
									}
								}
								if (checkarray[2] == false) {
									sped.putBoolean("checkArray[2]", arg2);
									if(counter>=2){
										startService(k);
									}
								}
								if (checkarray[3] == false) {
									sped.putBoolean("checkArray[3]", arg2);
									if(counter>=2){
										startService(l);
									}
								}
								sped.commit();
							}
						}
					});
			return builder.create();
		}
		if (id == 2) {
			 builder1 = new AlertDialog.Builder(this);
			builder1.setTitle("about");
			builder1.setIcon(R.drawable.appinfoicon);
			builder1.setMessage("Sensor Utility app helps you to start your basic inbuilt application by just shaking your mobile phone in different direction.\n\nThis app contain four basic application.\n 1.Whatsapp - Move Your Hand Over Phone Screen To Open Whatsapp. \n 2.Music - Shake Your Phone In Left-Right Direction To Open Music player. \n 3.Gmail - Shake your phone In Up down Direction To Open Gmail. \n 4.Gallery - Shake your phone In Backward-Forward Directin To Open Gallery.\nPress Start button to start the application. You can also choose which application you want to run by just selecting the given application in settings button.you must click on start button after making changes on settings button to get desired result. ");
			builder1.setPositiveButton("OK", new OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
                        
                        
				}
	
			});
			return builder1.create();
		}
		if (id == 3) {
			 builder2 = new AlertDialog.Builder(this);
			builder2.setTitle("Developers");
			builder2.setMessage("PRAFUL GOYAL\n" +
					"RONAK BORAWAR ");
			builder2.setPositiveButton("OK", new OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
                       
                       
				}
	
			});
			return builder2.create();
		}
		return super.onCreateDialog(id);
	}




}

