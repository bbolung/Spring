package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criterial {
	
	private int pageNum;	//page
	private int amount;		//페이지 개수
	
	private String type;	//검색 조건(제목, 내용, 작성자)
	private String keyword;
	
	//page와 개수 지정하지 않으면 기본 1페이지에 10개씩 조회
	public Criterial() {
		this(1,10);
	}
	
	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//다중 조건일 경우 type = CTW -> 하나씩 분리하여 배열에 저장(C, T, W)
	//sql구문에서 하나씩 조건 확인하기 위해서 분리
	public String[] getTypeArr() {
		return type==null ? new String[] {} : type.split("");
	}
}
