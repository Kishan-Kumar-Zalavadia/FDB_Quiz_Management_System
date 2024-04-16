package com.fdb.backend.Services;

import com.fdb.backend.Entities.Announcement;
import com.fdb.backend.Repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement addAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }
    public List<Announcement> getAnnouncementsByUserId(int userId) {
        return announcementRepository.findByUserUserID(userId);
    }

    public List<Announcement> getAllAnnouncementsByUserIdAndCourseId(int userId, int courseId) {
        return announcementRepository.findByUserUserIDAndCourseCourseId(userId, courseId);
    }

    public List<Announcement> getAllAnnouncementsByCourseId(int courseId) {
        return announcementRepository.findByCourseCourseId(courseId);
    }
}