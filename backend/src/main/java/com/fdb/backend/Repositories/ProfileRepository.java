package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findByProfileID(int profileID);
}
