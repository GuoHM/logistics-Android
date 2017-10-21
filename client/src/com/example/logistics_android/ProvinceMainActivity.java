package com.example.logistics_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import com.example.util.JsonHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;;

public class ProvinceMainActivity extends Activity {
	private JsonHelper json;
	private Button province_sort;
	private Button province_send;
	private TextView show;
	private String province;

	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_province_main);
		Intent intent= getIntent();
		province = intent.getStringExtra("province");
		initView();
		setListener();
	}

	private void initView() {
		province_sort = (Button) findViewById(R.id.province_sort);
		province_send = (Button) findViewById(R.id.province_send);
		show=(TextView) findViewById(R.id.province_show);
		show.setText("当前营业点：" + province + "营业点");
	}

	private void setListener() {
		province_sort.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("province", province);
				intent.setClass(ProvinceMainActivity.this, ProvinceSortActivity.class);
				startActivity(intent);
			}
		});

		province_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("province", province);
				intent.setClass(ProvinceMainActivity.this, ProvinceSendActivity.class);
				startActivity(intent);
			}
		});
	}

}
