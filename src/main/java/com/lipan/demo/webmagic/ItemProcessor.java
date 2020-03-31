package com.lipan.demo.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/31 13:12
 */
public class ItemProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetrySleepTime(1000);

    @Override
    public void process(Page page) {
        page.putField("myorder", page.getHtml().css(".fore2 div a").all());
        page.putField("plzlogin", page.getHtml().xpath("//a[@class=link-login]"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ItemProcessor())
                .addUrl("https://kuaibao.jd.com/?ids=231608998,790737,231486110,793988")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .thread(1)
                .run();
    }
}
