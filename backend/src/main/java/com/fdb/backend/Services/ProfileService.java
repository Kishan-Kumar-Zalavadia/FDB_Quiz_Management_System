package com.fdb.backend.Services;

import com.fdb.backend.Entities.Profile;
import com.fdb.backend.Repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepo repo;
    public List<Profile> fetchAllProfiles() {
        return repo.findAll();
    }

    public Profile fetchProfileByProfileID(int profileID) {
        return repo.findByProfileID(profileID);
    }

}