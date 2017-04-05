package com.sourcetech.patchwork;

import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

/**
 * Created by 李佳骏 on 2017/4/5.
 */
public class Hello {

    public String hello(){
        ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        session.put("hello","world");
        return "SUCCESS";
    }

}
