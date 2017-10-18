package com.example.logistics_android;

import com.example.util.JsonHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminMainActivity extends Activity {
	private JsonHelper json;
	private Button provinceManage;
	private Button districtManage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_main);
		final Button btn1=(Button)findViewById(R.id.provinceManage);
		final Button btn2=(Button)findViewById(R.id.districtManage);
     	btn1.setOnClickListener(new OnClickListener(){//点击修改打印进行跳转
			public void onClick(View view){
				Intent intent=new Intent(AdminMainActivity.this, ProvinceManageActivity.class);
		}
	});
     	
     	btn2.setOnClickListener(new OnClickListener(){//点击修改打印进行跳转
			public void onClick(View view){
				Intent intent=new Intent(AdminMainActivity.this,DistrictManageActivity.class);
		}
	});
	}
}
