package com.dje.util;

import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestUtil {
	 /**
    *
    * @param responseJson ,����������õ���Ӧ�ַ���ͨ��jsonת����json����
    * @param jpath,���jpathָ�����û���Ҫ��ѯjson�����ֵ��·��д��
    * jpathд��������1) per_page  2)data[1]/first_name ��data��һ��json���飬[1]��ʾ����
    * /first_name ��ʾdata������ĳһ��Ԫ���µ�json���������Ϊfirst_name
    * @return������first_name���json�������ƶ�Ӧ��ֵ
    */
	//1 json��������
	public static String getValueByJPath(JSONObject responseJson,String jpath) {
		Object obj=responseJson;
		for (String s : jpath.split("/")) {
			System.out.println(Arrays.toString((jpath.split("/"))));
			if (!s.isEmpty()) {
				if (!(s.contains("[")||s.contains("]"))) {
					obj=((JSONObject)obj).get(s);
				}else if (s.contains("[")||s.contains("]")) {
					obj=((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).
							get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));	
					System.out.println(obj.toString());
				}
			}			
		}
		return obj.toString();
	}
}
