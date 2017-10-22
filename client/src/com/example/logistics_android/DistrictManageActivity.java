package com.example.logistics_android;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.util.JsonHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class DistrictManageActivity extends Activity {

	private EditText provincename;
	private EditText cityname;
	private EditText districtname;
	private EditText password;
	private EditText passwordrepeat;

	private Button adddistrict;
	private JsonHelper json;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_district_manage);
		initView();
		setListener();
	}

	private void initView(){
		provincename = (EditText) findViewById(R.id.districtmanage_provincename);
		cityname = (EditText) findViewById(R.id.districtmanage_cityname);
		districtname = (EditText) findViewById(R.id.districtmanage_districtname);
		password = (EditText) findViewById(R.id.districtmanage_password);
		passwordrepeat = (EditText) findViewById(R.id.districtmanage_repeatpassword);
		adddistrict = (Button) findViewById(R.id.adddistrict);
	}

	private void setListener(){
		adddistrict.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String password1 = password.getText().toString();
				String password2 = passwordrepeat.getText().toString();
				if(password1.equals(password2)){
					String province = provincename.getText().toString();
					String city = cityname.getText().toString();
					String district = districtname.getText().toString();
					try {
						addDistrict(province,city,district,password1);
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
				} else {
					AlertDialog.Builder builder = new Builder(DistrictManageActivity.this);
					builder.setTitle("错误").setMessage("两次输入密码不一致").setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).create().show();
				}
			}
			
		});
	}
	
	private void addDistrict(String province,String city,String district,String password) throws ClientProtocolException, IOException, JSONException{
		json.setParameter("district", district);
		json.setParameter("province", province);
		json.setParameter("city", city);
		json.setParameter("password", password);
		json.processURL("addDistrict");
		if((Integer)json.getJsonData("success")==1){
			AlertDialog.Builder builder = new Builder(DistrictManageActivity.this);
			builder.setTitle("错误").setMessage("新增成功").setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).create().show();
		}
	}



	

}