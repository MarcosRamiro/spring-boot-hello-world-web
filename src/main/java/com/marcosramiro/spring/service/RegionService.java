package com.marcosramiro.spring.service;

public interface  RegionService {
	
    boolean isServerActive(int serverId);

    String getISOCountryCode();

}
