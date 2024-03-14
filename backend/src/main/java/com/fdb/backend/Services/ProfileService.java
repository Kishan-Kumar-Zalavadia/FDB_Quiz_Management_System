package com.fdb.backend.Services;

import com.fdb.backend.Entities.Profile;
import com.fdb.backend.Repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    // ---------------------------------------------------------------------------------------------------
    public List<Profile> fetchAllProfiles() {
        return profileRepository.findAll();
    }

    // ---------------------------------------------------------------------------------------------------
    public Profile fetchProfileByProfileID(int profileID) {
        return profileRepository.findByProfileID(profileID);
    }

}