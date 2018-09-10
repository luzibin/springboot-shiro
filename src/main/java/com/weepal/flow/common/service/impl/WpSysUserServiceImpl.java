package com.weepal.flow.common.service.impl;


import com.weepal.flow.common.dao.WpSysUserDao;
import com.weepal.flow.common.entity.WpSysUser;
import com.weepal.flow.common.service.WpSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <b>WpSysUserServiceImpl</b> is 功能描述
 * </p>
 *
 * @author Bin
 * @version $Id: spring-boot-shiro 62440 2018-09-03 14:51:15Z Bin $
 * @since 2018年09月03日
 **/
@Service("wpSysUserService")
public class WpSysUserServiceImpl implements WpSysUserService {

    @Autowired
    private WpSysUserDao wpSysUserDao;

    @Override
    public WpSysUser getUserInfo(String userid) {
        return wpSysUserDao.selectUserInfo(userid);
    }
}
