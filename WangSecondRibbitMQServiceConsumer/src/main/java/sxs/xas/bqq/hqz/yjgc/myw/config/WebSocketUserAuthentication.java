package sxs.xas.bqq.hqz.yjgc.myw.config;

import java.security.Principal;

public class WebSocketUserAuthentication implements Principal{

	/**
     * 用户身份标识符
     */
    private String token;

    public WebSocketUserAuthentication(String token) {
        this.token = token;
    }

    public WebSocketUserAuthentication() {
    }

    /**
     * 获取用户登录令牌
     * @return
     */
    @Override
    public String getName() {
        return token;
    }

}
