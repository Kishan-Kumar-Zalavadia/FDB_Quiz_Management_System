package com.fdb.backend.Services;

import com.fdb.backend.Entities.Department;
import com.fdb.backend.Entities.Profile;
import com.fdb.backend.Repositories.DepartmentRepository;
import com.fdb.backend.Repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // ---------------------------------------------------------------------------------------------------
    public List<Profile> fetchAllProfiles() {
        return profileRepository.findAll();
    }

    // ---------------------------------------------------------------------------------------------------
    public Profile fetchProfileByProfileID(int profileID) {
        return profileRepository.findByProfileID(profileID);
    }

    // ---------------------------------------------------------------------------------------------------
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    // ---------------------------------------------------------------------------------------------------
    public Profile updateProfile(int profileID, Profile updatedProfile) throws Exception {
        Profile existingProfile = profileRepository.findById(profileID)
                .orElseThrow(() -> new Exception("Profile not found"));
        // Update the existing profile with the new data
        existingProfile.setFirstName(updatedProfile.getFirstName());
        existingProfile.setLastName(updatedProfile.getLastName());
        existingProfile.setPhoneNumber(updatedProfile.getPhoneNumber());
        existingProfile.setPhotoURL(updatedProfile.getPhotoURL());
        existingProfile.setStreetNumber(updatedProfile.getStreetNumber());
        existingProfile.setStreetName(updatedProfile.getStreetName());
        existingProfile.setAptNumber(updatedProfile.getAptNumber());
        existingProfile.setCity(updatedProfile.getCity());
        existingProfile.setState(updatedProfile.getState());
        existingProfile.setZip(updatedProfile.getZip());
        existingProfile.setCountry(updatedProfile.getCountry());
        existingProfile.setDob(updatedProfile.getDob());
        existingProfile.setAge(updatedProfile.getAge());
        existingProfile.setDepartment(updatedProfile.getDepartment());

        return profileRepository.save(existingProfile);
    }


    @Transactional
    public Profile assignDepartment(int profileId, int departmentID) throws Exception {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new Exception("Profile not found"));
        Department department = departmentRepository.findById(departmentID).orElseThrow(() -> new Exception("Department not found"));
        department.setDepartmentID(departmentID);
        profile.setDepartment(department);
        return profileRepository.save(profile);
    }
}