package org.zerock.sample;

import org.springframework.stereotype.Component;

//@Component 어노테이션 입력해야 객체 생성됨(root-context의 객체 생성 코드 때문)
//@Component : 단순 bean만 등록(객체 생성)
@Component
public class Chef {

	public void eat() {
		System.out.println("점심 뭐 먹었지? (붕어)");
	}
}
