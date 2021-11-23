package com.marcosramiro.spring.filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckClientRequestFilter implements ClientRequestFilter {

	private static Logger LOGGER = LoggerFactory.getLogger(CheckClientRequestFilter.class);
	
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {

		MultivaluedMap<String, Object> headers = requestContext.getHeaders();

		if (headers.get("Accept") == null) {

			LOGGER.info("Não tem Accept Header. Será Incluido o Accept.");
			headers.add("Accept", "application/json");
		}

		if (headers.get("Password") != null) {
			LOGGER.info("Possui Password no Header. Não pode.");
			requestContext.abortWith(
					Response.status(Response.Status.BAD_REQUEST).entity("Não incluir Password no header.").build());
		}
	}
}