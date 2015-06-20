package com.tchat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	EditText mEtUserName;

	EditText mEtPassword;

	View mIvClearUserName;

	View mIvClearPassword;

	Button mBtnLogin;

	private String mUserName;
	private String mPassword;

	private String userName;
	private String passWord;
	
	TextWatcher mUserNameWatcher;
	TextWatcher mPassswordWatcher;
	
	TextView linkForgotPassword;

	private SharedPreferences sp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
		
		if ( !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord))
		{
			handleLogin();
		}
	}

	public void initView() {
		sp = this.getSharedPreferences("userInfo", MODE_PRIVATE);  
		
		mEtUserName = (EditText) findViewById(R.id.et_username);
		mEtPassword = (EditText) findViewById(R.id.et_password);
		mIvClearUserName = (View) findViewById(R.id.iv_clear_username);
		mIvClearPassword = (View) findViewById(R.id.iv_clear_password);
		mBtnLogin = (Button) findViewById(R.id.btn_login);
		
		linkForgotPassword = (TextView) findViewById(R.id.register_link);

		linkForgotPassword.setClickable(true);
		linkForgotPassword.setFocusable(true);
		
		linkForgotPassword.setOnClickListener(this);
		
		mIvClearUserName.setOnClickListener(this);
		mIvClearPassword.setOnClickListener(this);
		mBtnLogin.setOnClickListener(this);

		mUserNameWatcher = new SimpleTextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mIvClearUserName
						.setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE
								: View.VISIBLE);
			}
		};

		mPassswordWatcher = new SimpleTextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mIvClearPassword
						.setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE
								: View.VISIBLE);
			}
		};

		mEtUserName.addTextChangedListener(mUserNameWatcher);
		mEtPassword.addTextChangedListener(mPassswordWatcher);

	}

	public void initData() {
		userName = sp.getString("userName", "");
		passWord = sp.getString("passWord","");
		mEtUserName.setText(userName);
		mEtPassword.setText(passWord);

	}

	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.iv_clear_username:
			mEtUserName.getText().clear();
			mEtUserName.requestFocus();
			break;
		case R.id.iv_clear_password:
			mEtPassword.getText().clear();
			mEtPassword.requestFocus();
			break;
		case R.id.btn_login:
			handleLogin();
			break;
		case R.id.register_link:
			handleForgotPassword();
			break;
		default:
			break;
		}
	}
	private void Hint(EditText e,String s){
		Toast.makeText(LoginActivity.this,s, Toast.LENGTH_LONG).show();
		e.setText("");
		e.setFocusable(true);
		e.requestFocus();
	}
	private void handleLogin() {
		mUserName = mEtUserName.getText().toString();
		mPassword = mEtPassword.getText().toString();
		if ( mUserName.replace(" ","").isEmpty()){
			Hint(mEtUserName,"手机号码不能为空");
			return;
		}
		if (mPassword.replace(" ","").isEmpty()){
			Hint(mEtPassword,"密码不能为空");
			return;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");  
		Matcher m = p.matcher(mUserName);
		System.out.println(m.matches());
		if(m.matches() != true){
			Hint(mEtUserName,"请输入正确的手机号码");
			return;
		}
		if(mPassword.equals("1"))  
        {  
			Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show(); 
			 Editor editor = sp.edit();  
             editor.putString("userName", mUserName);  
             editor.putString("passWord",mPassword);  
             editor.commit();  
             Intent intent = new Intent(LoginActivity.this,MainActivity.class);  
             LoginActivity.this.startActivity(intent);  
             this.finish();
        }
		else{  
            Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新登录", Toast.LENGTH_LONG).show();  
        }  
	}
	
	private void handleForgotPassword(){
		
	}
	
	
	
	

}