package com.osaz.danaka.member.model.dto;


import java.sql.Date;


public class MemberDTO {

    public int userNo;
    public String userId;
    public String userNickname;
    public String userPwd;
    public String userName;
    public Date birthday;
    public char gender;
    public String address;
    public String phone;
    public Date regDate;
    public char status;
    public char userRole;

    public MemberDTO() {}

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getUserRole() {
        return userRole;
    }

    public void setUserRole(char userRole) {
        this.userRole = userRole;
    }

    public MemberDTO(int userNo, String userId, String userNickname, String userPwd, String userName, Date birthday, char gender, String address, String phone, Date regDate, char status, char userRole) {
        this.userNo = userNo;
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPwd = userPwd;
        this.userName = userName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.regDate = regDate;
        this.status = status;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", regDate=" + regDate +
                ", status=" + status +
                ", userRole=" + userRole +
                '}';
    }
}

