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
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    @GetMapping("download")
    public String download(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        String message = request.getParameter("message");
        String requestId = request.getParameter("requestId");
        String audioTime = request.getParameter("audioTime");
        log.info(message + "----返回信息");
        //获得请求文件名
        String filename = request.getParameter("filename");
        System.out.println(filename);
        //设置文件MIME类型
        response.setContentType(request.getServletContext().getMimeType("1.txt"));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + "1.txt");
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
//        String fullFileName = request.getServletContext().getRealPath("/download/" + filename);
        //System.out.println(fullFileName);
        //读取文件
//        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();

        //写文件
//        int b;
//        while ((b = in.read()) != -1) {
            out.write("123123123".getBytes());
//        }

//        in.close();
        out.close();
        return "success";
    }
    @PostMapping("result")
    @ResponseBody
    public String result(HttpServletRequest request) throws Exception {
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        FileWriter fw = null;
        try {
        String code = request.getParameter("code");
        String message = request.getParameter("message");
        String requestId = request.getParameter("requestId");
        String audioTime = request.getParameter("audioTime");
        log.info(message +"----返回信息");
        //经过测试：ufferedOutputStream执行耗时:1,1，1 毫秒
        outSTr = new FileOutputStream(new File("/download/" + requestId + ".txt"));
        Buff = new BufferedOutputStream(outSTr);
        long begin0 = System.currentTimeMillis();
        Buff.write(message.getBytes());
        Buff.flush();
        Buff.close();
        long end0 = System.currentTimeMillis();
        System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
