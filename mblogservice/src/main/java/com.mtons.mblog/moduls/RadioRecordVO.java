package com.mtons.mblog.moduls;/**
 * Created by Administrator on 2019/7/1.
 */

import lombok.Data;

import java.util.Date;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/7/1.
 **/
@Data
public class RadioRecordVO {
    private String requestId;

    private Long userId;

    private String fileName;

    private String status;

    private Date createTime = new Date();

    private Date updateTime = new Date();
}
