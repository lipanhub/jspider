package com.lipan.job51.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/29 14:52
 */
@Data
@Entity
@Table(name = "job_info")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyAddr;
    private String companyInfo;
    private String jobName;
    private String jobAddr;
    private String jobInfo;
    private Integer salaryMin;
    private Integer salaryMax;
    private String url;
    private String time;

}
