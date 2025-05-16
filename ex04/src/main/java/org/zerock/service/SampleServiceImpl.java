package org.zerock.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

	@Override
	public void test() {
		System.out.println("1234567890");
	}

	@Override
	public Integer doMul(Integer n1, Integer n2) {
		System.out.println("===doMul===");		//먼저 출력 후 n1*n2 계산하는 동안 @After 먼저 출력
		return n1*n2;
	}

}
