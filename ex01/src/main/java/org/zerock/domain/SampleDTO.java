package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @Builder 사용 시에는 @AllArgsConstructor, @NoArgsConstructor 세트로 기입!
// @Builder : 객체를 좀 더 직관적으로 사용O
@Builder		
@AllArgsConstructor
@NoArgsConstructor
public class SampleDTO {

	private String name;
	private int age;
	
	/*
	 * @AllArgsConstructor
	 	public SampleDTO(String name, int age) {
	 		this.name = name;
	 		this.age = age;
	 	}
	 
	  * @Builder
	  	public SampleDTO {}
	 */
}
