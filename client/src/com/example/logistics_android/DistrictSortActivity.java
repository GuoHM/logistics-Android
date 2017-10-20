package com.example.logistics_android;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.util.JsonHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DistrictSortActivity extends Activity {
	private JsonHelper json;
	private TextView display;
	private Button sort;
	private Button exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_district_sort);
		Intent intent = getIntent();
		String center = intent.getStringExtra("center");
		initView();
		setListener();
	}

	private void initView() {
		display = (TextView) findViewById(R.id.sort_display);
		sort = (Button) findViewById(R.id.sort_sort);
		exit = (Button) findViewById(R.id.sort_exit);
		Intent intent = getIntent();
		String center = intent.getStringExtra("center");
		json.setParameter("currentDistrict", center);
		try {
			json.processURL("getGoodsByDistrict");
			display.setText(json.getJsonData("result").toString().replaceAll("a", ":").replaceAll("b", "\n"));
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

	private void setListener() {
		sort.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				json = new JsonHelper();
				Intent intent = getIntent();
				String center = intent.getStringExtra("center");
				json.setParameter("currentDistrict", center);
				try {
					json.processURL("senditsDistrict");
					if((Integer)json.getJsonData("success")==1) {
						AlertDialog.Builder builder = new Builder(DistrictSortActivity.this);
						builder.setTitle("成功").setMessage("已发往省营业点").setPositiveButton("确定", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						}).create().show();
					}
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
		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DistrictSortActivity.this,DistrictMainActivity.class);
				startActivity(intent);
			}

		});
	}
}
