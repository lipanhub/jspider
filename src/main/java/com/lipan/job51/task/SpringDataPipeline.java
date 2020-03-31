package com.lipan.job51.task;


import com.lipan.job51.entity.Job;
import com.lipan.job51.service.JobService;
import com.lipan.job51.service.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/31 22:07
 */
@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private JobService jobService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Job job = resultItems.get("job");
        if (null != job) {
            jobService.save(job);
        }
    }
}
