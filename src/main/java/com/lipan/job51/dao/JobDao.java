package com.lipan.job51.dao;

import com.lipan.job51.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/29 14:58
 */
public interface JobDao extends JpaRepository<Job,Long> {
}
