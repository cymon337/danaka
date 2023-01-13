package com.osaz.danaka.member.model.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;


@Data
@Setter
@Getter

public class MemberDTO implements UserDetails{


    public String userNo;
    public String userId;
    public String userNickname;
    public String userPwd;
    public String memberName;
    public Date birthday;
    public String gender;
    public String address;
    public String email;
    public String phone;
    public String regDate;
    public String status;
    public String userRole;

    public MemberDTO() {}



    public MemberDTO(String userNo, String userId, String userNickname, String userPwd, String memberName, Date birthday, String gender, String address, String email, String phone, String regDate, String status, String userRole) {
        this.userNo = userNo;
        this.userId = userId;
        this.userNickname = userNickname;
        this.userPwd = userPwd;
        this.memberName = memberName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.regDate = regDate;
        this.status = status;
        this.userRole = userRole;
    }




    @Override
    public String getPassword() {
        return this.userPwd;
    }
    // 사용자의 Password를 반환한다.
    @Override
    public String getUsername() {
        return this.userId; //Id를  반환해야함 name아닌거 주의
    }
    // 사용자의 ID를 반환한다. (Unique하기에 해당 프로젝트에서는 Id를 반환한다.)
    // Security에서 인증처리를 하기 위해 userId를 지정해주는 것.


    public String getUMemberName() {
        return this.memberName; //Id를  반환해야함 name아닌거 주의
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;// true:만료되지 않음, false:만료됨
        // 계정의 만료 여부를 확인한다.
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.userRole));
        //사용자 권한을 Collection 형태로 반환
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;// true:잠기지 않음, false:잠김
    }
    // 계정의 잠금 여부를 확인한다.

    @Override
    public boolean isCredentialsNonExpired() {
        return true;// true:만료되지 않음, false:만료됨
    }
    // 비밀번호의 만료 여부를 확인한다.
    @Override
    public boolean isEnabled() {
        return true;// true:사용 가능, false:사용 불가
    }

    public void updatePassword(String newPassword) {
        this.userPwd = newPassword;
    }

    // 계정이 사용 가능한지 확인한다.
}