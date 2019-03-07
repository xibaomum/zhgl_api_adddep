package cjsjy.interfacetest;

import org.testng.annotations.Test;
import com.test.client.PostClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class TestPostAddDep {
	PostClient client;
	Map<String, Object> responseBody;
	int responseCode;
	String deptname;
	String deptleader;
	String url = "http://10.6.172.179:8080/pqms/depts";
	
	@Test
	public void testPostRequest() {
		// 断言反馈的状态码是否正确
		Assert.assertEquals(deptname, "test03");
	}

	@BeforeClass
	public void beforeClass() throws ClientProtocolException, IOException {
		client = new PostClient();

		// 用NameValuePair的list来添加请求主体参数
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("deptname", "test03");
		params.put("deptleader", "admin");

		responseBody = PostClient.sendPost(url, params);
		System.out.println(responseBody);

		deptname = (String) responseBody.get("deptname");
		System.out.println(deptname);
	}

}