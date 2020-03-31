package com.lipan.demo.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientPoolManagerTest {

	public static void main(String[] args) throws Exception {
		// 创建连接池管理器
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

		// 设置最大连接数
		cm.setMaxTotal(200);

		// 设置每个主机的最大连接数，访问每一个网站指定的连接数，不会影响其他网站的访问
		cm.setDefaultMaxPerRoute(20);

		// 使用连接池管理器获取连接并发起请求
		get(cm);
		get(cm);

	}

	private static void get(PoolingHttpClientConnectionManager cm) throws Exception {
		// 使用连接池管理器获取连接
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		// 打印连接
		System.out.println(httpClient);

		HttpGet httpGet = new HttpGet("https://www.baidu.com");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		System.out.println(response.getStatusLine().getStatusCode());

	}

}
