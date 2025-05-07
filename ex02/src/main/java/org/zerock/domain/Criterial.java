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
	
	//page와 개수 지정하지 않으면 기본 1페이지에 10개씩 조회
	public Criterial() {
		this(1,10);
	}
	
	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
