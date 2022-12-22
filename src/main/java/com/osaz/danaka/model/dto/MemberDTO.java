package com.osaz.danaka.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
//Data 어노테이션은 총 5개, Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode 어노테이션이 합쳐져 있습니다.
//@RequiredArgsConstructor / final 변수, Notnull 표시가 된 변수처럼 필수적인 정보를 세팅하는 생성자를 만들어준다.
@NoArgsConstructor
//기본 생성자를 생성해준다. 초기값 세팅이 필요한 final 변수가 있을 경우 컴파일 에러가 발생, (force=true) 를 사용하면 null, 0 등 기본 값으로 초기화 된다.
@AllArgsConstructor
//전체 변수를 생성하는 생성자를 만들어준다.
public class MemberDTO {

    public int userNo;
    public String userId;
    public String userNickname;
    public String userPwd;
    public String userName;
    public Date birthday;
    public char gender;
    public String adress;
    public String phone;
    public Date regDate;
    public char status;
    public char userRole;


}
