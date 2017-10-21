package com.example.logistics_android;

import android.os.Bundle;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import com.example.util.JsonHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class LoginActivity extends Activity {
	private EditText userField;
	private EditText passwordField;
	private RadioGroup radioGroup;
	private Button ok;
	private JsonHelper json;
	private int type=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		setListener();
	}

	private void initView() {
		userField = (EditText) findViewById(R.id.userField);
		passwordField = (EditText) findViewById(R.id.passwordField);
		ok = (Button) findViewById(R.id.ok);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
	}

	private void setListener() {
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				RadioButton radioButton = (RadioButton) findViewById(arg0.getCheckedRadioButtonId());
				String str = radioButton.getText().toString();
				if (str.equals("区县营业点")) {
					type = 1;
				} else if (str.equals("管理员")) {
					type = 2;
				} else {
					type = 3;
				}
			}

		});
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userid = userField.getText().toString();
				String password = passwordField.getText().toString();
				try {
					loginService(userid, password, type);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	private void loginService(String userid, String password, int type)
			throws ClientProtocolException, IOException, JSONException {
		json.setParameter("userid", userid);
		json.setParameter("password", password);
		json.setParameter("type", type);
		json.processURL("login");
		Integer result =  (Integer) json.getJsonData("login");
		Intent intent;
		switch (result) {
		case 0:
			AlertDialog.Builder builder = new Builder(LoginActivity.this);
			builder.setTitle("错误").setMessage("用户名密码错误").setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).create().show();
			break;
		case 1:
			intent = new Intent(LoginActivity.this, DistrictMainActivity.class);
			intent.putExtra("center", json.getJsonData("center").toString());
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(LoginActivity.this, ProvinceMainActivity.class);
			intent.putExtra("admin", json.getJsonData("admin").toString());
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(LoginActivity.this, ProvinceMainActivity.class);
			intent.putExtra("province", json.getJsonData("province").toString());
			startActivity(intent);
			break;
		}
	}


}
