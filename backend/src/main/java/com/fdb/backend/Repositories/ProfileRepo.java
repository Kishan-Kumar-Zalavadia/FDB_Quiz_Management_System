package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Profile;
import com.fdb.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends JpaRepository<Profile,Integer> {
    Profile findByProfileID(int profileID);
}
