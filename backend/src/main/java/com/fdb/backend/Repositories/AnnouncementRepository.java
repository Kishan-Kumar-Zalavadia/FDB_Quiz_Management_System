package com.fdb.backend.Repositories;

import com.fdb.backend.Entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    List<Announcement> findByUserUserID(int userId);

    List<Announcement> findByUserUserIDAndCourseCourseId(int userId, int courseId);

    List<Announcement> findByCourseCourseId(int courseId);
}