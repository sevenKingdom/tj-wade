package com.carry.control.model.po;

/**
 * Created by songxianying on 17/7/23.
 */
public class UserCreat {
    private UserData userData;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    private UserInfo userInfo;
}
