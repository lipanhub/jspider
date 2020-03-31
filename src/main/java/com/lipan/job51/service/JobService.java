package com.lipan.job51.service;



import com.lipan.job51.entity.Job;

import java.util.List;

/**
 * @author 001
 */
public interface JobService {

    /**
     * 保存一个商品
     *
     * @param job
     */
    void save(Job job);

    /**
     * 查询
     * @param job
     * @return
     */
    List<Job> findAll(Job job);
}
