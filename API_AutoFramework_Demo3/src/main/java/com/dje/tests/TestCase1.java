package com.dje.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.dje.base.TestBase;
import com.dje.parameters.PostParameters;
import com.dje.restclient.RestClient;
import com.dje.util.TestUtil;
import static com.dje.util.TestUtil.dtt;

public class TestCase1 extends TestBase{
	TestBase testBase;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String host;
    String testCaseExcel;
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        restClient = new RestClient();
        postHeader.put("Content-Type","application/json");
        //载入配置文件，接口endpoint
        host = prop.getProperty("HOST");
        //载入配置文件，post接口参数
        testCaseExcel=prop.getProperty("postdata");
    }
 
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(testCaseExcel,0);
 
    }
 
    @Test(dataProvider = "postData")
    public void login(String loginUrl,String username, String passWord) throws Exception {
        //使用构造函数将传入的用户名密码初始化成登录请求参数
        PostParameters loginParameters = new PostParameters(username,passWord);
        //将登录请求对象序列化成json对象
        String userJsonString = JSON.toJSONString(loginParameters);
        //发送登录请求
        closeableHttpResponse = restClient.post(host+loginUrl,userJsonString,postHeader);
        //从返回结果中获取状态码
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);
 
    }
    @BeforeClass
    public void endTest(){
        System.out.print("测试结束");
    }
}
