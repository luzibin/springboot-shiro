package com.weepal.flow.common.entity;

import java.io.Serializable;
import java.util.Date;

//用户信息
public class WpSysUser implements Serializable {

    private static final long serialVersionUID = 3543736883275640902L;
    private Integer userId;

    private Integer roleId;

    private Date bdate;

    private Date edate;

    private Boolean isActive;

    private String guid;

    private Date addDate;

    private String name;

    private String email;

    private String password;

    private String account;

    private String mobile;

    private byte[] timeStamp;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return BDate
     */
    public Date getBdate() {
        return bdate;
    }

    /**
     * @param bdate
     */
    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    /**
     * @return EDate
     */
    public Date getEdate() {
        return edate;
    }

    /**
     * @param edate
     */
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * @return guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return add_date
     */
    public Date getAddDate() {
        return addDate;
    }

    /**
     * @param addDate
     */
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public byte[] getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(byte[] timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return time_stamp
     */

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}