package com.lipan.jd.entity;

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
@Table(name = "jd_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long spu;
    private Long sku;
    private String title;
    private Double price;
    private String pic;
    private String url;
    private Date created;
    private Date updated;
}
