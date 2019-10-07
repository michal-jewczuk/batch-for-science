package com.example.batchforscience.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.Location;
import com.example.batchforscience.domain.entities.LocationEntity;

@Component
public class LocationEntityMapper {
	
	LocationEntity mapToEntity(Location location) {
		LocationEntity entity = new LocationEntity();
		entity.setId(location.getId());
		entity.setCodeName(location.getCodeName());
		entity.setAddress(location.getAddress());
		entity.setPost(location.getPost());
		
		return entity;
	}
	
	Set<LocationEntity> mapToEntities(Set<Location> locations) {
		return locations.stream()
				.map(l -> mapToEntity(l))
				.collect(Collectors.toSet());
	}

}
