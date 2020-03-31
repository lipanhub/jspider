package com.lipan.jd.service;

import com.lipan.jd.entity.Item;

import java.util.List;

/**
 * @author 001
 */
public interface ItemService {

    /**
     * 保存一个商品
     *
     * @param item
     */
    void save(Item item);

    /**
     * 查询
     * @param item
     * @return
     */
    List<Item> findAll(Item item);
}
