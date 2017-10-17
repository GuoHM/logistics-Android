package com.example.logistics_android;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import com.example.util.JsonHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DistrictMainActivity extends Activity {
	private JsonHelper json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_district);
		final Button btn1=(Button)findViewById(R.id.district_modifyprint);
		final Button btn2=(Button)findViewById(R.id.district_sort);
		final Button btn3=(Button)findViewById(R.id.district_send);
     	btn1.setOnClickListener(new OnClickListener(){//点击修改打印进行跳转
			public void onClick(View view){
				Intent intent=new Intent(DistrictMainActivity.this, SearchActivity.class);
		}
	});
		btn2.setOnClickListener(new OnClickListener(){//添加状态信息，表示分拣成功
			public void onClick(View view){
				try {
					json.processURL("adddistrictListStatus");
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
		btn3.setOnClickListener(new OnClickListener(){//添加状态信息，表示派送成功
			public void onClick(View view){
				try {
					json.processURL("addreceiverDistrictListStatus");
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
		
		
	}

	

}
