package com.example.logistics_android;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabHost = getTabHost();
		
		tabHost.addTab(tabHost.newTabSpec("LOGIN").setIndicator("µÇÂ¼")
				.setContent(new Intent().setClass(this, LoginActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("SEND").setIndicator("¼Ä¼þ")
				.setContent(new Intent().setClass(this, SendActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("SEARCH").setIndicator("²é¼þ")
				.setContent(new Intent().setClass(this, SearchActivity.class)));

	}

}
