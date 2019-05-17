package com.mtons.mblog.vo;

import lombok.Data;

/**
 * Created by raden on 2019/5/7.
 */
@Data
public class WsMessage {

    private int t; // 消息类型
    private String n; // 用户名称
    private String user_id; // 用户id
    // TODO: 预留房间ID
    private long room_id; // 房间 ID
    private String body; // 消息主体
    private int err; //错误码



    public WsMessage(int t, String n, int err) {
        this.t = t;
        this.n = n;
        this.err = err;
    }

    public WsMessage(int t, String n) {
        this.t = t;
        this.n = n;
        this.err = 0;
    }

    public WsMessage(int t, String n, String body, int err) {
        this.t = t;
        this.n = n;
        this.body = body;
        this.err = err;
    }

    public WsMessage(int t, String n, String body) {
        this.t = t;
        this.n = n;
        this.body = body;
        this.err = 0;
    }

}
