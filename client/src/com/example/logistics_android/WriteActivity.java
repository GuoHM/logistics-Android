package com.example.logistics_android;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import com.example.util.JsonHelper;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


public class WriteActivity extends Activity {//填写订单
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
	private Button ok;
	private JsonHelper json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.json = new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write);
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
		//	goodsIdField = json.getJsonData(key);
		goodsIdField = (TextView) findViewById(R.id.goodsIdField);
		goodsIdField.setText(getGoodsId());
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
		ok = (Button) findViewById(R.id.write_ok);
	}

	private void setListener() {
		ok.setOnClickListener(new OnClickListener() {

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
					writeService(goodsId, senderName, senderPhone, senderProvince, senderCity
							, senderDistrict, senderAddress, receiverName, receiverPhone, receiverProvince,
							receiverCity, receiverDistrict, receiverAddress);
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

	private void writeService(String goodsId,String senderName,String senderPhone,String senderProvince,String senderCity
			,String senderDistrict,String senderAddress,String receiverName,String receiverPhone,String receiverProvince,
			String receiverCity,String receiverDistrict,String receiverAddress)
					throws ClientProtocolException, IOException, JSONException {
		json=new JsonHelper();
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
		json.processURL("addGoods");	
		Integer isSuccess=(Integer) json.getJsonData("success");
		if(isSuccess!=0){
			AlertDialog.Builder builder = new Builder(WriteActivity.this);
			builder.setTitle("成功").setMessage("提交成功").setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).create().show();
		}
	}
	
	@SuppressLint("SimpleDateFormat")
	private String getGoodsId(){
		SimpleDateFormat f = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        String s = f.format(date);
        s += (int) (Math.random() * 10) + "";
        s += (int) (Math.random() * 10) + "";
        s += (int) (Math.random() * 10) + "";
        s += (int) (Math.random() * 10) + "";
        return s;
	}
}
