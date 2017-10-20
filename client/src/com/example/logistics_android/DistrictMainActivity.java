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
import android.widget.TextView;

public class DistrictMainActivity extends Activity {
	private JsonHelper json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.json = new JsonHelper();
		setContentView(R.layout.activity_district);
		final Button btn1 = (Button) findViewById(R.id.district_modifyprint);
		final Button btn2 = (Button) findViewById(R.id.district_sort);
		final Button btn3 = (Button) findViewById(R.id.district_send);
		final Button btn4 = (Button) findViewById(R.id.district_exit);
		final TextView show = (TextView) findViewById(R.id.district_show);
		final Intent intent = getIntent();
		final String center = intent.getStringExtra("center");
		show.setText("当前营业点：" + center + "营业点");
		btn1.setOnClickListener(new OnClickListener() {// 点击修改打印进行跳转
			public void onClick(View view) {
				intent.setClass(DistrictMainActivity.this, SearchModifyPrintActivity.class);
				intent.putExtra("center", center);
				startActivity(intent);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {// 添加状态信息，表示分拣成功
			public void onClick(View view) {
				intent.setClass(DistrictMainActivity.this, DistrictSortActivity.class);
				startActivity(intent);

			}
		});
		btn3.setOnClickListener(new OnClickListener() {// 添加状态信息，表示派送成功
			public void onClick(View view) {
				try {
					json.processURL("addreceiverDistrictListStatus");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DistrictMainActivity.this, MainActivity.class);
				startActivity(intent);
			}

		});

	}

}
