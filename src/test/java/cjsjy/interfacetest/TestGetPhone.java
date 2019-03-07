package cjsjy.interfacetest;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.GetlClient;
import com.test.utils.JSONParser;

import java.io.IOException;

import org.apache.http.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class TestGetPhone {
	GetlClient client;
	JSONObject responseBody;
	JSONParser jParser;
	int responseCode;
	String city;
	String province;
	String url = "https://api.apishop.net/communication/phone/getLocationByPhoneNum?apiKey=rdSgCD95612a799e375adbb2f99527894b387d8c0d7e359&phoneNum=1778635";

	@Test
	public void TestGetRequest() {
		// 断言反馈中城市是否正确
		Assert.assertEquals(city, "武汉");
		// 断言反馈中省份是否正确
		Assert.assertEquals(province, "湖北");
		// 断言反馈中的状态码是否正确
		Assert.assertEquals(responseCode, 200);
	}

	@BeforeClass
	public void beforeClass() throws ParseException, IOException {
		// 发送请求，获取反馈
		client = new GetlClient();
		client.getResponse(url);
		responseBody = client.getBodyInJSON();
		responseCode = client.getCodeInNumber();

		// 调用JSONParser获取反馈中的城市信息
		jParser = new JSONParser();
		city = jParser.getCity(responseBody);
		province = jParser.getProvince(responseBody);
	}

}