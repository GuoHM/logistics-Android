package com.example.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;

public class JsonHelper {
	private static final String SERVER_URL="http://10.0.2.2:8080/logistics-system-android/";
	private Map<String,Object> data=new HashMap<String,Object>();
	private HttpGet request;
	private HttpResponse response;
	private HttpClient httpclient = new DefaultHttpClient();

	public JsonHelper() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites()
				.detectNetwork().penaltyLog().build());
		StrictMode.setVmPolicy(
				new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());
	}
	
	public void setParameter(String key,Object value){
		data.put(key, value);
	}
	
	public void processURL(String actionName){
		String processURL=SERVER_URL+actionName+"?";
		Iterator<Entry<String, Object>> iter = this.data.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>)iter.next();
			processURL+=entry.getKey()+"="+entry.getValue()+"&";
		}
		
		Log.d("Ô¶³ÌURL", processURL);
		this.request = new HttpGet(processURL);
		this.request.addHeader("Accept", "text/json");
	}
	
	public Object getJsonData(String key) throws ClientProtocolException, IOException, JSONException{
		this.response = this.httpclient.execute(request);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity, "UTF-8");
		if(json!=null){
			JSONObject jsonObject = new JSONObject(JSONTokener(json));
			return jsonObject.get(key);
		}
		return null;
	}
	
	private String JSONTokener(String in) {
		// consume an optional byte order mark (BOM) if it exists
		if (in != null && in.startsWith("\ufeff")) {
			in = in.substring(1);
		}
		return in;
	}
	
	

}
