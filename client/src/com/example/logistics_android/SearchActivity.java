package com.example.logistics_android;

import android.os.Bundle;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends Activity {
	private EditText goodsIdField;
	private Button confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initView();
		setListener();
	}

	private void initView() {
		goodsIdField = (EditText) findViewById(R.id.goodsIdField);
		confirm = (Button) findViewById(R.id.confirm);
	}

	private void setListener() {
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String goodsId = goodsIdField.getText().toString();
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
	}

	private void searchService(String goodsId) throws ClientProtocolException, IOException, JSONException {
		Intent intent = new Intent();
		intent.putExtra("searchGoodsId", goodsId);
		intent.setClass(SearchActivity.this, SearchResultActivity.class);
		startActivity(intent);
	}

}
