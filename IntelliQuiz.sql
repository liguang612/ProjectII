CREATE DATABASE INTELLIQUIZ
GO
USE INTELLIQUIZ
GO

CREATE TABLE LOGIN (
    ID INT IDENTITY(1, 1),
    USERNAME VARCHAR(50) NOT NULL,
    [PASSWORD] VARCHAR(50) NOT NULL,
)
ALTER TABLE [LOGIN] ADD CONSTRAINT pk_login PRIMARY KEY(ID)
ALTER TABLE [LOGIN] ADD CONSTRAINT unique_username UNIQUE(USERNAME)

CREATE TABLE ACCOUNT (
    ID INT NOT NULL,
    [ROLE] INT NOT NULL, -- 0: Học viên, 1: Giảng viên
    [NAME] NVARCHAR(50) NOT NULL,
    DOB DATETIME NOT NULL,
    PHONENUMBER CHAR(10),
    EMAIL VARCHAR(100),
    SCHOOL NVARCHAR(1000) NOT NULL,
    CLASS NVARCHAR(6),
    [IMAGE] VARBINARY(MAX)
)
ALTER TABLE ACCOUNT ADD CONSTRAINT pk_account PRIMARY KEY(ID)
ALTER TABLE ACCOUNT ADD CONSTRAINT fk_account_login FOREIGN KEY(ID) REFERENCES [LOGIN](ID)
ALTER TABLE ACCOUNT ADD CONSTRAINT check_phonenumber CHECK (PHONENUMBER LIKE '%[0-9]%')
ALTER TABLE ACCOUNT ADD CONSTRAINT check_dob CHECK (YEAR(DOB) < YEAR(GETDATE()) - 5)
ALTER TABLE ACCOUNT ADD CONSTRAINT unique_email UNIQUE(EMAIL)

ALTER TABLE ACCOUNT DROP CONSTRAINT unique_email

CREATE TABLE EXAM (
    ID INT IDENTITY(1, 1),
    [NAME] NVARCHAR(2000) NOT NULL,
    [DESCRIPTION] NVARCHAR(MAX),
    OPENTIME DATETIME NOT NULL,
    CLOSETIME DATETIME NOT NULL,
    [SUBJECT] NVARCHAR(500),
    DURATION INT,
    CANREPEAT INT NOT NULL,
    CANREVIEWED BIT NOT NULL,
    TOTAL FLOAT NOT NULL, -- Tổng điểm
    EASIES INT NOT NULL,
    MEDIUMS INT NOT NULL,
    HARDS INT NOT NULL,
    TEACHER INT NOT NULL,
)
ALTER TABLE EXAM ADD CONSTRAINT pk_exam PRIMARY KEY(ID)
ALTER TABLE EXAM ADD CONSTRAINT fk_exam_account FOREIGN KEY(TEACHER) REFERENCES ACCOUNT(ID)
ALTER TABLE EXAM ADD CONSTRAINT check_opentime_closetime CHECK (CLOSETIME <= OPENTIME)
ALTER TABLE EXAM ADD CONSTRAINT check_duration CHECK (DURATION > 0 AND DURATION IS NOT NULL)
ALTER TABLE EXAM ADD CONSTRAINT check_total CHECK (TOTAL >= 0)
ALTER TABLE EXAM ADD CONSTRAINT check_numOfQuestions CHECK(EASIES >= 0 OR MEDIUMS >= 0 OR HARDS >= 0 OR EASIES + MEDIUMS + HARDS = 0)

CREATE TABLE QUESTION (
    ID INT IDENTITY(1,1),
    ASK NVARCHAR(MAX) NOT NULL,
    MEDIA VARBINARY(MAX),
    MARK FLOAT NOT NULL,
    [LEVEL] INT NOT NULL, -- 0: Dễ, 1: Trung bình, 2: Khó
    EXAM INT NOT NULL
)
ALTER TABLE QUESTION ADD CONSTRAINT pk_question PRIMARY KEY(ID)

ALTER TABLE QUESTION ADD CONSTRAINT fk_question_exam FOREIGN KEY (EXAM) REFERENCES EXAM(ID)
ALTER TABLE QUESTION ADD CONSTRAINT check_mark CHECK(MARK <= 0)
ALTER TABLE QUESTION ADD CONSTRAINT check_level CHECK ([LEVEL] < 1 OR [LEVEL] > 2)

CREATE TABLE CHOICE (
    ID INT IDENTITY(1, 1),
    [TEXT] NVARCHAR(MAX),
    MEDIA VARBINARY(MAX),
    ISCORRECT BIT NOT NULL,
    QUESTION INT NOT NULL
)
ALTER TABLE CHOICE ADD CONSTRAINT pk_choice PRIMARY KEY(ID)
ALTER TABLE CHOICE ADD CONSTRAINT fk_choice_question FOREIGN KEY(QUESTION) REFERENCES QUESTION(ID)
ALTER TABLE CHOICE ADD CONSTRAINT check_empty CHECK([TEXT] IS NULL AND MEDIA IS NULL)

CREATE TABLE ATTEMPT (
    ID INT IDENTITY(1, 1),
    EXAM INT NOT NULL,
    STUDENT INT NOT NULL,
    DURATION INT NOT NULL, -- Thời gian làm bài của sinh viên
    GRADE FLOAT NOT NULL, -- Điểm của lần thi
    TIMESTART DATETIME NOT NULL, -- Thời gian bắt đầu làm bài
    SUBMITTED BIT NOT NULL -- Xác định đã nộp bài chưa
)
ALTER TABLE ATTEMPT ADD CONSTRAINT pk_attempt PRIMARY KEY(ID)
ALTER TABLE ATTEMPT ADD CONSTRAINT fk_attempt_exam FOREIGN KEY(EXAM) REFERENCES EXAM(ID)
ALTER TABLE ATTEMPT ADD CONSTRAINT fk_attempt_student FOREIGN KEY(STUDENT) REFERENCES ACCOUNT(ID)

CREATE TABLE ATTEMPT_DETAIL (
    ATTEMPT INT NOT NULL,
    CHOICE INT NOT NULL -- Lựa chọn của học viên
)
ALTER TABLE ATTEMPT_DETAIL ADD CONSTRAINT pk_attemptDetail PRIMARY KEY(ATTEMPT, CHOICE)
ALTER TABLE ATTEMPT_DETAIL ADD CONSTRAINT fk_attemptDetail_attempt FOREIGN KEY(ATTEMPT) REFERENCES ATTEMPT(ID)
ALTER TABLE ATTEMPT_DETAIL ADD CONSTRAINT fk_attemptDetail_choice FOREIGN KEY(CHOICE) REFERENCES CHOICE(ID)