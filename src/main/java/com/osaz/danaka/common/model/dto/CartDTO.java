package com.osaz.danaka.common.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
//Data 어노테이션은 총 5개, Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode 어노테이션이 합쳐져 있습니다.
//@RequiredArgsConstructor / final 변수, Notnull 표시가 된 변수처럼 필수적인 정보를 세팅하는 생성자를 만들어준다.
//@NoArgsConstructor
//기본 생성자를 생성해준다. 초기값 세팅이 필요한 final 변수가 있을 경우 컴파일 에러가 발생, (force=true) 를 사용하면 null, 0 등 기본 값으로 초기화 된다.
//@AllArgsConstructor
//전체 변수를 생성하는 생성자를 만들어준다.
@Builder
public class CartDTO {

    public String cartNo;  //장바구니번호
    public String userNo;  //회원번호
    public String productNo;   //상품번호
    public String amount;  //수량
    public String packageId;   //패키지ID



}
