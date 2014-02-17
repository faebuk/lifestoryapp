package com.faebuk.lifestory.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faebuk.lifestory.R;

public class SettingsFragment extends Fragment{


	public SettingsFragment() {
		// Empty constructor required for fragment subclasses
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_einstellungen,
				container, false);
			
		return rootView;
	}
}
