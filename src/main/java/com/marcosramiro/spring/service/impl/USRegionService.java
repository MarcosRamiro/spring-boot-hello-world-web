package com.marcosramiro.spring.service.impl;

import org.springframework.stereotype.Service;

import com.marcosramiro.spring.service.RegionService;

@Service("USregionService")
public class USRegionService implements RegionService {
    @Override
    public boolean isServerActive(int serverId) {
        return true;
    }

    @Override
    public String getISOCountryCode() {
        return "USA!";
    }
}
