package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BartAPIController { 
	@Autowired
	RestTemplate restTemplate;

	 
	@RequestMapping(value = "/stations", method = RequestMethod.GET, produces = { "application/json" })
	Object getStations() throws JsonProcessingException {
		String theUrl = "http://api.bart.gov/api/stn.aspx?cmd=stns&key=MW9S-E7SL-26DU-VV8V&json=y";
		ResponseEntity<Object> response = restTemplate.exchange(theUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
        });
		Object body = response.getBody();
		return body;

	}
	
	@RequestMapping(value = "/station", method = RequestMethod.GET, produces = { "application/json" })
	Object getStation(@RequestParam(name="origin") String origin) {
		//String origin = request.getParameter("origin");
		String theUrl = "http://api.bart.gov/api/stn.aspx?cmd=stninfo&orig=" + origin + "&key=MW9S-E7SL-26DU-VV8V&json=y";
		ResponseEntity<Object> response = restTemplate.exchange(theUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
        });
		return response.getBody();
	}
	
	@RequestMapping(value="/trips", method = RequestMethod.GET, produces= {"application/json"})
	Object getFares(@RequestParam(name="source") String source, @RequestParam(name="destination") String destination) {
		String theUrl = "http://api.bart.gov/api/sched.aspx?cmd=depart&orig=" +source + "&dest=" + destination + "&date=now&key=MW9S-E7SL-26DU-VV8V&b=2&a=2&l=1&json=y";
		ResponseEntity<Object> response = restTemplate.exchange(theUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
        });
		return response.getBody();
	}

}
