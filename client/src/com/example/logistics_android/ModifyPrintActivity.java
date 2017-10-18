package com.example.logistics_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.util.JsonHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifyPrintActivity extends Activity {

	private TextView goodsIdField;
	private EditText senderNameField;
	private EditText senderPhoneField;
	private EditText senderProvinceField;
	private EditText senderCityField;
	private EditText senderDistrictField;
	private EditText senderAddressField;
	private EditText receiverNameField;
	private EditText receiverPhoneField;
	private EditText receiverProvinceField;
	private EditText receiverCityField;
	private EditText receiverDistrictField;
	private EditText receiverAddressField;
	private Button save;
	private Button print;
	private JsonHelper json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_print);
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
		
		goodsIdField = (TextView) findViewById(R.id.goodsIdField);
		senderNameField = (EditText) findViewById(R.id.senderNameField);
		senderPhoneField = (EditText) findViewById(R.id.senderPhoneField);
		senderProvinceField = (EditText) findViewById(R.id.senderProvinceField);
		senderCityField = (EditText) findViewById(R.id.senderCityField);
		senderDistrictField = (EditText) findViewById(R.id.senderDistrictField);
		senderAddressField = (EditText) findViewById(R.id.senderAddressField);
		receiverNameField = (EditText) findViewById(R.id.receiverNameField);
		receiverPhoneField = (EditText) findViewById(R.id.receiverPhoneField);
		receiverProvinceField = (EditText) findViewById(R.id.receiverProvinceField);
		receiverCityField = (EditText) findViewById(R.id.receiverCityField);
		receiverDistrictField = (EditText) findViewById(R.id.receiverDistrictField);
		receiverAddressField = (EditText) findViewById(R.id.receiverAddressField);
		save = (Button) findViewById(R.id.save);
		print = (Button) findViewById(R.id.print);
		Intent intent = getIntent();
		String goodsId = intent.getStringExtra("currentGoods");
		
		json.setParameter("currentGoods", goodsId);
		json.processURL("viewGoods");
		goodsIdField .setText(json.getJsonData("goodsId").toString());
		senderNameField.setText(json.getJsonData("receiverName").toString());
		senderPhoneField.setText(json.getJsonData("receiverPhone").toString());
		senderProvinceField .setText("�ռ���ʡ��:" + json.getJsonData("receiverProvince").toString());
		senderCityField .setText("�ռ��˳��У�" + json.getJsonData("receiverCity").toString());
		senderDistrictField.setText("�ռ������أ�" + json.getJsonData("receiverDistrict").toString());
		senderAddressField .setText("�ռ��˵�ַ��" + json.getJsonData("receiverAddress").toString());
		receiverNameField .setText("�ļ���������" + json.getJsonData("senderName").toString());
		receiverPhoneField .setText("�ļ��˵绰��" + json.getJsonData("senderPhone").toString());
		receiverProvinceField .setText("�ļ���ʡ�ݣ�" + json.getJsonData("senderProvince").toString());
		receiverDistrictField .setText("�ļ������أ�" + json.getJsonData("senderDistrict").toString());
		receiverCityField .setText("�ļ��˳��У�" + json.getJsonData("senderCity").toString());
		receiverAddressField.setText("�ļ��˵�ַ��" + json.getJsonData("senderAddress").toString());
	
		
		
		
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
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String goodsId = goodsIdField.getText().toString();
				String senderName = senderNameField.getText().toString();
				String senderPhone = senderPhoneField.getText().toString();
				String senderProvince = senderProvinceField.getText().toString();
				String senderCity = senderCityField.getText().toString();
				String senderDistrict = senderDistrictField.getText().toString();
				String senderAddress = senderAddressField.getText().toString();

				String receiverName = receiverNameField.getText().toString();
				String receiverPhone = receiverPhoneField.getText().toString();
				String receiverProvince = receiverProvinceField.getText().toString();
				String receiverCity = receiverCityField.getText().toString();
				String receiverDistrict = receiverDistrictField.getText().toString();
				String receiverAddress = receiverAddressField.getText().toString();
				try {
					modifyGoodsinfoService(goodsId, senderName, senderPhone,
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

	private void modifyGoodsinfoService(String goodsId, String senderName,
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
