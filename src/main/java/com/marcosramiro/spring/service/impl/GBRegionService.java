package com.marcosramiro.spring.service.impl;

import org.springframework.stereotype.Service;

import com.marcosramiro.spring.service.RegionService;

@Service("GBregionService")
public class GBRegionService implements RegionService {
	
	public GBRegionService() {
		System.out.println("Construtor GBRegionService");
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
