package com.lipan.demo.jsoup;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupFileTest {

	public static void main(String[] args) throws Exception {
		// 使用jsoup直接解析文件
		Document dom = Jsoup.parse(new File("C:/Users/tree/Desktop/test.html"), "UTF-8");

		// 获取title标签的内容
		String title = dom.getElementsByTag("title").first().text();
		System.out.println(title);

		String str = "";
		// 元素获取
		// 1. 根据id查询元素getElementById
		str = dom.getElementById("auto-header-fenzhan").text();

		// 2. 根据标签获取元素getElementsByTag参考以上的例子

		// 3. 根据class获取元素getElementsByClass
		str = dom.getElementsByClass("mini-left").first().text();
		// str = dom.getElementsByClass("mini-left").get(1).text();

		// 4. 根据属性获取元素getElementsByAttribute
		str = dom.getElementsByAttribute("abc").text();
		str = dom.getElementsByAttributeValue("abc", "123").first().text();

		// 元数据获取
		// 1. 从元素中获取id
		str = dom.getElementById("auto-header-fenzhan").id();

		// 2. 从元素中获取className和classNames
		str = dom.getElementById("auto-header-fenzhan").className();
		for (String className : dom.getElementById("auto-header-fenzhan").classNames()) {
			System.out.println(className);
		}

		// 3. 从元素中获取属性的值attr
		str = dom.getElementById("auto-header-fenzhan").attr("abc");

		// 4. 从元素中获取所有属性attributes
		Attributes attributes = dom.getElementById("auto-header-fenzhan").attributes();
		System.out.println(attributes.toString());

		// 5. 从元素中获取文本内容text,参考以上内容
		// str = dom.getElementById("auto-header-fenzhan").text();

		// 6. 从获取元素中的html
		str = dom.getElementById("auto-header-fenzhan").html();




		// 选择器语法
		// tagname: 通过标签查找元素，比如：a
		str = dom.select("title").first().text();

		// ns|tag: 通过标签在命名空间查找元素，比如：可以用 fb|name 语法来查找 <fb:name> 元素
		str = dom.select("ns|li").first().text();

		// #id: 通过ID查找元素，比如：#logo
		str = dom.select("#auto-header-fenzhan").first().text();

		// .class: 通过class名称查找元素，比如：.masthead
		str = dom.select(".orangelink").first().text();

		// [attribute]: 利用属性查找元素，比如：[href]
		str = dom.select("[abc]").text();

		// [attr=value]: 利用属性值来查找元素，比如：[width=500]
		str = dom.select("[abc=456]").text();




		// 选择器得组合使用
		// el#id: 元素+ID，比如： div#logo
		str = dom.select("ns|li#auto-header-fenzhan").text();

		// el.class: 元素+class，比如： div.masthead
		str = dom.select("a.orangelink").text();

		// el[attr]: 元素+属性名，比如： a[href]
		str = dom.select("a[abc]").text();

		// 任意组合，比如：a[href].highlight
		str = dom.select("div#auto-header.topbar").text();

		// ancestor child: 查找某个元素下子元素，比如：.body p 查找"body"下的所有 p
		str = dom.select("div#auto-header .mini-left").text();

		// parent > child: 查找某个父元素下的直接子元素，比如：div.content > p 查找 p
		// Elements elements = dom.select("div.mini-left123 > li");
		// System.out.println("----");
		// for (Element element : elements) {
		// System.out.println(element.attr("abc"));
		// }

		// parent > * 查找某个父元素下所有直接子元素
		Elements elements = dom.select("div.mini-left123 > *");
		System.out.println("----");
		for (Element element : elements) {
			System.out.println(element.attr("abc"));
		}

		System.out.println("---------------");
		System.out.println("获取到的内容是:[" + str + "]");

	}

}
