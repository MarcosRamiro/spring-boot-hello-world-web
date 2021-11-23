package com.marcosramiro.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.marcosramiro.spring.service.RegionService;

@Service("USregionService")
public class USRegionService implements RegionService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(USRegionService.class);
    
	public USRegionService() {
		LOGGER.info("Construtor USRegionService");
	}
	
	@Override
    public boolean isServerActive(int serverId) {
        return true;
    }

    @Override
    public String getISOCountryCode() {
        return "USA!";
    }
    
    @Override
	public boolean isResponsibleFor(String isoCountryCode) {
		return "US".equals(isoCountryCode);
	}
}
