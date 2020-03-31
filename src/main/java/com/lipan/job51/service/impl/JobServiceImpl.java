package com.lipan.job51.service.impl;

import com.lipan.job51.dao.JobDao;
import com.lipan.job51.entity.Job;
import com.lipan.job51.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/29 15:02
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao JobDao;


    @Override
    @Transactional
    public void save(Job job) {

        Job param = new Job();
        param.setUrl(job.getUrl());
        param.setTime(job.getTime());
        List<Job> jobList = findAll(param);
        if(null == jobList || 0 == jobList.size()){
            JobDao.saveAndFlush(job);
        }
    }

    @Override
    public List<Job> findAll(Job job) {
        Example<Job>  example = Example.of(job);
        return JobDao.findAll(example);
    }
}
