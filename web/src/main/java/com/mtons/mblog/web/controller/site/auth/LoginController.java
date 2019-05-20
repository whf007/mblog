/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.web.controller.site.auth;

import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.web.controller.BaseController;
import com.mtons.mblog.web.controller.site.Views;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 * @author langhsu
 */
@Slf4j
@Controller
public class LoginController extends BaseController {

    /**
     * 跳转登录页
     * @return
     */
	@GetMapping(value = "/login")
	public String view() {
		return view(Views.LOGIN);
	}

    /**
     * 提交登录
     * @param username
     * @param password
     * @param model
     * @return
     */
	@PostMapping(value = "/login")
	public String login(String username,
                        String password,
                        @RequestParam(value = "rememberMe",defaultValue = "0") Boolean rememberMe,
                        ModelMap model,HttpServletRequest request) {
		String view = view(Views.LOGIN);
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String requestUrl = "";
        if(savedRequest != null) {
            requestUrl = savedRequest.getRequestUrl();
        }
        Result<AccountProfile> result = executeLogin(username, password, rememberMe);

        if (result.isOk()) {
            if(StringUtils.isNotBlank(requestUrl)) {
                view = String.format(Views.REDIRECT_LOGIN_HOME, requestUrl);
            } else {
                view = String.format(Views.REDIRECT_USER_HOME, result.getData().getId());
            }
        } else {
            model.put("message", result.getMessage());
        }
        return view;
	}

}
