package com.dje.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RestClient_log_optimize {
	final static Logger log=Logger.getLogger(RestClient_log_optimize.class);
	/**
	 * ��������ͷ��get������װ
	 * @param url
	 * @return ������Ӧ����
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		//����һ�����Թرյ�httpClient����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//����һ��httpGet���������
		HttpGet httpGet=new HttpGet(url);
		//ִ������
		log.info("��ʼ����get����");
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		log.info("��������ɹ�����ʼ�õ���Ӧ����");
		return httpResponse;
	}
	/**
	 * ������ͷ��Ϣ��get����
	 * @param url
	 * @param headermap����ֵ����ʽ
	 * @return ������Ӧ����
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse get(String url,HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(url);
		//��������ͷ��httpGet����
		for (Map.Entry<String, String> entity : headerMap.entrySet()) {
			httpGet.addHeader(entity.getKey(), entity.getValue());
		}
		log.info("��ʼ���ʹ�����ͷ��get����:");
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		log.info("��������ɹ�����ʼ�õ���Ӧ����");
		return httpResponse;
	}
	/**
	 * ��װpost����
	 * @param url
	 * @param entityString����ʵ������������json����
	 * @param headermap��������ͷ
	 * @return ������Ӧ����
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse post(String url,String entityString,HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);
		//����payload
		httpPost.setEntity(new StringEntity(entityString));
		//��������ͷ��httpPost����
		for (Map.Entry<String, String> entity : headerMap.entrySet()) {
			httpPost.addHeader(entity.getKey(), entity.getValue());
		}
		CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
		log.info("��ʼ����post����");
		return httpResponse;
	}
	/**
	 * ��װ put���󷽷���������post����һ��
	 * @param url
	 * @param entityString�������Ҫ������payload,һ����˵����json��
	 * @param headerMap���������ͷ��Ϣ����ʽ�Ǽ�ֵ�ԣ���������ʹ��hashmap
	 * @return ������Ӧ����
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse put(String url,String entityString,HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPut httpPut=new HttpPut(url);
		//����payload
		httpPut.setEntity(new StringEntity(entityString));
		for (Map.Entry<String, String> entity : headerMap.entrySet()) {
			httpPut.addHeader(entity.getKey(), entity.getValue());
		}
		CloseableHttpResponse httpResponse=httpClient.execute(httpPut);
		log.info("��ʼ����put����");
		return httpResponse;
	}
	/**
	 * ��װ delete���󷽷���������get����һ��
	 * @param url�� �ӿ�url������ַ
	 * @return������һ��response���󣬷�����еõ�״̬���json��������
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpDelete httpDelete=new HttpDelete(url);
		CloseableHttpResponse httpResponse=httpClient.execute(httpDelete);
		log.info("��ʼ����delete����");
		return httpResponse;
	}
	/**
	 * ��ȡ��Ӧ״̬�룬��������TestBase�ж����״̬�볣��ȥ���Զ���ʹ��
	 * @param response
	 * @return ����int����״̬��
	 */
	public int getCode(CloseableHttpResponse httpResponse) {
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		log.info("�������õ���Ӧ״̬��:"+statusCode);
		return statusCode;
	}
	/**
	 * 
	 * @param response, �κ����󷵻ط��ص���Ӧ����
	 * @return�� ������Ӧ���json��ʽ���󣬷����������JSON�������ݽ���
	 * ��������һ����������TestUtil���µ�json���������õ�ĳһ��json�����ֵ
	 * @throws ParseException
	 * @throws IOException
	 */
	public JSONObject getResponseJson(CloseableHttpResponse httpResponse) 
			throws ParseException, IOException {
		log.info("�õ���Ӧ�����String��ʽ");
		String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		JSONObject responseJson=JSON.parseObject(responseString);
		log.info("������Ӧ���ݵ�JSON��ʽ");
		return responseJson;
	}
}