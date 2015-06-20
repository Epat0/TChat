package com.tchat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;

public class splash extends Activity{

	SharedPreferences preferences;
	private int count;
	private final int splash_lenght = 3000;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
		count = preferences.getInt("count", 0);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(count == 0){
					Intent intent = new Intent();
					intent.setClass(splash.this, guide_one.class);
					startActivity(intent);
					overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
					Editor editor = preferences.edit();
					editor.putInt("count", ++count);
					editor.commit();
					splash.this.finish();
				}else{
					Intent intent2 = new Intent();
					intent2.setClass(splash.this, LoginActivity.class);
					startActivity(intent2);
					overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
					splash.this.finish();
				}
			}
		}, splash_lenght);
	}
}
