package com.mtons.mblog.entity;/**
 * Created by Administrator on 2019/5/17.
 */

import lombok.Data;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/5/17.
 **/
@Data
public class ChatRecord {
    private Long userId;

    private String userName;

    private String info;

    private Long chatGroupId;
}
