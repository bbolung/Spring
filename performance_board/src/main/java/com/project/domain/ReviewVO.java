package com.project.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * create table tbl_performance(
    bno number(10,0),
    img_url varchar2(500) not null, 
    title varchar2(200) not null,
    writer varchar2(50) not null,
    readcount number(4) default 0,      -- 조회수
    regdate date default sysdate,       -- 작성일
    updatedate date default sysdate,     -- 수정일
    content varchar2(1000)
   );

 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {

	private Long bno;
	private String img;
	private String title;
	private String writer;
	private int readCount;
	private Date regDate;
	private Date updateDate;
	private String content;
}
