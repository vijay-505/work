package com.vijay.LinkedIn.dto.mapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.LinkedIn.dto.model.ExperienceDto;
import com.vijay.LinkedIn.entity.ExperienceEntity;

@Component
public class ExperienceMapper {
	
	public List<ExperienceEntity> toSortListOfExperienceEntity(
			List<ExperienceEntity> experiences){
		List<ExperienceEntity> listOfExperienceEntity = new ArrayList<>();
		Map<ExperienceEntity, Date> map = new HashMap<>();
		experiences.forEach(experience -> {
				map.put(experience, experience.getEndDate());
		});
		map.entrySet().stream()
		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.forEachOrdered(entry -> {
			 ExperienceEntity experience = entry.getKey();
			 listOfExperienceEntity.add(experience);
		});
		return listOfExperienceEntity;
	}
	
	public List<ExperienceDto> toExperienceDtos(List<ExperienceEntity> experiences){
		List<ExperienceEntity> listOfExperienceEntity = toSortListOfExperienceEntity(experiences);
		List<ExperienceDto> experienceDtos = listOfExperienceEntity.stream().map(experience -> 
				new ModelMapper().map(experience, ExperienceDto.class))
					.collect(Collectors.toList());
		return experienceDtos;
	}

	public String getExperienceTimePeriod(Date endDate, Date startDate) {
		long diffInMillies = Math.abs(endDate.getTime()-
				startDate.getTime());
		long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		int months = (int) (days/30);
		int years = (int)(months/12);
		String timePeriod = "";
		if(months>=12) {
			timePeriod = years+" yr "+months%12+" mos";
		}
		else {
			timePeriod = 0+" yr "+months%12+" mos";
		}
		return timePeriod;
	}
	
}
