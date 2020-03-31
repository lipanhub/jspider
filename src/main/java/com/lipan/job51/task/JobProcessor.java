package com.lipan.job51.task;


import com.lipan.job51.entity.Job;
import com.lipan.job51.util.SalaryUtils;
import org.jsoup.Jsoup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/29 16:28
 */
@Component
public class JobProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        List<Selectable> jobDivList = page.getHtml().css("div#resultList div.el").nodes();
        if (null == jobDivList || 0 == jobDivList.size()) {
            this.saveJob(page);
        } else {
            this.getJobUrls(jobDivList,page);
        }
    }

    private void saveJob(Page page) {
        Job job = new Job();

        Html html = page.getHtml();

        job.setCompanyName(html.css("div.cn p.cname a","text").toString());
        job.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
        job.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        job.setJobName(html.css("div.cn h1","text").toString());
        job.setJobAddr(html.css("div.cn span.lname","text").toString());
        job.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        job.setUrl(page.getUrl().toString());

        Integer[] salary = SalaryUtils.getSalary(html.css("div.cn strong", "text").toString());
        job.setSalaryMin(salary[0]);
        job.setSalaryMax(salary[1]);

        String time = Jsoup.parse(html.css("div.t1 span").regex(".*发布").toString()).text();
        job.setTime(time.substring(0,time.length()-2));
    }

    private void getJobUrls(List<Selectable> jobDivList,Page page) {
        for (Selectable jobDiv : jobDivList) {
            String jobUrl = jobDiv.links().toString();
            page.addTargetRequest(jobUrl);
        }
        String nextPage = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
        page.addTargetRequest(nextPage);
    }

    @Override
    public Site getSite() {
        Site site = Site.me();
        site.setRetrySleepTime(3 * 1000).setCharset("gbk").setRetryTimes(3).setTimeOut(10 * 1000);
        return site;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100)
    public void process() {

        String url = "https://search.51job.com/list/000000,000000,0000,01%252C32,9,99,java,2,1.html?lang=c" +
                "&stype=&postchannel=0000&workyear=99" +
                "&cotype=99&degreefrom=99&jobterm=99&companysize=99" +
                "&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9" +
                "&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        Spider.create(new JobProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .run();
    }
}
