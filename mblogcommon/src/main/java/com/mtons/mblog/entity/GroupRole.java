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
public class GroupRole {
    private Long userId;

    private String userName;

    private Long chatGroupId;

    private String role;
    private Long operationId;
    private String operationName;
}
