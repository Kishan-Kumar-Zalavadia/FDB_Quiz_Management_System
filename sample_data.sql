use qms;
-- NOTE: Execute Role and Department to run the project correctly

-- INSERT QUERIES

-- Role
INSERT INTO role (roleid, role_type) VALUES
(1, 'Student'),
(2, 'Professor');


-- Department
INSERT INTO department (departmentid, department_name) VALUES
(1, 'Computer Science'),
(2, 'Data Science'),
(3, 'Artificial Intelligence'),
(4, 'Chemistry'),
(5, 'Biology'),
(6, 'English'),
(7, 'History'),
(8, 'Economics'),
(9, 'Psychology'),
(10, 'Sociology');

-- NOTE: You will not be able to run these queries directly from SQL because of cardinalities

-- User
INSERT INTO user (userid, emailid, password, user_name, profileid, roleid) VALUES
(1, 'user1@example.com', 'password1', 'User One', 1, 1),
(2, 'user2@example.com', 'password2', 'User Two', 2, 1),
(3, 'user3@example.com', 'password3', 'User Three', 3, 1),
(4, 'user4@example.com', 'password4', 'User Four', 4, 1),
(5, 'user5@example.com', 'password5', 'User Five', 5, 1),
(6, 'user6@example.com', 'password6', 'User Six', 6, 2),
(7, 'user7@example.com', 'password7', 'User Seven', 7, 2),
(8, 'user8@example.com', 'password8', 'User Eight', 8, 2),
(9, 'user9@example.com', 'password9', 'User Nine', 9, 2),
(10, 'user10@example.com', 'password10', 'User Ten', 10, 2);


-- Profile
INSERT INTO profile (profileid, apt_number, city, country, dob, first_name, last_name, phone_number, state, street_name, street_number, zip, departmentid) VALUES
(1, 'Apt 101', 'New York', 'USA', '1990-05-15', 'John', 'Doe', 1234567890, 'NY', 'Main St', '123', '10001', 1),
(2, '', 'Los Angeles', 'USA', '1985-02-28', 'Jane', 'Smith', 9876543210, 'CA', 'Maple Ave', '456', '90001', 2),
(3, 'Unit 3B', 'Chicago', 'USA', '1992-11-03', 'Michael', 'Johnson', 5551234567, 'IL', 'Oak Rd', '789', '60601', 3),
(4, '', 'Houston', 'USA', '1988-07-20', 'Emily', 'Williams', 7890123456, 'TX', 'Pine Blvd', '246', '77001', 4),
(5, 'Apt 502', 'Phoenix', 'USA', '1995-03-10', 'David', 'Brown', 1112223333, 'AZ', 'Cedar St', '135', '85001', 5),
(6, '', 'Philadelphia', 'USA', '1982-09-25', 'Sarah', 'Davis', 4445556666, 'PA', 'Elm Ave', '678', '19101', 1),
(7, 'Unit 7A', 'San Antonio', 'USA', '1993-04-18', 'Daniel', 'Miller', 7778889999, 'TX', 'Maple Ln', '246', '78201', 2),
(8, '', 'San Diego', 'USA', '1987-12-31', 'Jessica', 'Wilson', 1230987654, 'CA', 'Oak Dr', '369', '92101', 3),
(9, 'Apt 305', 'Dallas', 'USA', '1991-06-12', 'Christopher', 'Anderson', 5679012345, 'TX', 'Pine Rd', '159', '75001', 4),
(10, '', 'San Jose', 'USA', '1989-01-08', 'Ashley', 'Thomas', 8902345678, 'CA', 'Cedar Ave', '753', '95101', 5);

-- Department_Course
INSERT INTO department_course (course_id, department_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 2),
(7, 3),
(8, 4),
(9, 5),
(10, 1);

-- Feedback
INSERT INTO feedback (feedbackid, feedback_date, feedback_text, feedback_time, userid) VALUES
(1, '2023-04-18 10:30:00', 'The app is very user-friendly and intuitive.', '10:30:00', 1),
(2, '2023-04-17 14:45:30', 'I encountered a bug when trying to upload a file.', '14:45:30', 2),
(3, '2023-04-16 09:15:00', 'The app could use more customization options.', '09:15:00', 3),
(4, '2023-04-15 16:20:45', 'The app is fast and responsive.', '16:20:45', 4),
(5, '2023-04-14 11:00:00', 'The app lacks some important features I need.', '11:00:00', 5),
(6, '2023-04-13 13:30:15', 'The app has a clean and modern design.', '13:30:15', 6),
(7, '2023-04-12 17:45:30', 'The app drains my battery too quickly.', '17:45:30', 7),
(8, '2023-04-11 08:00:00', 'The app is a game-changer for productivity.', '08:00:00', 8),
(9, '2023-04-10 12:15:45', 'The app could use better integration with other apps.', '12:15:45', 9),
(10, '2023-04-09 10:30:00', 'Overall, I am satisfied with this app.', '10:30:00', 10);

-- Course
INSERT INTO course (course_id, course_name, professor_userid) VALUES
(1, 'Introduction to Computer Science', 6),
(2, 'Data Structures and Algorithms', 6),
(3, 'Database Management Systems', 7),
(4, 'Web Development', 8),
(5, 'Artificial Intelligence', 8),
(6, 'Operating Systems', 9),
(7, 'Computer Networks', 8),
(8, 'Software Engineering', 8),
(9, 'Cyber Security', 9),
(10, 'Mobile App Development', 10);

-- User_Course
INSERT INTO user_course (user_id, course_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10);


-- Quiz
INSERT INTO quiz (quiz_id, access_code, created_date, end_date, end_time, instruction, quiz_duration, quiz_number_of_attempts, quiz_title, course_course_id) VALUES
(1, 'abc123', '2024-04-19', '2024-04-30', '18:00:00', 'Read all questions carefully before answering.', 60, 3, 'Quiz 1', 1),
(2, 'def456', '2024-04-20', '2024-05-01', '20:00:00', 'Complete the quiz within the given time limit.', 45, 2, 'Quiz 2', 2),
(3, 'ghi789', '2024-04-21', '2024-05-02', '15:30:00', 'No cheating allowed during the quiz.', 75, 1, 'Quiz 3', 3),
(4, 'jkl012', '2024-04-22', '2024-05-03', '17:45:00', 'Ensure you submit your answers before the deadline.', 60, 2, 'Quiz 4', 4),
(5, 'mno345', '2024-04-23', '2024-05-04', '14:00:00', 'You have only one attempt for this quiz.', 30, 1, 'Quiz 5', 5),
(6, 'pqr678', '2024-04-24', '2024-05-05', '19:00:00', 'All questions are multiple-choice.', 45, 3, 'Quiz 6', 6),
(7, 'stu901', '2024-04-25', '2024-05-06', '16:30:00', 'Make sure to review your answers before submission.', 60, 2, 'Quiz 7', 7),
(8, 'vwx234', '2024-04-26', '2024-05-07', '13:45:00', 'Contact your instructor if you encounter any technical issues.', 90, 1, 'Quiz 8', 8),
(9, 'yza567', '2024-04-27', '2024-05-08', '21:00:00', 'This quiz covers topics from chapters 1 to 5.', 60, 2, 'Quiz 9', 9),
(10, 'bcd890', '2024-04-28', '2024-05-09', '18:15:00', 'You cannot go back to previous questions once answered.', 45, 1, 'Quiz 10', 10);


-- Question
INSERT INTO question (question_id, question_marks, question_text, quiz_id) VALUES
(1, 5, 'What is the capital of France?', 1),
(2, 10, 'Explain the process of photosynthesis.', 2),
(3, 8, 'Calculate the area of a circle with a radius of 3 units.', 3),
(4, 12, 'Describe the main features of the Renaissance period.', 4),
(5, 7, 'Write a function in Python to reverse a string.', 5),
(6, 15, 'Discuss the impacts of climate change on ecosystems.', 6),
(7, 10, 'Analyze the theme of love in Shakespeare''s sonnets.', 7),
(8, 6, 'Simplify the following algebraic expression: 3x^2 + 2x - 5.', 8),
(9, 14, 'Compare and contrast the political systems of the United States and the United Kingdom.', 9),
(10, 9, 'Name three common data structures in computer science and their use cases.', 10);

-- Options
INSERT INTO options (correct, option_name, question_id)
VALUES
    (1, 'Paris', 1),
    (0, 'London', 1),
    (0, 'Berlin', 1),
    (0, 'Madrid', 1),
    (1, 'Conversion of light energy into chemical energy', 2),
    (0, 'Respiration in plants', 2),
    (0, 'Reproduction in plants', 2),
    (0, 'Growth of plants', 2),
    (1, '28.27 square units', 3),
    (0, '18.85 square units', 3),
    (0, '37.70 square units', 3),
    (0, '12.57 square units', 3),
    (1, 'Rebirth of art, literature, and philosophy', 4),
    (0, 'Industrial Revolution', 4),
    (0, 'Age of Exploration', 4),
    (0, 'Reformation', 4),
    (1, 'def reverse_string(s): return s[::-1]', 5),
    (0, 'def reverse_string(s): return s.reverse()', 5),
    (0, 'def reverse_string(s): return reversed(s)', 5),
    (0, 'def reverse_string(s): return ''.join(reversed(s))', 5);

-- Quiz_Attmpt_Option
INSERT INTO quiz_attempt_option (quiz_attempt_id, option_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 5),
(2, 6),
(3, 1),
(3, 4),
(4, 2),
(4, 3),
(5, 5);

-- Quiz_Feedback
INSERT INTO quiz_feedback (id, date, text, time, quiz_quiz_id, user_userid) VALUES
(1, '2024-04-19', 'Great quiz, very informative!', '10:30:00', 1, 101),
(2, '2024-04-20', 'The quiz was challenging but fair.', '11:45:00', 2, 102),
(3, '2024-04-21', 'I struggled with some questions, need more practice.', '09:15:00', 3, 103),
(4, '2024-04-22', 'The instructions were clear, thanks!', '13:00:00', 4, 104),
(5, '2024-04-23', 'I enjoyed the quiz format, looking forward to the next one.', '14:45:00', 5, 105),
(6, '2024-04-24', "The quiz duration was too short, couldn't finish on time.", '16:20:00', 6, 106),
(7, '2024-04-25', 'Some questions were ambiguous, could use clarification.', '17:55:00', 7, 107),
(8, '2024-04-26', 'Overall, a good experience. Thank you!', '18:30:00', 8, 108),
(9, '2024-04-27', 'The quiz covered relevant topics, helped reinforce learning.', '19:10:00', 9, 109),
(10, '2024-04-28', 'I encountered technical issues during the quiz.', '20:00:00', 10, 110);


-- Media
INSERT INTO media (media_id, media_link, question_id) VALUES
(1, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 1),
(2, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 2),
(3, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 3),
(4, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 4),
(5, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 5),
(6, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 6),
(7, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 7),
(8, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 8),
(9, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 9),
(10, 'https://www.programiz.com/sites/tutorial2program/files/Bubble-sort-0.png', 10);

-- Attempt
INSERT INTO attempts (quiz_attempt_id, attempt_date, attempt_number, end_time, quiz_quiz_id, result_result_id, user_userid) VALUES
(1, '2023-04-18', 1, '10:30:00', 1, 1, 1),
(2, '2023-04-17', 2, '14:45:30', 2, 2, 2),
(3, '2023-04-16', 1, '09:15:00', 3, 3, 3),
(4, '2023-04-15', 3, '16:20:45', 1, 4, 4),
(5, '2023-04-14', 2, '11:00:00', 4, 5, 5),
(6, '2023-04-13', 1, '13:30:15', 2, 6, 6),
(7, '2023-04-12', 4, '17:45:30', 3, 7, 7),
(8, '2023-04-11', 2, '08:00:00', 1, 8, 8),
(9, '2023-04-10', 1, '12:15:45', 4, 9, 9),
(10, '2023-04-09', 3, '10:30:00', 2, 10, 10);

-- Result
INSERT INTO result (result_id, result_score) VALUES
(1, 10),
(2, 15),
(3, 10),
(4, 20),
(5, 14),
(6, 16),
(7, 20),
(8, 22),
(9, 18),
(10, 13);


-- Announcement 
INSERT INTO announcement (announcement_id, content, date, time, course_id, user_userid) VALUES
(1, 'This is the first announcement.', '2023-04-18', '10:30:00', 1, 1),
(2, 'Important update regarding the project deadline.', '2023-04-17', '14:45:30', 2, 3),
(3, 'Class cancelled for tomorrow.', '2023-04-16', '09:15:00', 3, 2),
(4, 'New reading assignment posted.', '2023-04-15', '16:20:45', 1, 4),
(5, 'Reminder: Midterm exam next week.', '2023-04-14', '11:00:00', 4, 1),
(6, 'Office hours changed for this week.', '2023-04-13', '13:30:15', 2, 5),
(7, 'Guest speaker scheduled for next month.', '2023-04-12', '17:45:30', 3, 3),
(8, 'Submission deadline extended by 2 days.', '2023-04-11', '08:00:00', 1, 2),
(9, 'Extra credit opportunity announced.', '2023-04-10', '12:15:45', 4, 4),
(10, 'Course materials updated on the website.', '2023-04-09', '10:30:00', 2, 1);