package com.test.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class JSONParserExcel {
	public boolean isResponseCorrect(JSONObject jo, String checkpoint, String passValue) {
		// 用jsonpath处理json，获取result中特定键值
		ReadContext context = JsonPath.parse(jo);
		JSONArray result = context.read("$.result.." + checkpoint);
		String resultString = result.get(0).toString();
		if (resultString.equals(passValue)) {
			return true;
		} else {
			return false;
		}
	}

}
