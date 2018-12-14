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
public class Teacher_card extends Model<Teacher_card> {

    private static final long serialVersionUID = 1L;

    private Integer tcid;

    private String tcdeas;

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }
    public String getTcdeas() {
        return tcdeas;
    }

    public void setTcdeas(String tcdeas) {
        this.tcdeas = tcdeas;
    }

    @Override
    protected Serializable pkVal() {
        return this.tcid;
    }

    @Override
    public String toString() {
        return "Teacher_card{" +
        "tcid=" + tcid +
        ", tcdeas=" + tcdeas +
        "}";
    }
}
