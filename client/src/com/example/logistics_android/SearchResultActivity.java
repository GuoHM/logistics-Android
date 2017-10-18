package com.example.logistics_android;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import com.example.util.JsonHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.TextView;

public class SearchResultActivity extends Activity {
	private TextView searchResult_goodsId;
	private TextView searchResult_senderInfo;
	private TextView searchResult_receiverInfo;
	private TextView searchResult_status;
	
	private Button searchResult_return;
	private JsonHelper json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		try {
			initView();
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
		setListener();
	}

	private void initView() throws ClientProtocolException, IOException, JSONException {
		searchResult_goodsId = (TextView) findViewById(R.id.searchResult_goodsId);
		searchResult_senderInfo = (TextView) findViewById(R.id.searchResult_senderInfo);
		searchResult_receiverInfo = (TextView) findViewById(R.id.searchResult_receiverInfo);
		searchResult_status = (TextView) findViewById(R.id.searchResult_status);
		searchResult_return = (Button) findViewById(R.id.searchResult_return);
		Intent intent = getIntent();
		String goodsId = intent.getStringExtra("searchGoodsId");
		
		json.setParameter("searchGoodsId", goodsId);
		json.processURL("searchGoodsByID");
		searchResult_status.setText(json.getJsonData("status").toString().replaceAll("a", "\n"));
		searchResult_goodsId.setText("µ¥ºÅ£º" + goodsId);
		searchResult_receiverInfo.setText(json.getJsonData("receiverInfo").toString().replaceAll("a", "\n").replaceAll("b", ":"));
		searchResult_senderInfo.setText(json.getJsonData("senderInfo").toString().replaceAll("a", "\n").replaceAll("b", ":"));
	}

	public void setListener() {
		searchResult_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
				startActivity(intent);
			}

		});
	}
}
