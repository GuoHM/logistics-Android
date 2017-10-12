package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public class JsonHelper {
	public Map<String, Object> data;
	
	public void init(HttpServletResponse response){
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
	}

	public void put(String name,Object object){
		data = new HashMap<String , Object>();
		data.put(name, object);
	}
	
	public void output(HttpServletResponse response) throws IOException{
		byte[] jsonBytes = this.data.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

}
