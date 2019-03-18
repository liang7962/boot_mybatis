package com.mybatis.generator.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Liang
 * @since 2019-03-18
 */
public class Less extends Model<Less> {

    private static final long serialVersionUID = 1L;

    /**
     * 样品id
     */
    private String id;

    /**
     * 创建时间
     */
    private Date create_date;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Less{" +
        "id=" + id +
        ", create_date=" + create_date +
        ", name=" + name +
        "}";
    }
}
