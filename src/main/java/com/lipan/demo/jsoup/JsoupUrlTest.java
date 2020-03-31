package com.lipan.demo.jsoup;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUrlTest {

	public static void main(String[] args) throws Exception {
		// 解析url地址，第一个参数是需要解析的url，第二个参数是连接的超时时间,单位是毫秒
		Document dom = Jsoup.parse(new URL("https://www.autohome.com.cn/bestauto/1"), 10000);

		// 获取页面信息，输出为html文件
		//FileUtils.writeStringToFile(new File("C:/Users/001/Desktop/test.html"), dom.html(), "UTF-8");

		// 使用dom对象解析页面，获取title标签的内容
		String title = dom.getElementsByTag("title").first().text();

		System.out.println(title);

	}

}
