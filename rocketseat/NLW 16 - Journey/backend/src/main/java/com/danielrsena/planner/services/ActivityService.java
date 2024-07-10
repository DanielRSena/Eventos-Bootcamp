package com.danielrsena.planner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielrsena.planner.dtos.ActivitiesDataDTO;
import com.danielrsena.planner.dtos.ActivityCreatorDTO;
import com.danielrsena.planner.dtos.ActivityResponseDTO;
import com.danielrsena.planner.entities.Activity;
import com.danielrsena.planner.entities.Trip;
import com.danielrsena.planner.repositories.ActivityRepository;

import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public ActivityResponseDTO registerActivity(ActivityCreatorDTO newActivity, Trip trip) {

        Activity activity = new Activity(newActivity.title(), newActivity.occursAt(), trip);
        this.activityRepository.save(activity);
        return new ActivityResponseDTO(activity.getId());
    }

    public List<ActivitiesDataDTO> getAllActivitiesFromTripId(UUID tripId) {
        return this.activityRepository.findByTripId(tripId).stream().map(activity -> new ActivitiesDataDTO(activity.getId(), activity.getTitle(), activity.getOccursAt())).toList();
    }
}