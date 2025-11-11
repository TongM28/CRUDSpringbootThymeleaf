package com.t2404e.jobboard.controller;

// <<< SỬA LỖI: Thêm tất cả các import bị thiếu
import com.t2404e.jobboard.model.Company;
import com.t2404e.jobboard.model.JobPosting;
import com.t2404e.jobboard.model.JobType;
import com.t2404e.jobboard.repository.CompanyRepository;
import com.t2404e.jobboard.repository.JobPostingRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// Bạn có thể cần import này nếu bạn dùng List
import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // <<< SỬA LỖI: Thêm phương thức loadFormData mà bạn đã gọi nhưng chưa định nghĩa
    // (Đây cũng là nơi sửa lỗi "enum types may not be instantiated")
    private void loadFormData(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("jobTypes", JobType.values()); // Lấy tất cả giá trị Enum
    }

    // 1. Hiển thị danh sách
    @GetMapping
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobPostingRepository.findAll());
        return "jobs/list"; // Đảm bảo bạn có file templates/jobs/list.html
    }

    // 2. Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("job", new JobPosting());
        model.addAttribute("pageTitle", "Đăng tin tuyển dụng mới");

        // <<< SỬA LỖI: Gọi hàm loadFormData
        loadFormData(model);
        return "jobs/form"; // Đảm bảo bạn có file templates/jobs/form.html
    }

    // 3. Xử lý lưu (Thêm mới & Cập nhật)
    @PostMapping("/save")
    public String saveJob(@Valid @ModelAttribute("job") JobPosting job, // <<< SỬA LỖI: Cần import @Valid
                          BindingResult result, // <<< SỬA LỖI: Cần import BindingResult
                          Model model) {

        // Kiểm tra validation
        if (result.hasErrors()) {
            // Nếu có lỗi, đặt lại pageTitle, load lại form data và trả về
            String pageTitle = job.getId() == null ? "Đăng tin tuyển dụng mới" : "Sửa tin tuyển dụng";
            model.addAttribute("pageTitle", pageTitle);

            // <<< SỬA LỖI: Gọi hàm loadFormData khi có lỗi
            loadFormData(model);
            return "jobs/form";
        }

        // <<< SỬA LỖI: Đảm bảo bạn lưu 'job' (kiểu JobPosting),
        // KHÔNG phải 'job.getJobType()' (kiểu JobType)
        jobPostingRepository.save(job);

        return "redirect:/jobs";
    }

    // 4. Hiển thị form cập nhật
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        JobPosting job = jobPostingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid job Id:" + id));

        model.addAttribute("job", job);
        model.addAttribute("pageTitle", "Sửa tin tuyển dụng");

        // <<< SỬA LỖI: Gọi hàm loadFormData
        loadFormData(model);
        return "jobs/form";
    }

    // 5. Xử lý xóa
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id) {
        JobPosting job = jobPostingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid job Id:" + id));

        jobPostingRepository.delete(job);
        return "redirect:/jobs";
    }

    // Lưu ý: Các phương thức tìm kiếm (findByTitle, findByLocation...)
    // mà bạn gọi ở dòng 31, 33, 35 sẽ cần được triển khai trong Controller.
    // Mã ở trên là mã CRUD cơ bản đã sửa lỗi.
}