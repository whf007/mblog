package com.mtons.mblog.config;

import com.mtons.mblog.entity.GroupUser;
import freemarker.ext.beans.HashAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by raden on 2019/5/7.
 */
public class NettyConfig {
    // 存储所有连接的 channel
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Map<ChannelHandlerContext,String> ctxs = new HashMap<>();
    // 记录聊天人员信息
    public static Map<Long,LinkedList<GroupUser>> gourpMap = new HashMap<>();
    // 防止意外链接断开无法删除用户信息
    public static Map<ChannelHandlerContext,GroupUser> chxGourpMap = new HashMap<>();
    // host name 和监听的端口号，需要配置到配置文件中
    public static String WS_HOST = "127.0.0.1";
    public static int WS_PORT = 9090;
}
