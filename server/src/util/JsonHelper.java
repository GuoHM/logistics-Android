package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public class JsonHelper {
	public Map<String, Object> data;
	private HttpServletResponse response;

	public JsonHelper(HttpServletResponse response){
		this.response=response;
		this.response.setContentType("text/json;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
	}
	
	public void put(String name,Object object){
		this.data = new HashMap<String , Object>();
		this.data.put(name, object);
	}
	
	public void output() throws IOException{
		byte[] jsonBytes = this.data.toString().getBytes("utf-8");
		this.response.setContentLength(jsonBytes.length);
		this.response.getOutputStream().write(jsonBytes);
		this.response.getOutputStream().flush();
		this.response.getOutputStream().close();
	}

}
