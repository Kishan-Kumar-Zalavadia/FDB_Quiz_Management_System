import { Component } from '@angular/core';
import { Announcement } from 'src/app/models/announcementModel/announcement';
import { AnnouncementService } from 'src/app/services/announcementService/announcement.service';
import { CourseService } from 'src/app/services/courseService/course.service';
import { UserService } from 'src/app/services/userService/user.service';

@Component({
  selector: 'app-add-announcement',
  templateUrl: './add-announcement.component.html',
  styleUrls: ['./add-announcement.component.scss'],
})
export class AddAnnouncementComponent {
  announcementText!: string;
  announcement = new Announcement();
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

  addAnnouncement(): void {
    if (!this.announcementText.trim() || !this.courseId || !this.userId) {
      return;
    }
    this.announcement.content = this.announcementText;
    this.announcementService
      .addAnnouncement(this.announcement, this.courseId, this.userId)
      .subscribe(
        (response) => {
          console.log('Announcement added successfully:', response);
          // You can handle success response here
          this.announcementText = ''; // Clear input after successful addition
          this.fetchAnnouncements();
        },
        (error) => {
          console.error('Error adding announcement:', error);
          // You can handle error response here
          this.fetchAnnouncements();
        }
      );
      this.fetchAnnouncements();
  }
  fetchAnnouncements(): void {
    this.announcementService
      .getAnnouncementsByUserIdAndCourseId(this.userId, this.courseId)
      .subscribe(
        (response) => {
          this.announcements = response;
          console.log(
            'Announcements fetched successfully:',
            this.announcements
          );
        },
        (error) => {
          console.error('Error fetching announcements:', error);
        }
      );
  }
}
