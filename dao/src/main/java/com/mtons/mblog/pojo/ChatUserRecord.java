package com.mtons.mblog.pojo;

import java.util.Date;

public class ChatUserRecord {
    private Long userId;

    private String userName;

    private String info;

<<<<<<< HEAD
    private String chatGroupId;
=======
    private Integer chatGroupId;
>>>>>>> 44f218f0ee4c6f097fc3e62cc2d0cc89cd114d1e

    private Date createTime;

    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

<<<<<<< HEAD
    public String getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(String chatGroupId) {
        this.chatGroupId = chatGroupId == null ? null : chatGroupId.trim();
=======
    public Integer getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
>>>>>>> 44f218f0ee4c6f097fc3e62cc2d0cc89cd114d1e
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