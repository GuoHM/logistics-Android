package com.example.logistics_android;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.bean.*;
import com.example.util.JsonHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchResultActivity extends Activity {
	private TextView searchResult_goodsId;
	private TextView searchResult_status1;
	private TextView searchResult_status2;
	private TextView searchResult_status3;
	private TextView searchResult_status4;
	private TextView searchResult_status5;
	private TextView searchResult_status6;
	private TextView searchResult_receiverName;
	private TextView searchResult_receiverProvince;
	private TextView searchResult_receiverCity;
	private TextView searchResult_receiverDistcit;
	private TextView searchResult_receiverAddress;
	private TextView searchResult_receiverPhone;
	private TextView searchResult_senderName;
	private TextView searchResult_senderProvince;
	private TextView searchResult_senderCity;
	private TextView searchResult_senderDistrict;
	private TextView searchResult_senderAddress;
	private TextView searchResult_senderPhone;
	private Button searchResult_return;
	private final String CONDITION1 = "订单已提交";
	private final String CONDITION2 = "快递已到达A区县营业点";
	private final String CONDITION3 = "快递已到达A省分拣中心";
	private final String CONDITION4 = "快递已派送至A省分拣中心";
	private final String CONDITION5 = "快递已派送至A区县营业点";
	private final String CONDITION6 = "快递已完成";
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
		searchResult_status1 = (TextView) findViewById(R.id.searchResult_status1);
		searchResult_status2 = (TextView) findViewById(R.id.searchResult_status2);
		searchResult_status3 = (TextView) findViewById(R.id.searchResult_status3);
		searchResult_status4 = (TextView) findViewById(R.id.searchResult_status4);
		searchResult_status5 = (TextView) findViewById(R.id.searchResult_status5);
		searchResult_status6 = (TextView) findViewById(R.id.searchResult_status6);
		searchResult_receiverName = (TextView) findViewById(R.id.searchResult_receiverName);
		searchResult_receiverProvince = (TextView) findViewById(R.id.searchResult_receiverProvince);
		searchResult_receiverCity = (TextView) findViewById(R.id.searchResult_receiverCity);
		searchResult_receiverDistcit = (TextView) findViewById(R.id.searchResult_receiverDistcit);
		searchResult_receiverAddress = (TextView) findViewById(R.id.searchResult_receiverAddress);
		searchResult_receiverPhone = (TextView) findViewById(R.id.searchResult_receiverPhone);
		searchResult_senderName = (TextView) findViewById(R.id.searchResult_senderName);
		searchResult_senderProvince = (TextView) findViewById(R.id.searchResult_senderProvince);
		searchResult_senderCity = (TextView) findViewById(R.id.searchResult_senderCity);
		searchResult_senderDistrict = (TextView) findViewById(R.id.searchResult_senderDistrict);
		searchResult_senderAddress = (TextView) findViewById(R.id.searchResult_senderAddress);
		searchResult_senderPhone = (TextView) findViewById(R.id.searchResult_senderPhone);
		searchResult_return = (Button) findViewById(R.id.searchResult_return);
		Intent intent = getIntent();
		String goodsId = intent.getStringExtra("searchGoodsId");
		Log.d("111111111111111111111111", goodsId);
		json.setParameter("searchGoodsId", goodsId);
		json.processURL("searchGoodsByID");

		// Goods goods = intent.getParcelableExtra("getGoodsByID");
		// List<GoodsStatus> statuslist = (List<GoodsStatus>)
		// getIntent().getSerializableExtra("statuslist");
		// String[] s = new String[6];
		// int i = 0;
		// for (GoodsStatus n : statuslist) {
		// switch (n.getConditions().getConditionId()) {
		// case "1":
		// s[i] = CONDITION1 + ",单号：" + n.getGoods().getGoodsId();
		// break;
		// case "2":
		// s[i] = CONDITION2.replaceAll("A", n.getGoods().getSenderDistrict());
		// break;
		// case "3":
		// s[i] = CONDITION3.replaceAll("A", n.getGoods().getSenderProvince());
		// break;
		// case "4":
		// s[i] = CONDITION4.replaceAll("A",
		// n.getGoods().getReceiverProvince());
		// break;
		// case "5":
		// s[i] = CONDITION5.replaceAll("A",
		// n.getGoods().getReceiverDistrict());
		// break;
		// case "6":
		// s[i] = CONDITION6;
		// break;
		// }
		// i++;
		// }
		// for (String k : s) {
		// if (k == null) {
		// k = " ";
		// }
		// }
		// searchResult_status1.setText(s[0]);
		// searchResult_status2.setText(s[1]);
		// searchResult_status3.setText(s[2]);
		// searchResult_status4.setText(s[3]);
		// searchResult_status5.setText(s[4]);
		// searchResult_status6.setText(s[5]);
		//
		searchResult_goodsId.setText("单号：" + json.getJsonData("goodsId").toString());
		searchResult_receiverName.setText("收件人姓名：" + json.getJsonData("receiverName").toString());
		searchResult_receiverProvince.setText("收件人省份:" + json.getJsonData("receiverProvince").toString());
		searchResult_receiverCity.setText("收件人城市：" + json.getJsonData("receiverCity").toString());
		searchResult_receiverDistcit.setText("收件人区县：" + json.getJsonData("receiverDistrict").toString());
		searchResult_receiverAddress.setText("收件人地址：" + json.getJsonData("receiverAddress").toString());
		searchResult_receiverPhone.setText("收件人电话：" + json.getJsonData("receiverPhone").toString());
		searchResult_senderName.setText("寄件人姓名：" + json.getJsonData("senderName").toString());
		searchResult_senderProvince.setText("寄件人省份：" + json.getJsonData("senderProvince").toString());
		searchResult_senderCity.setText("寄件人城市：" + json.getJsonData("senderCity").toString());
		searchResult_senderAddress.setText("寄件人地址：" + json.getJsonData("senderAddress").toString());
		searchResult_senderPhone.setText("寄件人电话：" + json.getJsonData("senderPhone").toString());
		searchResult_senderDistrict.setText("寄件人区县：" + json.getJsonData("senderDistrict").toString());
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
