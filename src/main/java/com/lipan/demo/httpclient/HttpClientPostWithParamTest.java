package com.lipan.demo.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientPostWithParamTest {

	public static void main(String[] args) throws Exception {
		// 创建HttpClient对象，相当于浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建httpPost对象，相当于输入请求地址
		HttpPost httpPost = new HttpPost("https://www.oschina.net/");
		// 设置用户代理信息，解决开源中国不允许爬虫访问的问题
		httpPost.setHeader("User-Agent", "");

		// 设置表单数据scope=project&q=HttpClient
		// 创建List集合封装请求参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("scope", "project"));
		params.add(new BasicNameValuePair("q", "HttpClient"));

		// 使用封装请求参数的List集合，创建表单实体对象
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

		// 把表单实体设置到请求httpPost中
		httpPost.setEntity(formEntity);

		CloseableHttpResponse response = null;
		try {
			// 使用HttpClient发起请求，相当于浏览器按回车，返回response
			response = httpClient.execute(httpPost);

			// 判断状态200表示正常请求
			if (response.getStatusLine().getStatusCode() == 200) {
				String html = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println("请求成功，打印的数据是：" + html.length());
			}
			// 获取内容

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭response连接
				if (response != null) {
					response.close();
				}
				// 关闭HttpClient
				httpClient.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
