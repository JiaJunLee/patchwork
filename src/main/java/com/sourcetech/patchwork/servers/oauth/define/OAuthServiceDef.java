package com.sourcetech.patchwork.servers.oauth.define;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
public interface OAuthServiceDef {

    public static final long ACCESS_TOKEN_TIMEOUT = 1000 * 60;
    public static final long REFRESH_TOKEN_TIMEOUT = 1000 * 60 * 5;

    /**
     * 认证服务
     * @return
     */
    public String oAuthService();

    /**
     * 令牌刷新服务
     * @return
     */
    public String refreshToken();

}
