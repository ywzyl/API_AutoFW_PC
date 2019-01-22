package com.dje.tests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.dje.base.TestBase;
import com.dje.restclient.RestClient_log_optimize;
import com.dje.util.TestUtil;

public class GetApiTest_log_optimize extends TestBase{
	TestBase testBase;
	String url;
	String host;
	RestClient_log_optimize restClient;
	CloseableHttpResponse closeableHttpResponse;
	final static Logger log=Logger.getLogger(GetApiTest_log_optimize.class);
	
	@BeforeClass
	public void setUp() {
		testBase=new TestBase();
		host=prop.getProperty("HOST");
		url=host+"/api/users?page=2";
	}
	
	@Test
	public void testGet() throws ParseException, IOException {
		log.info("��ʼִ������.");
		restClient=new RestClient_log_optimize();
		closeableHttpResponse=restClient.get(url);
		//����״̬���ǲ���200
		log.info("������Ӧ״̬���Ƿ���200");
		int statusCode=restClient.getCode(closeableHttpResponse);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"response status code is not 200");
		
		JSONObject responseJson=restClient.getResponseJson(closeableHttpResponse);
		//json���ݽ���
		String s=TestUtil.getValueByJPath(responseJson, "data[0]/first_name");
		log.info("ִ��JSON������������������"+s);
		log.info("�ӿ�������Ӧ����");
		Assert.assertEquals(s, "Eve","first name is not Eve");
		log.info("����ִ�н���");
	}
}
