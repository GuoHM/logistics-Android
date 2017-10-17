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
	private final String CONDITION1 = "�������ύ";
	private final String CONDITION2 = "����ѵ���A����Ӫҵ��";
	private final String CONDITION3 = "����ѵ���Aʡ�ּ�����";
	private final String CONDITION4 = "�����������Aʡ�ּ�����";
	private final String CONDITION5 = "�����������A����Ӫҵ��";
	private final String CONDITION6 = "��������";
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
		// s[i] = CONDITION1 + ",���ţ�" + n.getGoods().getGoodsId();
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
		searchResult_goodsId.setText("���ţ�" + json.getJsonData("goodsId").toString());
		searchResult_receiverName.setText("�ռ���������" + json.getJsonData("receiverName").toString());
		searchResult_receiverProvince.setText("�ռ���ʡ��:" + json.getJsonData("receiverProvince").toString());
		searchResult_receiverCity.setText("�ռ��˳��У�" + json.getJsonData("receiverCity").toString());
		searchResult_receiverDistcit.setText("�ռ������أ�" + json.getJsonData("receiverDistrict").toString());
		searchResult_receiverAddress.setText("�ռ��˵�ַ��" + json.getJsonData("receiverAddress").toString());
		searchResult_receiverPhone.setText("�ռ��˵绰��" + json.getJsonData("receiverPhone").toString());
		searchResult_senderName.setText("�ļ���������" + json.getJsonData("senderName").toString());
		searchResult_senderProvince.setText("�ļ���ʡ�ݣ�" + json.getJsonData("senderProvince").toString());
		searchResult_senderCity.setText("�ļ��˳��У�" + json.getJsonData("senderCity").toString());
		searchResult_senderAddress.setText("�ļ��˵�ַ��" + json.getJsonData("senderAddress").toString());
		searchResult_senderPhone.setText("�ļ��˵绰��" + json.getJsonData("senderPhone").toString());
		searchResult_senderDistrict.setText("�ļ������أ�" + json.getJsonData("senderDistrict").toString());
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
