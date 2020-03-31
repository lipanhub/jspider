package com.lipan.jd.task;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lipan.jd.entity.Item;
import com.lipan.jd.service.ItemService;
import com.lipan.jd.util.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/29 16:28
 */
@Component
public class ItemTask {

    @Autowired(required = false)
    private HttpClientUtils httpClientUtils;
    @Autowired
    private ItemService itemService;
    private final ObjectMapper MAPPER = new ObjectMapper();

    @Scheduled(fixedDelay = 100 * 1000)
    public void itemTask() {
        String url = "https://search.jd.com/Search" +
                "?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2" +
                "&wq=%E6%89%8B%E6%9C%BA&s=56&click=0&page=";
        for (int i = 1; i < 10; i += 2) {
            String htmlContent = httpClientUtils.doGetHttp(url + i);
            this.parse(htmlContent);
        }

        System.out.println("spider run success!");
    }

    private void parse(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);
        Elements spuElements = document.select("#J_goodsList > ul > li");
        for (Element spuElement : spuElements) {
            Long spu = Long.parseLong(spuElement.attr("data-spu"));

            Elements skuElements = spuElement.select("li.ps-item");
            for (Element skuElement : skuElements) {
                long sku = Long.parseLong(skuElement.select("img[data-sku]").attr("data-sku"));

                Item item = new Item();
                item.setSpu(spu);
                item.setSku(sku);
                List<Item> itemList = itemService.findAll(item);
                if (null == itemList || 0 == itemList.size()) {
                    String url = "https://item.jd.com/" + sku + ".html";
                    item.setUrl(url);

                    String picUrl = "https:" + skuElement.select("img[data-sku]").attr("data-lazy-img");
                    picUrl = picUrl.replace("/n9/","/n1/");
                    item.setPic(picUrl);

                    String priceJson = httpClientUtils.doGetHttp("https://p.3.cn/prices/mgets?skuIds=J_" + sku);
                    Double price = 0.0;
                    try {
                        price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    item.setPrice(price);

                    String itemHtml = httpClientUtils.doGetHttp(item.getUrl());
                    Document itemDocument = Jsoup.parse(itemHtml);
                    String title = itemDocument.select("div.sku-name").first().text();
                    item.setTitle(title);

                    item.setCreated(new Date());
                    item.setUpdated(item.getCreated());

                    itemService.save(item);
                }
            }
        }
    }
}
