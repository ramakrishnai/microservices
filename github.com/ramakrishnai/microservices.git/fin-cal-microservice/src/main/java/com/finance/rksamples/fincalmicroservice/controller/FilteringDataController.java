package com.finance.rksamples.fincalmicroservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.finance.rksamples.fincalmicroservice.model.SampleDataBean;

@RestController
public class FilteringDataController {

	
//	@GetMapping(path="/static-filtering-list") //Static Filtering: field1, field2 are JsonIgnored in Bean
//	public SampleDataBean getStaticFilterSampleData() {
//		
//		return new SampleDataBean("Static-value1", "Static-value2", "Static-value3");
//	}
	
	
	//http://localhost:8084/dynamic-filter
	@GetMapping(path="/dynamic-filter") //Dynamic Filtering for some requests some props to be hidden, and some requests full response
	public MappingJacksonValue getDynamicFilterSampleData() {
		SampleDataBean sampleDataBean = new SampleDataBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SampleDataBeanFilter", filter);//define filter inSampleDataBean using @JsonFilter - SampleDataBeanFilter 
		MappingJacksonValue mapping = new MappingJacksonValue(sampleDataBean);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}
	
	
	//http://localhost:8084/dynamic-filtered-list
	@GetMapping(path="/dynamic-filtered-list") //Dynamic Filtering for list of objects values in response
	public MappingJacksonValue getFullSampleData() {
		List<SampleDataBean> list = Arrays.asList(new SampleDataBean("value1", "value2", "value3"),
				  								  new SampleDataBean("value11", "value22", "value33"));
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SampleDataBeanFilter", filter);//define filter inSampleDataBean using @JsonFilter - SampleDataBeanFilter 
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		
		return mapping;
	}
	
	
	
}
