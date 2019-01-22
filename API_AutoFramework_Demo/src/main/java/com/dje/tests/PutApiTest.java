package com.dje.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dje.base.TestBase;
import com.dje.data.Users;
import com.dje.restclient.RestClient;
import com.dje.util.TestUtil;

public class PutApiTest extends TestBase{
	TestBase testBase;
	String url;
	String host;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeClass
	public void setUp() {
		testBase=new TestBase();
		host=prop.getProperty("HOST");
		url=host+"/api/users/2";
	}
	
	@Test
	public void testPut() throws ParseException, IOException {
		restClient=new RestClient();
		//׼������ͷ��Ϣ
		HashMap<String, String>  headerMap=new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//����ת����Json�ַ���
		Users users=new Users("yw","automation tester");
		String userJsonString=JSON.toJSONString(users);
		closeableHttpResponse=restClient.put(url, userJsonString, headerMap);
		//��֤״̬���ǲ���200
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"response status code is not 200");
		//��֤����Ϊyw��job�ǲ��� automation tester
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity());
		JSONObject responseJson=JSON.parseObject(responseString);
		String jobString=TestUtil.getValueByJPath(responseJson, "job");
		System.out.println(jobString);
		Assert.assertEquals(jobString, "automation tester","job is not same");
	}
}
