package com.example.logistics_android;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.bean.*;
import com.example.util.JsonHelper;

import android.app.Activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SearchActivity extends Activity {
	private EditText goodsIdField;
	private Button confirm;
	private JsonHelper json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.json=new JsonHelper();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initView();
		setListener();
	}
	
	private void initView(){
		goodsIdField = (EditText)findViewById(R.id.goodsIdField);
		confirm = (Button) findViewById(R.id.confirm);
	}
	
	private void setListener() {
		confirm.setOnClickListener(new OnClickListener(){

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
	
	private void searchService(String goodsId) throws ClientProtocolException, IOException, JSONException{
//		json.setParameter("searchGoodsId", goodsId);
//		json.processURL("searchGoodsByID");
//		if (json.getJsonData("error")!=null){
//			AlertDialog.Builder builder = new Builder(SearchActivity.this);
//			builder.setTitle("错误").setMessage("无此订单").setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			}).create().show();
//		} else {
			Intent intent = new Intent();
//			Goods goods = json.getJsonData(Goods.class);
//			Log.d("11111111111111111111111",goods.toString());
//			List<GoodsStatus> statuslist = (List<GoodsStatus>) json.getJsonData("statuslist");
//			
			intent.putExtra("searchGoodsId", goodsId);
//			intent.putExtra("statuslist", (Serializable)statuslist);
			intent.setClass(SearchActivity.this, SearchResultActivity.class);
			startActivity(intent);
		}
		
//	}



}
