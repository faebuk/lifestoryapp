package com.faebuk.lifestory.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.faebuk.lifestory.R;
import com.faebuk.lifestory.help.Manager;

public class LoginActivity extends Activity implements OnClickListener {

	Manager manager;
	
	EditText etfLoginName;
	EditText pwtfLoginPassword;
	
	TextView tvlHeadline;
	
	Button btnLogin;

	CheckBox cbAutoLogin;

	String username;
	String password;

	String MSG_LOGIN_ERROR;
	
	public static final String PREFS_NAME = "UserPreferencesFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		getActionBar().setTitle("Login");

		//Manager.TypeFaceTextView(tvlHeadline, getAssets());
		
		etfLoginName = (EditText) findViewById(R.id.etfLoginName);
		Manager.TypeFaceEditText(etfLoginName, getAssets());
		
		pwtfLoginPassword = (EditText) findViewById(R.id.pwtfLoginPassword);
		Manager.TypeFaceEditText(pwtfLoginPassword, getAssets());

		cbAutoLogin = (CheckBox) findViewById(R.id.cbLoginAutoLogin);
		Manager.TypeFaceCheckBox(cbAutoLogin, getAssets());
		
		btnLogin = (Button) findViewById(R.id.btnLogin);
		Manager.TypeFaceButton(btnLogin, getAssets());
		btnLogin.setOnClickListener(this);

		username = "name@email.com";
		password = "password";

		MSG_LOGIN_ERROR = getResources().getString(R.string.msg_login_error);
		
		saveSharedPrefs();

	}

	@Override
	public void onClick(View v) {
		if (v == btnLogin) {
			saveSharedPrefs();
			if (etfLoginName.getText().toString().equals(username)
					|| pwtfLoginPassword.getText().toString().equals(password)) {
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(), MSG_LOGIN_ERROR,
						Toast.LENGTH_LONG).show();
			}
		}

	}
	
	private void saveSharedPrefs() {
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("name", etfLoginName.getText().toString());
			editor.putString("password", pwtfLoginPassword.getText()
					.toString());
			editor.putBoolean("autologin", cbAutoLogin.isChecked());
			editor.commit();
	}

}
