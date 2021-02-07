package com.college.register.dto;

import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import com.college.register.constants.GeneralConstants;

public class StudentSearchParams {

	@Parameter(description = "city",in = ParameterIn.QUERY)
    @QueryParam(GeneralConstants.PARAM_CITY)
    private String city;
	
	@Parameter(description = "region",in = ParameterIn.QUERY)
    @QueryParam(GeneralConstants.PARAM_REGION)
    private String region;
	
	@Parameter(description = "country",in = ParameterIn.QUERY)
    @QueryParam(GeneralConstants.PARAM_COUNTRY)
    private String country;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
