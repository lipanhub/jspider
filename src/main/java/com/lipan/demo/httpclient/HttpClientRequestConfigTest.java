package com.lipan.demo.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientRequestConfigTest {

	public static void main(String[] args) {
		// 创建HttpClient对象，相当于浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建httpGet对象，相当于输入请求地址
		HttpGet httpGet = new HttpGet("https://www.baidu.com/");

		// 创建连接请求参数
		RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间
				.setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
				.setSocketTimeout(10 * 1000) // 数据传输的最长时间
				.build();

		// 把请求参数设置到请求对象httpGet
		httpGet.setConfig(config);

		CloseableHttpResponse response = null;
		try {
			// 使用HttpClient发起请求，相当于浏览器按回车，返回response
			response = httpClient.execute(httpGet);

			// 判断状态200表示正常请求
			if (response.getStatusLine().getStatusCode() == 200) {
				String html = EntityUtils.toString(response.getEntity(), "UTF-8");
				// 获取内容
				System.out.println("请求成功，打印的数据是：" + html.length());
			}

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
