package com.example.logistics_android;

import java.util.List;

import com.example.bean.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchResultActivity extends Activity {
	private EditText searchResult_goodsId = (EditText) findViewById(R.id.searchResult_goodsId);
	private EditText searchResult_status1 = (EditText) findViewById(R.id.searchResult_status1);
	private EditText searchResult_status2 = (EditText) findViewById(R.id.searchResult_status2);
	private EditText searchResult_status3 = (EditText) findViewById(R.id.searchResult_status3);
	private EditText searchResult_status4 = (EditText) findViewById(R.id.searchResult_status4);
	private EditText searchResult_status5 = (EditText) findViewById(R.id.searchResult_status5);
	private EditText searchResult_status6 = (EditText) findViewById(R.id.searchResult_status6);
	private EditText searchResult_receiverName = (EditText) findViewById(R.id.searchResult_receiverName);
	private EditText searchResult_receiverProvince = (EditText) findViewById(R.id.searchResult_receiverProvince);
	private EditText searchResult_receiverCity = (EditText) findViewById(R.id.searchResult_receiverCity);
	private EditText searchResult_receiverDistcit = (EditText) findViewById(R.id.searchResult_receiverDistcit);
	private EditText searchResult_receiverAddress = (EditText) findViewById(R.id.searchResult_receiverAddress);
	private EditText searchResult_receiverPhone = (EditText) findViewById(R.id.searchResult_receiverPhone);
	private EditText searchResult_senderName = (EditText) findViewById(R.id.searchResult_senderName);
	private EditText searchResult_senderProvince = (EditText) findViewById(R.id.searchResult_senderProvince);
	private EditText searchResult_senderCity = (EditText) findViewById(R.id.searchResult_senderCity);
	private EditText searchResult_senderDistrict = (EditText) findViewById(R.id.searchResult_senderDistrict);
	private EditText searchResult_senderAddress = (EditText) findViewById(R.id.searchResult_senderAddress);
	private EditText searchResult_senderPhone = (EditText) findViewById(R.id.searchResult_senderPhone);
	private Button searchResult_return = (Button) findViewById(R.id.searchResult_return);
	private final String CONDITION1 = "订单已提交";
	private final String CONDITION2 = "快递已到达A区县营业点";
	private final String CONDITION3 = "快递已到达A省分拣中心";
	private final String CONDITION4 = "快递已派送至A省分拣中心";
	private final String CONDITION5 = "快递已派送至A区县营业点";
	private final String CONDITION6 = "快递已完成";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		initView();
		setListener();
	}

	private void initView() {
		Intent intent = getIntent();
		Goods goods = intent.getParcelableExtra("getGoodsByID");
		List<GoodsStatus> statuslist = (List<GoodsStatus>) getIntent().getSerializableExtra("statuslist");
		String[] s = new String[6];
		int i = 0;
		for (GoodsStatus n : statuslist) {
			switch (n.getConditions().getConditionId()) {
			case "1":
				s[i] = CONDITION1 + ",单号：" + n.getGoods().getGoodsId();
				break;
			case "2":
				s[i] = CONDITION2.replaceAll("A", n.getGoods().getSenderDistrict());
				break;
			case "3":
				s[i] = CONDITION3.replaceAll("A", n.getGoods().getSenderProvince());
				break;
			case "4":
				s[i] = CONDITION4.replaceAll("A", n.getGoods().getReceiverProvince());
				break;
			case "5":
				s[i] = CONDITION5.replaceAll("A", n.getGoods().getReceiverDistrict());
				break;
			case "6":
				s[i] = CONDITION6;
				break;
			}
			i++;
		}
		for (String k : s) {
			if (k == null) {
				k = " ";
			}
		}
		searchResult_status1.setText(s[0]);
		searchResult_status2.setText(s[1]);
		searchResult_status3.setText(s[2]);
		searchResult_status4.setText(s[3]);
		searchResult_status5.setText(s[4]);
		searchResult_status6.setText(s[5]);
		searchResult_goodsId.setText(goods.getGoodsId());
		searchResult_receiverName.setText(goods.getReceiverName());
		searchResult_receiverProvince.setText(goods.getReceiverProvince());
		searchResult_receiverCity.setText(goods.getReceiverCity());
		searchResult_receiverDistcit.setText(goods.getReceiverDistrict());
		searchResult_receiverAddress.setText(goods.getReceiverAddress());
		searchResult_receiverPhone.setText(goods.getReceiverPhone());
		searchResult_senderName.setText(goods.getSenderName());
		searchResult_senderProvince.setText(goods.getSenderProvince());
		searchResult_senderCity.setText(goods.getSenderCity());
		searchResult_senderAddress.setText(goods.getSenderAddress());
		searchResult_senderPhone.setText(goods.getSenderPhone());
		searchResult_senderDistrict.setText(goods.getSenderDistrict());
	}

	public void setListener() {
		searchResult_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchResultActivity.this, SearchActivity.class);
				startActivity(intent);
			}

		});
	}
}
