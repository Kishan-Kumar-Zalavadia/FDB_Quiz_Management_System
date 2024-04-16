import { Component } from '@angular/core';
import { Announcement } from 'src/app/models/announcementModel/announcement';
import { AnnouncementService } from 'src/app/services/announcementService/announcement.service';
import { CourseService } from 'src/app/services/courseService/course.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.scss'],
})
export class AnnouncementsComponent {
  courseId!: number;
  userId!: number;
  announcements: Announcement[] = [];

  constructor(
    private announcementService: AnnouncementService,
    private userService: UserService,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    this.userId = this.userService.getUser().userID;
    this.courseId = this.courseService.getCourse().courseId;
    this.fetchAnnouncements();
  }
  fetchAnnouncements() {
    this.announcementService.getAnnouncementsByCourseId(this.courseId).subscribe(
      (response) => {
        this.announcements = response;
        console.log('Announcements:', this.announcements);
      },
      (error) => {
        console.error('Error fetching announcements:', error);
      }
    );
  }
}
