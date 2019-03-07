package cjsjy.interfacetest;

import org.testng.annotations.Test;
import com.test.client.PostClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class TestPostAddUser {
	PostClient client;
	Map<String, Object> responseBody;
	int responseCode;
	String username;
	String password;
	String pinyin;
	String deptid;
	
	String url = "http://10.6.172.179:8080/pqms/users";

	@Test
	public void testPostRequest() {
		// 断言反馈的状态码是否正确
		Assert.assertEquals(username, "test002");
	}

	@BeforeClass
	public void beforeClass() throws ClientProtocolException, IOException {
		client = new PostClient();

		// 用NameValuePair的list来添加请求主体参数
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", "test002");
		params.put("password", "123");
		params.put("deptid", "6");

		Map<String, Object> map = PostClient.sendPost(url, params);
		System.out.println(map);

		username = (String) map.get("username");
		System.out.println(username);
	}

}