package com.lipan.demo.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @description:利用httpclient爬取https://www.autohome.com.cn/bestauto
 * @author: lipan
 * @time: 2020/3/28 22:10
 */
public class AutohomeSpider {

	public static void main(String[] args) throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 声明httpGet请求对象
		String url = "https://search.jd.com/Search" +
				"?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2" +
				"&wq=%E6%89%8B%E6%9C%BA&s=56&click=0&page=1";
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");

		// 使用HttpClient发起请求，获取response
		CloseableHttpResponse response = httpClient.execute(httpGet);

		// 判断响应的状态码是否是200
		if (response.getStatusLine().getStatusCode() == 200) {
			// 如果是200表示请求成功，获取响应的数据
			String html = EntityUtils.toString(response.getEntity(), "UTF-8");

			System.out.println(html);
		}
	}

}
