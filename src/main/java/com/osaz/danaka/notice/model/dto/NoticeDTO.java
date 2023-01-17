package com.osaz.danaka.notice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
	public int noticeNo;
	public String userName;
	public String noticeName;
	public String noticeContent;
	public Date noticeDate;
	public int noticeCount;
}
