package com.weepal.flow.common.dao;

import com.weepal.flow.common.entity.WpSysUser;

/**
 * Created by mawen on 2018/8/31.
 */
public interface WpSysUserDao {
    WpSysUser selectUserInfo(String userid);
}
