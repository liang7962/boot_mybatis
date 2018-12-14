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
public class Teacher extends Model<Teacher> {

    private static final long serialVersionUID = 1L;

    private Integer tid;

    private String tname;

    private Integer tcid;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    @Override
    protected Serializable pkVal() {
        return this.tid;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        "tid=" + tid +
        ", tname=" + tname +
        ", tcid=" + tcid +
        "}";
    }
}
