-- Tệp này sẽ tự động chạy khi Spring Boot khởi động để thêm dữ liệu mẫu.

-- ------------------------------
-- 1. Thêm dữ liệu vào bảng COMPANIES
-- ------------------------------

-- Thêm Công ty 1
INSERT INTO COMPANIES (ID, NAME, ADDRESS, EMAIL, WEBSITE) VALUES
    (1, 'FPT Software', 'Hà Nội, Việt Nam', 'contact@fpt.com', 'https://www.fpt-software.com');

-- Thêm Công ty 2
INSERT INTO COMPANIES (ID, NAME, ADDRESS, EMAIL, WEBSITE) VALUES
    (2, 'Viettel Solutions', 'Tp. Hồ Chí Minh, Việt Nam', 'hr@viettel.vn', 'https://viettelsolutions.com');

-- Thêm Công ty 3
INSERT INTO COMPANIES (ID, NAME, ADDRESS, EMAIL, WEBSITE) VALUES
    (3, 'TMA Solutions', 'Đà Nẵng, Việt Nam', 'career@tma.com', 'https://www.tma.com.vn');


-- ------------------------------
-- 2. Thêm dữ liệu vào bảng JOB_POSTINGS
-- ------------------------------

-- Tin tuyển dụng 1: Lập trình viên Java (FULL_TIME, FPT Software)
INSERT INTO JOB_POSTINGS (ID, TITLE, DESCRIPTION, LOCATION, SALARY, POSTED_DATE, JOB_TYPE, COMPANY_ID) VALUES
    (101, 'Senior Java Developer', 'Yêu cầu 3+ năm kinh nghiệm với Spring Boot và Microservices.',
     'Hà Nội', '25.000.000 - 40.000.000 VNĐ', CURRENT_DATE(), 'FULL_TIME', 1);

-- Tin tuyển dụng 2: Tester (INTERNSHIP, Viettel Solutions)
INSERT INTO JOB_POSTINGS (ID, TITLE, DESCRIPTION, LOCATION, SALARY, POSTED_DATE, JOB_TYPE, COMPANY_ID) VALUES
    (102, 'Thực tập sinh QA/QC', 'Hỗ trợ kiểm thử phần mềm cho các dự án lớn của Viettel.',
     'Tp. Hồ Chí Minh', '4.000.000 VNĐ', DATEADD('DAY', -7, CURRENT_DATE()), 'INTERNSHIP', 2);

-- Tin tuyển dụng 3: Chuyên viên Phân tích Dữ liệu (REMOTE, FPT Software)
INSERT INTO JOB_POSTINGS (ID, TITLE, DESCRIPTION, LOCATION, SALARY, POSTED_DATE, JOB_TYPE, COMPANY_ID) VALUES
    (103, 'Data Analyst (Remote)', 'Phân tích dữ liệu khách hàng và đưa ra chiến lược kinh doanh.',
     'Làm việc từ xa (Remote)', '18.000.000 - 30.000.000 VNĐ', DATEADD('DAY', -3, CURRENT_DATE()), 'REMOTE', 1);

-- Tin tuyển dụng 4: Lập trình viên Front-end (PART_TIME, TMA Solutions)
INSERT INTO JOB_POSTINGS (ID, TITLE, DESCRIPTION, LOCATION, SALARY, POSTED_DATE, JOB_TYPE, COMPANY_ID) VALUES
    (104, 'Part-time Front-end Developer', 'Xây dựng giao diện bằng ReactJS trong môi trường linh hoạt.',
     'Đà Nẵng', 'Thỏa thuận theo giờ', DATEADD('DAY', -10, CURRENT_DATE()), 'PART_TIME', 3);