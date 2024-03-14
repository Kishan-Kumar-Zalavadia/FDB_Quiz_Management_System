package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Profile;
import com.fdb.backend.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
