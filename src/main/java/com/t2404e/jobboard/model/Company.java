package com.t2404e.jobboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên công ty không được để trống")
    @Size(max = 100, message = "Tên công ty không quá 100 ký tự")
    @Column(nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "Địa chỉ không được để trống")
    @Column(nullable = false)
    private String address;

    @URL(message = "Website phải là URL hợp lệ")
    @Column
    private String website;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email phải là địa chỉ email hợp lệ")
    @Column(nullable = false, unique = true)
    private String email;

    // Mối quan hệ One-to-Many
    // mappedBy="company": chỉ ra rằng 'company' là tên của trường
    // trong lớp JobPosting quản lý mối quan hệ này.
    // cascade = CascadeType.ALL: khi xóa, sửa, tạo Company thì
    // các JobPosting liên quan cũng sẽ bị ảnh hưởng.
    // orphanRemoval = true: khi một JobPosting bị xóa khỏi list
    // jobPostings, nó cũng sẽ bị xóa khỏi DB.
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<JobPosting> jobPostings = new ArrayList<>();

    // Constructors
    public Company() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<JobPosting> getJobPostings() {
        return jobPostings;
    }

    public void setJobPostings(List<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }

    // Helper methods (tùy chọn nhưng hữu ích)
    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
        jobPosting.setCompany(this);
    }

    public void removeJobPosting(JobPosting jobPosting) {
        jobPostings.remove(jobPosting);
        jobPosting.setCompany(null);
    }
}