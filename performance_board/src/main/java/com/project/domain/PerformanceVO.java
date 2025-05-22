package com.project.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceVO {
	private int pno;
	private String image;
	private String category;
	private String eventName;
    private String place;
    private String paid;
    private String fee;
    private String period;
    private String userTrgt;
    private String homePage;
    private String imgKey;
}
