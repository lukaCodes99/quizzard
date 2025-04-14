-- Insert into UserEntity table
INSERT INTO users (id, username, password, email) VALUES (1, 'john_doe', 'password123', 'john.doe@example.com');
INSERT INTO users (id, username, password, email) VALUES (2, 'jane_doe', 'password456', 'jane.doe@example.com');

-- Insert into Quiz table
INSERT INTO quizzes (id, title, description) VALUES (1, 'Java Basics', 'A quiz on Java fundamentals');
INSERT INTO quizzes (id, title, description) VALUES (2, 'Spring Boot', 'A quiz on Spring Boot basics');

-- Insert into Question table
INSERT INTO questions (id, text, type, quizId) VALUES (1, 'What is Java?', 'MULTIPLE_CHOICE', 1);
INSERT INTO questions (id, text, type, quizId) VALUES (2, 'What is Spring Boot?', 'MULTIPLE_CHOICE', 2);

-- Insert into Answer table
INSERT INTO answers (id, text, isCorrect, questionId) VALUES (1, 'A programming language', true, 1);
INSERT INTO answers (id, text, isCorrect, questionId) VALUES (2, 'A database', false, 1);
INSERT INTO answers (id, text, isCorrect, questionId) VALUES (3, 'A framework', true, 2);
INSERT INTO answers (id, text, isCorrect, questionId) VALUES (4, 'An IDE', false, 2);

-- Insert into Result table
INSERT INTO results (resultID, score, correctAnswers, date, userID) VALUES (1, 85.0, 17, '2023-10-01', 1);
INSERT INTO results (resultID, score, correctAnswers, date, userID) VALUES (2, 90.0, 18, '2023-10-02', 2);