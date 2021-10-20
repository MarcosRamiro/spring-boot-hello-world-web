package com.marcosramiro.spring.service.impl;

import org.springframework.stereotype.Service;

import com.marcosramiro.spring.service.RegionService;

@Service("GBregionService")
public class GBRegionService implements RegionService {

	@Override
	public boolean isServerActive(int serverId) {
		return false;
	}

	@Override
	public String getISOCountryCode() {
		return "GB!";
	}

}
