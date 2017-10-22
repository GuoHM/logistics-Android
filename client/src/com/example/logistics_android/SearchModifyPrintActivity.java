package com.example.logistics_android;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.util.JsonHelper;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SearchModifyPrintActivity extends Activity {
	private EditText goodsIdFild;
	private Button ok;
	private Button cancel;
	private JsonHelper json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_modify_print);
		initView();
		setListener();
	}

	private void initView() {
		goodsIdFild = (EditText) findViewById(R.id.goodsIdFild);
		ok = (Button) findViewById(R.id.ok);
		cancel = (Button) findViewById(R.id.cancel);
	}

	private void setListener() {
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String goodsId = goodsIdFild.getText().toString();
				try {
					searchService(goodsId);
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
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchModifyPrintActivity.this, DistrictMainActivity.class);
				startActivity(intent);
			}

		});

	}

	private void searchService(String goodsId) throws ClientProtocolException, IOException, JSONException {
		Intent intent = getIntent();
		String center=intent.getStringExtra("center");
		Log.d("1123213131321111111111111111111111111111111111", center);
		intent.putExtra("currentGoods", goodsId);
		intent.putExtra("center", center);
		intent.setClass(SearchModifyPrintActivity.this, ModifyPrintActivity.class);
		startActivity(intent);
	}

	// }
}
