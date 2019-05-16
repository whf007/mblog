package com.mtons.mblog.entity;

import lombok.Data;

/**
 * Created by raden on 2019/5/16.
 */
@Data
public class GroupUser {
    private Long userId;

    private String userName;

    private Integer chatGroupId;
}
