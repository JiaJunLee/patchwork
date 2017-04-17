package com.sourcetech.patchwork.servers.oauth;

import com.opensymphony.xwork2.ActionSupport;
import com.sourcetech.patchwork.hibernate.HibernateFactory;
import com.sourcetech.patchwork.hibernate.model.PatchworkAccessTokenEntity;
import com.sourcetech.patchwork.hibernate.model.PatchworkMemberEntity;
import com.sourcetech.patchwork.hibernate.model.PatchworkRefreshTokenEntity;
import com.sourcetech.patchwork.hibernate.service.AccessTokenServiceImpl;
import com.sourcetech.patchwork.hibernate.service.MemberServiceImpl;
import com.sourcetech.patchwork.hibernate.service.RefreshTokenServiceImpl;
import com.sourcetech.patchwork.servers.oauth.define.OAuthServiceDef;
import com.sourcetech.patchwork.util.safe.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 李佳骏 on 2017/4/15.
 */

public class OAuthServiceByPasswordCredentials extends ActionSupport implements OAuthServiceDef{

    private String publicKey;
    private String username;
    private String password;
    private String accessToken;
    private String refreshToken;

    @Autowired
    private MemberServiceImpl memberService;
    @Autowired
    private AccessTokenServiceImpl accessTokenService;
    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;
    @Autowired
    private RSAFactory rsaFactory;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String oAuthService() {

        // 封装用户数据
        PatchworkMemberEntity memberEntity = getMemberEntity();
        // 检索用户
        memberEntity = memberService.queryMember(memberEntity);

        // 如果不存在该用户
        if(memberEntity==null)
            return INPUT;

        // 解析密码元数据
        String passwordMeta = getPasswordMeta();

        // 解析失败
        if(passwordMeta==null)
            return ERROR;

        // 校验用户
        if(memberService.checkMember(memberEntity,passwordMeta)) {

            //创建AccessToken
            accessTokenService.createToken(memberEntity.getmId());
            //创建RefreshToken
            refreshTokenService.createToken(memberEntity.getmId());

            return SUCCESS;
        }
        else
            return INPUT;
    }

    private PatchworkMemberEntity getMemberEntity(){
        PatchworkMemberEntity memberEntity = new PatchworkMemberEntity();
        memberEntity.setmEmail(getUsername());
        memberEntity.setmPhoneNumber(getUsername());
        return memberEntity;
    }

    private String getPasswordMeta(){
        String passwordMeta = null;
        try {
            passwordMeta = rsaFactory.decode(new String[]{getPassword()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passwordMeta;
    }

    public String refreshToken() {
        return null;
    }

    public String reqPublicKey(){
        Map<String,Object> keys = rsaFactory.getKeys();
        RSAPublicKey publicKey = (RSAPublicKey) keys.get(RSAEncrypt.PUBLIC_KEY);
        setPublicKey(Base64.encode(publicKey.getEncoded()));
        return SUCCESS;
    }

}
