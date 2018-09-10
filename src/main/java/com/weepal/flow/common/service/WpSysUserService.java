package com.weepal.flow.common.service;


import com.weepal.flow.common.entity.WpSysUser;

/**
 * <p>
 * <b>WpSysUserService</b> is 功能描述
 * </p>
 *
 * @author Bin
 * @version $Id: spring-boot-shiro 62440 2018-09-03 14:50:15Z Bin $
 * @since 2018年09月03日
 **/
public interface WpSysUserService {

    WpSysUser getUserInfo(String userid);
}
