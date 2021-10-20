package com.marcosramiro.spring.service;

public interface  RegionService {
	
    boolean isServerActive(int serverId);

    String getISOCountryCode();
    
    boolean isResponsibleFor(String isoCountryCode);

}
