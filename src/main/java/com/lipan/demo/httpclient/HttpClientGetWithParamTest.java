package com.lipan.demo.httpclient;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientGetWithParamTest {

	public static void main(String[] args) throws Exception {
		// 创建HttpClient对象，相当于浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建httpGet对象，相当于输入请求地址
		// HttpGet httpGet = new
		// HttpGet("https://www.baidu.com/s?wd=httpclient");

		// 创建URIBuilder设置参数
		URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com/s").setParameter("wd", "httpclient");

		URI uri = uriBuilder.build();

		System.out.println(uri);

		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = null;
		try {
			// 使用HttpClient发起请求，相当于浏览器按回车，返回response
			response = httpClient.execute(httpGet);

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
