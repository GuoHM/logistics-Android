package com.example.logistics_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import com.example.util.JsonHelper;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;;

public class ProvinceMainActivity extends Activity {
	private JsonHelper json;
	private Button province_sort;
	private Button province_send;

	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_province_main);
		initView();
		setListener();
	}

	private void initView() {
		province_sort = (Button) findViewById(R.id.province_sort);
		province_send = (Button) findViewById(R.id.province_send);
	}

	private void setListener() {
		province_sort.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					json.processURL("addsenderProvinceListStatus");
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

		province_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					json.processURL("addsenderDistrictListStatus");
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
	}

}
