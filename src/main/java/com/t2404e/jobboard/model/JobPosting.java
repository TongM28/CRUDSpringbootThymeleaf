package com.t2404e.jobboard.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "job_postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tiêu đề không được để trống")
    @Size(min = 5, message = "Tiêu đề phải có ít nhất 5 ký tự")
    @Column(nullable = false)
    private String title;

    @NotEmpty(message = "Mô tả không được để trống")
    @Lob // Dùng cho kiểu TEXT (dữ liệu lớn)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotEmpty(message = "Địa điểm không được để trống")
    @Column(nullable = false)
    private String location;

    @Column
    private String salary;

    @NotNull(message = "Loại công việc không được để trống")
    @Enumerated(EnumType.STRING) // Lưu Enum dưới dạng String trong DB
    @Column(nullable = false)
    private JobType jobType;

    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;

    // Mối quan hệ Many-to-One
    // FetchType.LAZY: chỉ tải thông tin Company khi được gọi
    // @JoinColumn: chỉ định cột khóa ngoại trong bảng 'job_postings'
    @NotNull(message = "Bạn phải chọn một công ty")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // Tự động gán ngày hiện tại khi tạo mới
    @PrePersist
    protected void onCreate() {
        if (this.postedDate == null) {
            this.postedDate = LocalDate.now();
        }
    }

    // Constructors
    public JobPosting() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
