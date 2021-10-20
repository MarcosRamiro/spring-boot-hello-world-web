package com.marcosramiro.spring.service.impl;

import org.springframework.stereotype.Service;

import com.marcosramiro.spring.service.RegionService;

@Service("USregionService")
public class USRegionService implements RegionService {
    
	public USRegionService() {
		System.out.println("Construtor GBRegionService");
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
