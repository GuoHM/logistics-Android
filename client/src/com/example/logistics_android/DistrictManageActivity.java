package com.example.logistics_android;

import java.io.IOException;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class DistrictManageActivity extends Activity {

	private EditText provincename;
	private EditText cityname;
	private EditText districtname;
	private EditText districtpw;
	private EditText districtid;

	private TextView displayView;
	private JsonHelper json;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.json = new JsonHelper();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_manage);
        
        provincename = (EditText)findViewById(R.id.provincename);
        cityname = (EditText)findViewById(R.id.cityname);
        districtname = (EditText)findViewById(R.id.districtname);
        districtpw = (EditText)findViewById(R.id.districtpw);
        provincename = (EditText)findViewById(R.id.provincename);
        districtid = (EditText)findViewById(R.id.districtid);      
        
        
        Button addprovince = (Button)findViewById(R.id.addprovince);
        Button queryAllButton = (Button)findViewById(R.id.query_all);      
        Button clearButton = (Button)findViewById(R.id.clear);
        
        Button queryButton = (Button)findViewById(R.id.query);
        Button deleteButton = (Button)findViewById(R.id.delete);
        
        
        addprovince.setOnClickListener(addButtonListener); 
        queryAllButton.setOnClickListener(queryAllButtonListener);     
        clearButton.setOnClickListener(clearButtonListener);   
        
        queryButton.setOnClickListener(queryButtonListener);
        deleteButton.setOnClickListener(deleteButtonListener);
        
    }
    
    OnClickListener addButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			 String province=provincename.getText().toString();
			 String city=cityname.getText().toString();
			 String district=districtname.getText().toString();
			 String newpassword=districtpw.getText().toString();
			
			    json.setParameter("province", province);
				json.setParameter("newpassword", newpassword);
				json.setParameter("city", city);
				json.setParameter("district", district);
				try {
					json.processURL("addDistrict");
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
    };

    
    OnClickListener queryAllButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
		try {
			json.processURL("getProvinceList");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    };
    
    OnClickListener clearButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			displayView.setText("");
		}	
    };
    

    OnClickListener queryButtonListener = new OnClickListener() {//显示全部省分拣中心信息
		@Override
		public void onClick(View v) {
			try {
				json.processURL("getProvinceList");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
    };
    
    OnClickListener deleteButtonListener = new OnClickListener() {//根基id删除省分拣中心
		@Override
		public void onClick(View v) {
			int centerId = Integer.parseInt(districtid.getText().toString());
			json.setParameter("centerId", centerId);
			try {
				json.processURL("deleteProvince");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
    };

}