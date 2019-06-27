/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.web.controller.site.record;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.lang.MtonsException;
import com.mtons.mblog.base.utils.FileKit;
import com.mtons.mblog.entity.GroupUser;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.modules.service.MessageService;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.service.ChatService;
import com.mtons.mblog.web.controller.BaseController;
import com.mtons.mblog.web.controller.site.Views;
import com.mtons.mblog.web.controller.site.posts.UploadController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * 用户主页
 *
 * @author langhsu
 */
@Controller
@RequestMapping("/radio")
@Slf4j
public class RadioController extends BaseController {
    public static HashMap<String, String> errorInfo = new HashMap<>();
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;


    /**
     * 通用方法, 访问 users 目录下的页面
     * @param userId 用户ID
     * @param method 调用方法
     * @param model  ModelMap
     * @return template name
     */
//    @GetMapping(value = "/{userId}")
//    public String method(@PathVariable(value = "userId") Long userId,
//                         @PathVariable(value = "method") String method,
//                         ModelMap model, HttpServletRequest request) {
//        model.put("pageNo", ServletRequestUtils.getIntParameter(request, "pageNo", 1));
//
//        // 访问消息页, 判断登录
//        if (Views.METHOD_MESSAGES.equals(method)) {
//            // 标记已读
//            AccountProfile profile = getProfile();
//            if (null == profile || profile.getId() != userId) {
//                throw new MtonsException("您没有权限访问该页面");
//            }
//            messageService.readed4Me(profile.getId());
//        }
//
//        initUser(userId, model);
//        return view(String.format(Views.USER_LIVE_TEMPLATE, method));
//    }

    private void initUser(long userId, ModelMap model) {
        model.put("user", userService.get(userId));
        boolean owner = false;

        AccountProfile profile = getProfile();
        if (null != profile && profile.getId() == userId) {
            owner = true;
            putProfile(userService.findProfile(profile.getId()));
        }
        model.put("owner", owner);
    }
    @PostMapping("result")
    @ResponseBody
    public String result(HttpServletRequest request) {
        String code = request.getParameter("code");
        String message = request.getParameter("message");
        String requestId = request.getParameter("requestId");
        String audioTime = request.getParameter("audioTime");
        log.info(message +"----返回信息");
        // 根据回调结果中的任务号找到订单
//        Order order = orderService.get(requestId);
//        if (order.getResult() == null && code.equals("0")) {
//            if (code.equals("0")) {
//                // 删除临时文件
//                String path = uploadFolder + order.getFileName();
//                File file = new File(path);
//                file.delete();
//            } else {
//                order.setResult(message);
//                order.setState(3);
//            }
//            orderService.saveResult(order);
//        }
        return "success";
    }
}
