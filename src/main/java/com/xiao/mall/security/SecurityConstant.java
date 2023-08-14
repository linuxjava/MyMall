package com.xiao.mall.security;

public class SecurityConstant {
    /**
     * 请求中header中token标识
     */
    public static final String TOKEN = "token";
    /**
     * token过期时间
     */
    public static final long TOKEN_EXPIRATION = 365*24*60*60*1000;
    /**
     * token秘钥
     */
    public static final String TOKEN_SIGN_KEY = "123abc!@#";
    /**
     * 登录url
     */
    public static final String URL_LOGIN = "/admin/acl/login";
    /**
     * 登录url
     */
    public static final String URL_LOGOUT = "/admin/acl/index/logout";
    /**
     * 拦截白名单
     */
    public static final String[] URL_WHITE = new String[]{"/api/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/**",
            "/swagger-ui.html/**",
            "/doc.html/**",
            "/rabbit/simple"
    };
}
