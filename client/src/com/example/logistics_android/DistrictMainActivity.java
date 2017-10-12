package com.example.logistics_android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class DistrictMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_district);
		final Button btn1=(Button)findViewById(R.id.Button01);
		final Button btn2=(Button)findViewById(R.id.Button02);
		final Button btn3=(Button)findViewById(R.id.Button03);
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Intent intent=new Intent(DistrictMainActivity.this, MPActivity.class);
			}
		});
/*		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Intent intent=new Intent(DistrictMainActivity.this, MPActivity.class);
			}
		});
		btn3.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Intent intent=new Intent(DistrictMainActivity.this, MPActivity.class);
			}
		});
		
		*/
	}

	

}
