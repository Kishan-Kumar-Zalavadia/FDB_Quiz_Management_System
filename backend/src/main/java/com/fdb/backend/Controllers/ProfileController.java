package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Profile;
import com.fdb.backend.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

//----------------------------------------------------------------------------------------------------------------
// Get Profile by profileID

    @GetMapping("/{profileID}")
    public Profile getProfileById(@PathVariable int profileID) throws Exception{
        Profile profile = profileService.fetchProfileByProfileID(profileID);
        if (profile == null) {
            // Handle the case where the profile with the specified ID is not found.
            throw new Exception("Profile with ID " + profileID + " not found");
        }
        return profile;
    }

//----------------------------------------------------------------------------------------------------------------
// Get all Profiles
    @GetMapping("")
    public List<Profile> getAllProfiles(){
        return profileService.fetchAllProfiles();
    }

    //----------------------------------------------------------------------------------------------------------------
// Add Profile
    @PostMapping("/save")
    public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile) {
        Profile savedProfile = profileService.saveProfile(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    //----------------------------------------------------------------------------------------------------------------
// Update Profile
    @PutMapping("update/{profileID}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int profileID, @RequestBody Profile updatedProfile) {
        try {
            Profile profile = profileService.updateProfile(profileID, updatedProfile);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    //----------------------------------------------------------------------------------------------------------------
// Update Profile Department
    @PutMapping("/{profileId}/assignDepartment/{departmentId}")
    public ResponseEntity<Profile> assignDepartmentToProfile(@PathVariable int profileId, @PathVariable int departmentId) {
        try {
            Profile updatedProfile = profileService.assignDepartment(profileId, departmentId);
            return ResponseEntity.ok(updatedProfile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
