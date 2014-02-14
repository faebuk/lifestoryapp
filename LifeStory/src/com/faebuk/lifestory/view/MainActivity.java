package com.faebuk.lifestory.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.faebuk.lifestory.R;

public class MainActivity extends Activity {

	SlideMenuContainer smc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.smc = (SlideMenuContainer) this.getLayoutInflater().inflate(
				R.layout.activity_main, null);

		this.setContentView(smc);

	}
}
