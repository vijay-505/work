package com.vijay.LinkedIn.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.CompanyDto;
import com.vijay.LinkedIn.entity.CompanyEntity;

@Component
public class CompanyMapper {
	
	public List<CompanyDto> toCompanyDtos(List<CompanyEntity> companies){
		List<CompanyDto> companyDtos = companies.stream().map(company -> 
				new ModelMapper().map(company, CompanyDto.class))
					.collect(Collectors.toList());
		return companyDtos;
	}

}
