package com.mybatis.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Liang
 * @since 2018-12-14
 */
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    private Integer bid;

    private String name;

    private Integer anthorid;

    private Integer publicid;

    private Integer typeid;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getAnthorid() {
        return anthorid;
    }

    public void setAnthorid(Integer anthorid) {
        this.anthorid = anthorid;
    }
    public Integer getPublicid() {
        return publicid;
    }

    public void setPublicid(Integer publicid) {
        this.publicid = publicid;
    }
    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Override
    protected Serializable pkVal() {
        return this.bid;
    }

    @Override
    public String toString() {
        return "Book{" +
        "bid=" + bid +
        ", name=" + name +
        ", anthorid=" + anthorid +
        ", publicid=" + publicid +
        ", typeid=" + typeid +
        "}";
    }
}
