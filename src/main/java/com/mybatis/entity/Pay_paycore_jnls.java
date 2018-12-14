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
public class Pay_paycore_jnls extends Model<Pay_paycore_jnls> {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方机构编号
     */
    private String ORG_NO;

    private String PAY_METHOD;

    private String MERCHANT_NO;

    private String TERMINAL_NO;

    /**
     * 第三方机构支付订单号
     */
    private String PAY_NO;

    private String AMOUNT;

    private String NOTIFY_URL;

    private String DESCRIPT;

    private String MID;

    /**
     * 第三方机构时间戳
     */
    private String TMSTAMP;

    /**
     * 应答时间戳
     */
    private String R_TMSTAMP;

    /**
     * 通知时间戳
     */
    private String N_TMSTAMP;

    private String INSTITUTE;

    /**
     * 平台流水号
     */
    private String PJNL;

    /**
     * 上游流水号
     */
    private String BJNL;

    private String REPLY_CODE;

    private String REPLY_MESSAGE;

    /**
     * 上游支付状态
     */
    private String TRADE_STATUS;

    private String REMAIN1;

    private String REMAIN2;

    private String REMAIN3;

    private String REQUEST_DATE;

    private String REQUEST_TIME;

    private String RESPONSE_DATE;

    private String RESPONSE_TIME;

    private String FLAG;

    /**
     * 通道标识
     */
    private String CHANNEL;

    /**
     * 原始平台订单号
     */
    private String ORIG_PJNL;

    public String getORG_NO() {
        return ORG_NO;
    }

    public void setORG_NO(String ORG_NO) {
        this.ORG_NO = ORG_NO;
    }
    public String getPAY_METHOD() {
        return PAY_METHOD;
    }

    public void setPAY_METHOD(String PAY_METHOD) {
        this.PAY_METHOD = PAY_METHOD;
    }
    public String getMERCHANT_NO() {
        return MERCHANT_NO;
    }

    public void setMERCHANT_NO(String MERCHANT_NO) {
        this.MERCHANT_NO = MERCHANT_NO;
    }
    public String getTERMINAL_NO() {
        return TERMINAL_NO;
    }

    public void setTERMINAL_NO(String TERMINAL_NO) {
        this.TERMINAL_NO = TERMINAL_NO;
    }
    public String getPAY_NO() {
        return PAY_NO;
    }

    public void setPAY_NO(String PAY_NO) {
        this.PAY_NO = PAY_NO;
    }
    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }
    public String getNOTIFY_URL() {
        return NOTIFY_URL;
    }

    public void setNOTIFY_URL(String NOTIFY_URL) {
        this.NOTIFY_URL = NOTIFY_URL;
    }
    public String getDESCRIPT() {
        return DESCRIPT;
    }

    public void setDESCRIPT(String DESCRIPT) {
        this.DESCRIPT = DESCRIPT;
    }
    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }
    public String getTMSTAMP() {
        return TMSTAMP;
    }

    public void setTMSTAMP(String TMSTAMP) {
        this.TMSTAMP = TMSTAMP;
    }
    public String getR_TMSTAMP() {
        return R_TMSTAMP;
    }

    public void setR_TMSTAMP(String R_TMSTAMP) {
        this.R_TMSTAMP = R_TMSTAMP;
    }
    public String getN_TMSTAMP() {
        return N_TMSTAMP;
    }

    public void setN_TMSTAMP(String N_TMSTAMP) {
        this.N_TMSTAMP = N_TMSTAMP;
    }
    public String getINSTITUTE() {
        return INSTITUTE;
    }

    public void setINSTITUTE(String INSTITUTE) {
        this.INSTITUTE = INSTITUTE;
    }
    public String getPJNL() {
        return PJNL;
    }

    public void setPJNL(String PJNL) {
        this.PJNL = PJNL;
    }
    public String getBJNL() {
        return BJNL;
    }

    public void setBJNL(String BJNL) {
        this.BJNL = BJNL;
    }
    public String getREPLY_CODE() {
        return REPLY_CODE;
    }

    public void setREPLY_CODE(String REPLY_CODE) {
        this.REPLY_CODE = REPLY_CODE;
    }
    public String getREPLY_MESSAGE() {
        return REPLY_MESSAGE;
    }

    public void setREPLY_MESSAGE(String REPLY_MESSAGE) {
        this.REPLY_MESSAGE = REPLY_MESSAGE;
    }
    public String getTRADE_STATUS() {
        return TRADE_STATUS;
    }

    public void setTRADE_STATUS(String TRADE_STATUS) {
        this.TRADE_STATUS = TRADE_STATUS;
    }
    public String getREMAIN1() {
        return REMAIN1;
    }

    public void setREMAIN1(String REMAIN1) {
        this.REMAIN1 = REMAIN1;
    }
    public String getREMAIN2() {
        return REMAIN2;
    }

    public void setREMAIN2(String REMAIN2) {
        this.REMAIN2 = REMAIN2;
    }
    public String getREMAIN3() {
        return REMAIN3;
    }

    public void setREMAIN3(String REMAIN3) {
        this.REMAIN3 = REMAIN3;
    }
    public String getREQUEST_DATE() {
        return REQUEST_DATE;
    }

    public void setREQUEST_DATE(String REQUEST_DATE) {
        this.REQUEST_DATE = REQUEST_DATE;
    }
    public String getREQUEST_TIME() {
        return REQUEST_TIME;
    }

    public void setREQUEST_TIME(String REQUEST_TIME) {
        this.REQUEST_TIME = REQUEST_TIME;
    }
    public String getRESPONSE_DATE() {
        return RESPONSE_DATE;
    }

    public void setRESPONSE_DATE(String RESPONSE_DATE) {
        this.RESPONSE_DATE = RESPONSE_DATE;
    }
    public String getRESPONSE_TIME() {
        return RESPONSE_TIME;
    }

    public void setRESPONSE_TIME(String RESPONSE_TIME) {
        this.RESPONSE_TIME = RESPONSE_TIME;
    }
    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }
    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }
    public String getORIG_PJNL() {
        return ORIG_PJNL;
    }

    public void setORIG_PJNL(String ORIG_PJNL) {
        this.ORIG_PJNL = ORIG_PJNL;
    }

    @Override
    protected Serializable pkVal() {
        return this.ORG_NO;
    }

    @Override
    public String toString() {
        return "Pay_paycore_jnls{" +
        "ORG_NO=" + ORG_NO +
        ", PAY_METHOD=" + PAY_METHOD +
        ", MERCHANT_NO=" + MERCHANT_NO +
        ", TERMINAL_NO=" + TERMINAL_NO +
        ", PAY_NO=" + PAY_NO +
        ", AMOUNT=" + AMOUNT +
        ", NOTIFY_URL=" + NOTIFY_URL +
        ", DESCRIPT=" + DESCRIPT +
        ", MID=" + MID +
        ", TMSTAMP=" + TMSTAMP +
        ", R_TMSTAMP=" + R_TMSTAMP +
        ", N_TMSTAMP=" + N_TMSTAMP +
        ", INSTITUTE=" + INSTITUTE +
        ", PJNL=" + PJNL +
        ", BJNL=" + BJNL +
        ", REPLY_CODE=" + REPLY_CODE +
        ", REPLY_MESSAGE=" + REPLY_MESSAGE +
        ", TRADE_STATUS=" + TRADE_STATUS +
        ", REMAIN1=" + REMAIN1 +
        ", REMAIN2=" + REMAIN2 +
        ", REMAIN3=" + REMAIN3 +
        ", REQUEST_DATE=" + REQUEST_DATE +
        ", REQUEST_TIME=" + REQUEST_TIME +
        ", RESPONSE_DATE=" + RESPONSE_DATE +
        ", RESPONSE_TIME=" + RESPONSE_TIME +
        ", FLAG=" + FLAG +
        ", CHANNEL=" + CHANNEL +
        ", ORIG_PJNL=" + ORIG_PJNL +
        "}";
    }
}
