package com.project.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	private int pageNum; //페이지
	private int amount;  //페이지 개수
	
	private String type; // 검색 조건: 제목(T), 장소(P), 카테고리(C)
	private String keyword; // 검색어
	
	// 1페이지에 10개
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type==null ? new String[] {} : type.split("");
	}
	
	
}
