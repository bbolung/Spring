package com.project.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.project.mapper.PerformanceMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.domain.Criteria;
import com.project.domain.PerformanceVO;
import com.project.mapper.PerformanceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class PerformanceServiceImpl implements PerformanceService{

	private final PerformanceMapper mapper;
	
	@Override
	public List<PerformanceVO> fetchPerformances() {
		List<PerformanceVO> list = new ArrayList<>();

        try {
            String apiUrl = "http://openapi.seoul.go.kr:8088/6b6159777a6b796f37364c66715763/json/culturalEventInfo/1/20/";

            URL url = new URL(apiUrl);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(sb.toString());

                JsonNode items = root.path("culturalEventInfo").path("row");

                for (JsonNode node : items) {
                    PerformanceVO vo = new PerformanceVO();
                    vo.setEventName(node.path("TITLE").asText());
                    vo.setCategory(node.path("CODENAME").asText());
                    vo.setPlace(node.path("PLACE").asText());
                    vo.setImage(node.path("MAIN_IMG").asText());
                    vo.setHomePage(node.path("ORG_LINK").asText());
                    vo.setUserTrgt(node.path("USE_TRGT").asText());
                    vo.setPeriod(node.path("DATE").asText());
                    vo.setPaid(node.path("IS_FREE").asText());
                    vo.setFee(node.path("USE_FEE").asText());
                    vo.setImgKey(extractImgKey(vo.getImage()));
                    list.add(vo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // 실무에서는 logger로 교체 권장
        }

        return list;
    }

	@Override
	public String extractImgKey(String imageUrl) {
		 if (imageUrl == null) return null;
	        try {
	            java.net.URL url = new java.net.URL(imageUrl);
	            String query = url.getQuery();
	            if (query == null) return null;

	            String[] params = query.split("&");
	            for (String param : params) {
	                String[] keyValue = param.split("=");
	                if (keyValue.length == 2 && "atchFileId".equals(keyValue[0])) {
	                    return keyValue[1];
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	@Override
	public PerformanceVO findByImgKey(String imgKey) {
		return mapper.selectByImgKey(imgKey);
	}

	@Override
	public PerformanceVO getOrSavePerformance(String imgKey) {
		PerformanceVO vo = mapper.selectByImgKey(imgKey);
        if (vo == null) {
            List<PerformanceVO> apiList = fetchPerformances();
            for (PerformanceVO p : apiList) {
                if (imgKey.equals(p.getImgKey())) {
                    mapper.insert(p);
                    break;
                }
            }
            vo = mapper.selectByImgKey(imgKey);
        }
		return vo;
	}

	@Override
	public List<PerformanceVO> getList(Criteria cri) {
		log.info("getList.........");
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("getTotal...........");
		return mapper.getTotalCount(cri);
	}


	
	

}
