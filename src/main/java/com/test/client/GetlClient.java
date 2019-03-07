package com.test.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class GetlClient {
	// url是我们去进行get请求的地址；
	//String url = "https://api.apishop.net/communication/phone/getLocationByPhoneNum?apiKey=rdSgCD95612a799e375adbb2f99527894b387d8c0d7e359&phoneNum=1778635";
	String url = "https://api.apishop.net/common/weather/getWeatherByPhonePostCode?apiKey=rdSgCD95612a799e375adbb2f99527894b387d8c0d7e359&phoneCode=027";
	// httpClient是用来发送http请求的HttpClient实例；
	CloseableHttpClient httpclient;
	// httpGet是get请求的一个实例；
	HttpGet httpGet;
	// httpResponse用来存储我们接收到的反馈;
	CloseableHttpResponse httpResponse;
	// responseCode用来存储反馈的状态码；
	int responseCode;
	// responseBody用来存储反馈的主体信息,JSON格式；；
	JSONObject responseBody;
	// responseHeader用来存储反馈的头部信息；
	HashMap<String, String> responseHeads;

	// getResponse方法：通过httpclient获取请求的反馈
	public void getResponse(String url) throws ClientProtocolException, IOException {
		// 创建一个httpClient的实例
		httpclient = HttpClients.createDefault();
		// 创建一个httpGet请求实例
		httpGet = new HttpGet(url);
		// 使用httpClient实例发送刚创建的get请求，并用httpResponse将反馈接收
		httpResponse = httpclient.execute(httpGet);
	}

	// getBodyInJSON方法：以JSON格式获取到反馈的主体
	public JSONObject getBodyInJSON() throws ParseException, IOException {
		HttpEntity entity;
		String entityToString;

		// 从反馈中提取出反馈主体
		entity = httpResponse.getEntity();
		// 用EntityUtils工具类将反馈主体处理为字符串形式
		entityToString = EntityUtils.toString(entity);
		System.out.println("This is your entityToString:" + entityToString+"-----001");
		responseBody = JSON.parseObject(entityToString);
		System.out.println("This is your response body:" + responseBody+"-----001");

		return responseBody;
	}

	// getHeaderInHash方法：以哈希图的方式获取到反馈头部
	public HashMap<String, String> getHeaderInHash() {
		Header[] responseHeader;
		// 从反馈中提取出所有头部信息
		responseHeader = httpResponse.getAllHeaders();

		// 用哈希图将反馈头信息以键值对形式保存
		responseHeads = new HashMap<String, String>();

		for (Header header : responseHeader) {
			responseHeads.put(header.getName(), header.getValue());
		}

		System.out.println("This is your response header" + responseHeads+"-----001");

		return responseHeads;
	}

	// getCodeInNumber方法：获取反馈状态码
	public int getCodeInNumber() {
		responseCode = httpResponse.getStatusLine().getStatusCode();

		System.out.println("This is your response code" + responseCode);

		return responseCode;
	}
}