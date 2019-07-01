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
import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.base.utils.FileKit;
import com.mtons.mblog.convert.ConvertChat;
import com.mtons.mblog.entity.GroupUser;
import com.mtons.mblog.enums.RadioStatusEnum;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.modules.service.MessageService;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.moduls.RadioRecordVO;
import com.mtons.mblog.sdk.OasrRequestSample;
import com.mtons.mblog.sdk.model.OasrResponse;
import com.mtons.mblog.service.ChatService;
import com.mtons.mblog.service.RadioRecordService;
import com.mtons.mblog.web.controller.BaseController;
import com.mtons.mblog.web.controller.site.Views;
import com.mtons.mblog.web.controller.site.posts.UploadController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

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
    private RadioRecordService radioRecordService;

    @GetMapping(value = "/index")
    @ResponseBody
    public String home(ModelMap model, HttpServletRequest request) {
        // 创建聊天室
        AccountProfile profile = getProfile();
        return String.valueOf(profile.getId());
    }

    /**
     * 用户文章
     *
     * @param userId 用户ID
     * @param model  ModelMap
     * @return template name
     */
    @GetMapping(value = "/info/{userId}")
    public String posts(@PathVariable(value = "userId") Long userId,
                        ModelMap model, HttpServletRequest request) {
        // 访问消息页, 判断登录
        // 标记已读
        AccountProfile profile = getProfile();
        if (null == profile || profile.getId() != userId) {
            throw new MtonsException("您没有权限访问该页面");
        }
        messageService.readed4Me(profile.getId());
        initUser(userId, model);
        return method(userId, Views.METHOD_POSTS, model, request);
    }

    /**
     * 通用方法, 访问 users 目录下的页面
     *
     * @param userId 用户ID
     * @param method 调用方法
     * @param model  ModelMap
     * @return template name
     */
    public String method(@PathVariable(value = "userId") Long userId,
                         @PathVariable(value = "method") String method,
                         ModelMap model, HttpServletRequest request) {
        model.put("pageNo", ServletRequestUtils.getIntParameter(request, "pageNo", 1));

        // 访问消息页, 判断登录
        if (Views.METHOD_MESSAGES.equals(method)) {
            // 标记已读
            AccountProfile profile = getProfile();
            if (null == profile || profile.getId() != userId) {
                throw new MtonsException("您没有权限访问该页面");
            }
            messageService.readed4Me(profile.getId());
        }

        initUser(userId, model);
        return view(String.format(Views.USER_RADIO_TEMPLATE, method));
    }

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

    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {

//                    String suffix = FileKit.getSuffix(Objects.requireNonNull(file.getOriginalFilename()));
//            if (!".zip".equalsIgnoreCase(suffix)) {
//                return Result.failure("请上传zip文件");
//            }
        AccountProfile profile = getProfile();
        if (null == profile ) {
            throw new MtonsException("您没有权限访问该页面");
        }
        if (null == file || file.isEmpty()) {
            return Result.failure("文件不能为空");
        } else try {
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "download/";
            String originalFilename = file.getOriginalFilename();
            savePic(file.getBytes(), originalFilename, path);
            OasrRequestSample oasrRequestSample = new OasrRequestSample();
            OasrResponse oasrResponse = oasrRequestSample.start(path + originalFilename);
            new File(path + originalFilename).delete();

            if (oasrResponse.getCode().equals("0")) {
                RadioRecordVO radioRecord = new RadioRecordVO();
                radioRecord.setFileName(originalFilename);
                radioRecord.setRequestId(oasrResponse.getRequestId());
                radioRecord.setStatus(RadioStatusEnum.init.getCode());
                radioRecord.setUserId(profile.getId());
                radioRecordService.add(ConvertChat.convert(radioRecord));
                return Result.success();
            }
            RadioRecordVO radioRecord = new RadioRecordVO();
            radioRecord.setFileName(originalFilename);
            radioRecord.setRequestId(oasrResponse.getRequestId());
            radioRecord.setStatus(RadioStatusEnum.fail.getCode());
            radioRecord.setUserId(profile.getId());
            radioRecordService.add(ConvertChat.convert(radioRecord));
            return Result.failure("文件上传失败");
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    private void savePic(byte[] bs, String fileName, String path) {
        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
                os.write(bs, 0, bs.length);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("download")
    public String download(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            log.info(message + "----返回信息");
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
                if (fw != null) {
                    fw.close();
                }
                if (Buff != null) {
                    Buff.close();
                }
                if (outSTr != null) {
                    outSTr.close();
                }
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
