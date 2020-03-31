package com.lipan.demo.jsoup;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupStringTest {

	public static void main(String[] args) throws Exception {
		// 使用工具类，读取文件获取字符串
		String html = FileUtils.readFileToString(new File("C:/Users/tree/Desktop/test.html"), "UTF-8");

		// 使用jsoup解析字符串
		Document dom = Jsoup.parse(html);

		// 获取title标签的内容
		String title = dom.getElementsByTag("title").first().text();
		System.out.println(title);
	}

}
