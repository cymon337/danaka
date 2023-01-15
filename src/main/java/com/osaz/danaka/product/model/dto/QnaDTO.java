package com.osaz.danaka.product.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class QnaDTO {

    private String qnaNo;
    private String userNo;
    private String productNo;
    private String userNickname;
    private String qnaBody;
    private String secretStatus;
    private Date regDate;
    private String reply;
    private Date replyDate;
}
