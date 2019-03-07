package com.test.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONParser {
	JSONObject internalJSON;

	public String getCity(JSONObject jo) {
		String city = "";

		try {
			// 先获取反馈中的result这一个内部JSON对象
			JSONObject internalJSON = jo.getJSONObject("result");
			// 再根据键名查找键值
			city = internalJSON.getString("city");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public String getProvince(JSONObject jo) {
		String province = "";

		try {
			// 先获取反馈中的result这个一个内部JSON对象
			JSONObject internalJSON = jo.getJSONObject("result");
			// 再根据键名查找键值
			province = internalJSON.getString("province");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return province;
	}

	public String getProvOil(JSONObject jo) {
		String prov = "";

		try {
			// 注意：list中的内容带有中括号[]，所以要转化为JSONArray类型的对象
			JSONArray listvalue = jo.getJSONObject("result").getJSONArray("list");
			System.out.println(listvalue + "-----lalalal123");
			for (int i = 0; i < listvalue.size(); i++) {
				prov = listvalue.getJSONObject(i).getString("prov");
				System.out.println(prov + "-----hahahah");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prov;
	}

	public int countOil(JSONObject obj) {
		int count = 0;

		try {
			// 注意：list中的内容带有中括号[]，所以要转化为JSONArray类型的对象
			count = obj.getJSONObject("result").getJSONArray("list").size();
			System.out.println(count + "-----kakakakak");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public String getDeptname(JSONObject jo) {
		String deptname = "";

		try {
			// 先获取反馈中的result这一个内部JSON对象
			deptname = jo.getString("deptname");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptname;
	}
}