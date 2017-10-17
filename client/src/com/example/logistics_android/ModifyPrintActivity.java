package com.example.logistics_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.util.JsonHelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModifyPrintActivity extends Activity {

	private EditText searchResult_goodsId;
	private EditText searchResult_senderName;
	private EditText searchResult_senderPhone;
	private EditText searchResult_senderProvince;
	private EditText searchResult_senderCity;
	private EditText searchResult_senderDistrict;
	private EditText searchResult_senderAddress;
	private EditText searchResult_receiverName;
	private EditText searchResult_receiverPhone;
	private EditText searchResult_receiverProvince;
	private EditText searchResult_receiverCity;
	private EditText searchResult_receiverDistrict;
	private EditText searchResult_receiverAddress;
	private Button save;
	private Button print;
	private JsonHelper json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_print);
		initView();
		setListener();
	}

	private void initView() {
		searchResult_goodsId = (EditText) findViewById(R.id.searchResult_goodsId);
		searchResult_senderName = (EditText) findViewById(R.id.searchResult_senderName);
		searchResult_senderPhone = (EditText) findViewById(R.id.searchResult_senderPhone);
		searchResult_senderProvince = (EditText) findViewById(R.id.searchResult_senderProvince);
		searchResult_senderCity = (EditText) findViewById(R.id.searchResult_senderCity);
		searchResult_senderDistrict = (EditText) findViewById(R.id.searchResult_senderDistrict);
		searchResult_senderAddress = (EditText) findViewById(R.id.searchResult_senderAddress);
		searchResult_receiverName = (EditText) findViewById(R.id.searchResult_receiverName);
		searchResult_receiverPhone = (EditText) findViewById(R.id.searchResult_receiverPhone);
		searchResult_receiverProvince = (EditText) findViewById(R.id.searchResult_receiverProvince);
		searchResult_receiverCity = (EditText) findViewById(R.id.searchResult_receiverCity);
		searchResult_receiverDistrict = (EditText) findViewById(R.id.searchResult_receiverDistrict);
		searchResult_receiverAddress = (EditText) findViewById(R.id.searchResult_receiverAddress);
		save = (Button) findViewById(R.id.save);
		print = (Button) findViewById(R.id.print);
	}

	private void setListener() {
		print.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					json.processURL("addStatus");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String goodsId = searchResult_goodsId.getText().toString();
				String senderName = searchResult_senderName.getText()
						.toString();
				String senderPhone = searchResult_senderPhone.getText()
						.toString();
				String senderProvince = searchResult_senderProvince.getText()
						.toString();
				String senderCity = searchResult_senderCity.getText()
						.toString();
				String senderDistrict = searchResult_senderDistrict.getText()
						.toString();
				String senderAddress = searchResult_senderAddress.getText()
						.toString();

				String receiverName = searchResult_receiverName.getText()
						.toString();
				String receiverPhone = searchResult_receiverPhone.getText()
						.toString();
				String receiverProvince = searchResult_receiverProvince
						.getText().toString();
				String receiverCity = searchResult_receiverCity.getText()
						.toString();
				String receiverDistrict = searchResult_receiverDistrict
						.getText().toString();
				String receiverAddress = searchResult_receiverAddress.getText()
						.toString();
				try {
					writeService(goodsId, senderName, senderPhone,
							senderProvince, senderCity, senderDistrict,
							senderAddress, receiverName, receiverPhone,
							receiverProvince, receiverCity, receiverDistrict,
							receiverAddress);
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

	private void writeService(String goodsId, String senderName,
			String senderPhone, String senderProvince, String senderCity,
			String senderDistrict, String senderAddress, String receiverName,
			String receiverPhone, String receiverProvince, String receiverCity,
			String receiverDistrict, String receiverAddress)
			throws ClientProtocolException, IOException, JSONException {
		json.setParameter("goodsId", goodsId);
		json.setParameter("senderName", senderName);
		json.setParameter("senderPhone", senderPhone);
		json.setParameter("senderProvince", senderProvince);
		json.setParameter("senderCity", senderCity);
		json.setParameter("senderDistrict", senderDistrict);
		json.setParameter("senderAddress", senderAddress);
		json.setParameter("receiverName", receiverName);
		json.setParameter("receiverPhone", receiverPhone);
		json.setParameter("receiverProvince", receiverProvince);
		json.setParameter("receiverCity", receiverCity);
		json.setParameter("receiverDistrict", receiverDistrict);
		json.setParameter("receiverAddress", receiverAddress);
		json.processURL("modifyGoodsinfo");
	}

}
