package com.dje.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	//1. Get 请求方法
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		//创建一个可关闭的HttpClient对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//创建一个HttpGet的请求对象
		HttpGet httpGet=new HttpGet(url);
		//执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		return httpResponse;
	}
	//2. Get 请求方法（带请求头信息）
	public CloseableHttpResponse get(String url,HashMap<String, String> headermap) 
			throws ClientProtocolException, IOException {
		//创建一个可关闭的HttpClient对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		 //创建一个HttpGet的请求对象
		HttpGet httpGet=new HttpGet(url);
		//加载请求头到httpget对象
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		//执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		return httpResponse;
	}	 
	//3. POST方法
	public CloseableHttpResponse post(String url,String entityString,HashMap<String, String> headermap) 
			throws ClientProtocolException, IOException {
		//创建一个可关闭的HttpClient对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		headermap.put("Content-Type", "application/json");
		//创建一个HttpPost的请求对象
		HttpPost httpPost=new HttpPost(url);
		//设置payload
		httpPost.setEntity(new StringEntity(entityString));
		//加载请求头到httppost对象
		for (Map.Entry<String, String>  entry : headermap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		//发送post请求
		CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
		return httpResponse;
	}
	//4. Put方法
	public CloseableHttpResponse put(String url,String entity,HashMap<String, String> headerMap) 
			throws ClientProtocolException, IOException  {
		//创建一个可关闭的HttpClient对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		headerMap.put("Content-Type", "application/json");
		//创建一个HttpPut的请求对象
		HttpPut httpPut=new HttpPut(url);
		//设置payload
		httpPut.setEntity(new StringEntity(entity));
		//加载请求头到httpput对象
		for(Map.Entry<String, String> entry:headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		//发送put请求
		CloseableHttpResponse httpResponse=httpClient.execute(httpPut);
		return httpResponse;
	}
	//5. Delete方法
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {	
		//创建一个可关闭的HttpClient对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//创建一个HttpDelete的请求对象
		HttpDelete httpDelete=new HttpDelete(url);
		//发送delete请求
		CloseableHttpResponse httpResponse=httpClient.execute(httpDelete);
		return httpResponse;
	}
}
