-- Insert Users
INSERT INTO users (id, username, password, role, email) VALUES
          (1, 'user1', 'password1', 'USER', 'email@gmail.com'),
          (2, 'user2', 'password2', 'USER', 'mail@mail.com');

-- Insert Quizzes
INSERT INTO quizzes (id, title, description, creationDate, category, averageRating, ratingCount, ownerId) VALUES
          (1, 'General Knowledge Quiz', 'A quiz to test your general knowledge.', '01-01-2023', 'GENERAL_KNOWLEDGE', 4.5, 10, 1),
          (2, 'Science Quiz', 'A quiz to test your science knowledge.', '02-01-2023', 'SCIENCE', 4.0, 8, 2);

-- Insert Questions
INSERT INTO questions (id, text, type, quizId) VALUES
           (1, 'What is the capital of France?', 'MULTIPLE_CHOICE', 1),
           (2, 'Is the Earth flat?', 'TRUE_FALSE', 1),
           (3, 'Name the largest ocean on Earth.', 'SHORT_ANSWER', 1),
           (4, 'What is 2 + 2?', 'MULTIPLE_CHOICE', 1),
           (5, 'Is water wet?', 'TRUE_FALSE', 1),
           (6, 'What is the chemical symbol for water?', 'SHORT_ANSWER', 2),
           (7, 'Is the sun a star?', 'TRUE_FALSE', 2),
           (8, 'What planet is known as the Red Planet?', 'MULTIPLE_CHOICE', 2),
           (9, 'What is the speed of light?', 'SHORT_ANSWER', 2),
           (10, 'Is fire a solid?', 'TRUE_FALSE', 2);

-- Insert Answers
INSERT INTO answers (id, text, isCorrect, questionId) VALUES
          (1, 'Paris', true, 1),
          (2, 'London', false, 1),
          (3, 'No', true, 2),
          (4, 'Yes', false, 2),
          (5, 'Pacific Ocean', true, 3),
          (6, 'Atlantic Ocean', false, 3),
          (7, '4', true, 4),
          (8, '5', false, 4),
          (9, 'Yes', true, 5),
          (10, 'No', false, 5),
          (11, 'H2O', true, 6),
          (12, 'O2', false, 6),
          (13, 'Yes', true, 7),
          (14, 'No', false, 7),
          (15, 'Mars', true, 8),
          (16, 'Venus', false, 8),
          (17, '299,792 km/s', true, 9),
          (18, '150,000 km/s', false, 9),
          (19, 'No', true, 10),
          (20, 'Yes', false, 10);

-- Insert Results
INSERT INTO results (id, userId, quizId, score, date) VALUES
        (1, 1, 1, 80, '03-01-2023'),
        (2, 1, 2, 90, '04-01-2023'),
        (3, 2, 1, 70, '05-01-2023'),
        (4, 2, 2, 85, '06-01-2023'),
        (5, 1, 1, 75, '07-01-2023'),
        (6, 1, 2, 95, '08-01-2023'),
        (7, 2, 1, 60, '09-01-2023'),
        (8, 2, 2, 80, '10-01-2023'),
        (9, 1, 1, 85, '11-01-2023'),
        (10, 2, 2, 88, '12-01-2023');
