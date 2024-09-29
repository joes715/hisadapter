package com.hospital.adapt.model.local;

import com.hospital.adapt.model.common.BaseBean;
import com.hospital.adapt.utils.Str;

public class LoBnUser extends BaseBean {
    private String userId = null;
    private String userAccount = null;
    private String userPasswd = null;
    private String userNickname = null;
    private Integer userRole = null;
    private String wardId = null;
    private String deptId = null;
    private Integer sortCode = null;
    private Integer userState = null;
    private String noteTxt = null;

    private String wardCode = null;
    private String wardName = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = Str.chkNull(userId);
        this.id = Str.chkNull(userId);
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = Str.chkNull(userAccount);
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = Str.chkNull(userPasswd);
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = Str.chkNull(userNickname);
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = Str.chkNull(wardId);
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = Str.chkNull(deptId);
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getNoteTxt() {
        return noteTxt;
    }

    public void setNoteTxt(String noteTxt) {
        this.noteTxt = Str.chkNull(noteTxt);
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = Str.chkNull(wardCode);
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = Str.chkNull(wardName);
    }

}
