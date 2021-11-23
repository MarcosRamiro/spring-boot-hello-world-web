package com.marcosramiro.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.marcosramiro.spring.service.RegionService;

@Service("GBregionService")
public class GBRegionService implements RegionService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(USRegionService.class);
	
	public GBRegionService() {
		LOGGER.info("Construtor GBRegionService");
	}

	@Override
	public boolean isServerActive(int serverId) {
		return false;
	}

	@Override
	public String getISOCountryCode() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "GB!";
	}

	@Override
	public boolean isResponsibleFor(String isoCountryCode) {
		return "GB".equals(isoCountryCode);
	}

}
