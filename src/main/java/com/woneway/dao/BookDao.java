package com.woneway.dao;

import org.springframework.stereotype.Repository;

/**
 * @author 连戊
 * @version 1.00
 * @time 2020/10/10 4:34 下午
 * @description
 */
@Repository
public class BookDao {

    private int label = 1;

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label=" + label +
                '}';
    }
}
