package coder.aihui.data.bean;

import android.annotation.SuppressLint;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyEntity implements Serializable{
	
	@SuppressLint("SimpleDateFormat")
	public Date getDate(String str) throws ParseException {
    	if (str == null || "".equals(str)) {
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ic_password1:ss");
    	return sdf.parse(str);
    }
	
	public Integer getInt(String str) {
		if (str == null || "".equals(str)) {
    		return null;
    	}
		return Integer.parseInt(str);
	}
	
	public Long getLong(String str) {
		if (str == null || "".equals(str)) {
    		return null;
    	}
		return Long.parseLong(str);
		
	}
	
	public Double getDouble(String str) {
		if (str == null || "".equals(str)) {
    		return null;
    	}
		return Double.parseDouble(str);
		
	}

	public Object parseNullToString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj;

	}

	//需要自动上传的子类，要重新该方法
	public JSONObject getUploadJson() {
		return null;
	}
	
}
