package com.faebuk.lifestory.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.faebuk.lifestory.R;

public class SplashActivity extends Activity {

	public static final String PREFS_NAME = "UserPreferencesFile";
	private boolean isAutoLogged;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		loadSharedPrefs();
		LogoTimer();
	}

	public void LogoTimer() {
		Thread logoTimer = new Thread() {
			public void run() {
				try {
					sleep(2000);
					Intent i;
					if (isAutoLogged == true) {
						i = new Intent(SplashActivity.this, MainActivity.class);
					} else {
						i = new Intent(SplashActivity.this, LoginActivity.class);
					}
					startActivity(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// Nothing
				}
			}
		};
		logoTimer.start();
	}

	private void loadSharedPrefs() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		isAutoLogged = settings.getBoolean("autologin", false);
	}
}