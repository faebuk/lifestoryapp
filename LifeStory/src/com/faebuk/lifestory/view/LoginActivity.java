package com.faebuk.lifestory.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.faebuk.lifestory.R;

public class LoginActivity extends Activity implements OnClickListener {

	EditText etfLoginName;
	EditText pwtfLoginPassword;
	
	Button btnLogin;

	CheckBox cbAutoLogin;

	String username;
	String password;

	String MSG_LOGIN_ERROR;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		etfLoginName = (EditText) findViewById(R.id.etfLoginName);
		pwtfLoginPassword = (EditText) findViewById(R.id.pwtfLoginPassword);
		
		cbAutoLogin = (CheckBox) findViewById(R.id.cbLoginAutoLogin);
		
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);

		username = "name@email.com";
		password = "password";

		MSG_LOGIN_ERROR = getResources().getString(R.string.msg_login_error);

	}

	@Override
	public void onClick(View v) {
		if (v == btnLogin) {
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

}
