package com.faebuk.lifestory.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.faebuk.lifestory.R;

public class MainActivity extends Activity {

	SlideMenuContainer smc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.smc = (SlideMenuContainer) this.getLayoutInflater().inflate(R.layout.activity_main, null);

		this.setContentView(smc);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(R.drawable.ic_launcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home : 
			this.smc.toggleMenu();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void toggleMenu(View v){
		this.smc.toggleMenu();
	}
}
