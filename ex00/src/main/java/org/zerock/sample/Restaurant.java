package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	
	@Autowired
	private Chef chef;
	
	void sample() {
		chef.eat();
	}
}
