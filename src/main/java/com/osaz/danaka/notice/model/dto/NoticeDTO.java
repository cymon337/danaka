package com.osaz.danaka.notice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
	public int noticeNo;		// 공지번호
	public String userName;		// 작성자
	public String noticeName;	// 제목
	public String noticeContent;	// 내용
	public Date noticeDate;		// 작성일
	public int noticeCount;		// 조회수
}
