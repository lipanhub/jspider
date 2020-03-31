package com.lipan.jd.dao;

import com.lipan.jd.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: lipan
 * @time: 2020/3/29 14:58
 */
public interface ItemDao extends JpaRepository<Item,Long> {
}
