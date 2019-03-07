package com.test.client;

import java.io.IOException;

import java.nio.charset.Charset;


import java.util.Map;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class PostClient {

	private static final Logger logger = LoggerFactory.getLogger(PostClient.class);

	// 1.拿到一个httpclient的对象
	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	public static Map<String, Object> sendPost(String url, Map<String, String> parameterMap) {
		HttpPost httpPost = null;
		try {
			// 2.设置请求发送方式为post
			httpPost = new HttpPost(url);
			// 3. 提交header头信息
			httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
			// 4. 提交请求体,fastjson jar包，map转json
			Object json = JSONObject.toJSON(parameterMap);
			//host方法传递参数
			httpPost.setEntity(new StringEntity(json.toString()));
			// 5.执行请求
			CloseableHttpResponse response = httpclient.execute(httpPost);
			//6.获取返回码
			int code = response.getStatusLine().getStatusCode();
			System.out.println(code);
			// 7.获取返回值
			String body = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
			Map<String, Object> maps = (Map<String, Object>) JSON.parse(body);
			return maps;
		} catch (IOException e) {
			logger.error("httpclient post error", e);
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
		return null;
	}

}
