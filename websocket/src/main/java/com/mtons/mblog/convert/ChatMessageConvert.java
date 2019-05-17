package com.mtons.mblog.convert;/**
 * Created by Administrator on 2019/5/17.
 */

import com.mtons.mblog.entity.ChatRecord;
import com.mtons.mblog.vo.WsMessage;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/5/17.
 **/
public class ChatMessageConvert {
    public static ChatRecord convert(WsMessage wsMessage){
        ChatRecord record = new ChatRecord();
        record.setChatGroupId(wsMessage.getRoom_id());
        record.setInfo(wsMessage.getBody());
        record.setUserId(Long.valueOf(wsMessage.getUser_id()));
        record.setUserName(wsMessage.getN());
        return record;
    }
}
