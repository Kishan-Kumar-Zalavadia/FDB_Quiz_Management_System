package com.fdb.backend.Controllers;

import com.fdb.backend.Entities.Announcement;
import com.fdb.backend.Entities.Course;
import com.fdb.backend.Entities.User;
import com.fdb.backend.Services.AnnouncementService;
import com.fdb.backend.Services.CourseService;
import com.fdb.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, CourseService courseService, UserService userService) {
        this.announcementService = announcementService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping("/add/{courseId}/{userId}")
    public ResponseEntity<Announcement> addAnnouncement(@RequestBody Announcement announcementRequest, @PathVariable int courseId, @PathVariable int userId) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Fetch Course and User entities
        Course course = courseService.getCourseById(courseId).orElse(null);
        User user = userService.fetchUserByUserId(userId);

        // Check if Course and User exist
        if (course == null || user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Create new Announcement
        Announcement announcement = new Announcement();
        announcement.setContent(announcementRequest.getContent());
        announcement.setDate(currentDate);
        announcement.setTime(currentTime);
        announcement.setCourse(course);
        announcement.setUser(user);

        // Save Announcement
        Announcement newAnnouncement = announcementService.addAnnouncement(announcement);
        return new ResponseEntity<>(newAnnouncement, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Announcement>> getAnnouncementsByUserId(@PathVariable int userId) {
        List<Announcement> announcements = announcementService.getAnnouncementsByUserId(userId);
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<List<Announcement>> getAnnouncementsByUserIdAndCourseId(@PathVariable int userId, @PathVariable int courseId) {
        List<Announcement> announcements = announcementService.getAllAnnouncementsByUserIdAndCourseId(userId, courseId);
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Announcement>> getAnnouncementsByCourseId(@PathVariable int courseId) {
        List<Announcement> announcements = announcementService.getAllAnnouncementsByCourseId(courseId);
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }
}