package com.faebuk.lifestory.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.faebuk.lifestory.R;

public class MainActivity extends Activity {

	SlideMenuContainer smc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.smc = (SlideMenuContainer) this.getLayoutInflater().inflate(R.layout.activity_main, null);

		this.setContentView(smc);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void toggleMenu(View v){
		this.smc.toggleMenu();
	}
}
