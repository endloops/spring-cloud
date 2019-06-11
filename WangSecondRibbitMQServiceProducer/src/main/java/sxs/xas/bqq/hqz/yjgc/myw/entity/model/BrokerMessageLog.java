package sxs.xas.bqq.hqz.yjgc.myw.entity.model;

import java.util.Date;

public class BrokerMessageLog {
    private String messageId;

    private String message;

    private Integer tryAccount;

    private String status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getTryAccount() {
        return tryAccount;
    }

    public void setTryAccount(Integer tryAccount) {
        this.tryAccount = tryAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}