package com.t2404e.jobboard.controller;


import com.t2404e.jobboard.model.Company;
import com.t2404e.jobboard.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    // 1. Hiển thị danh sách (Read)
    @GetMapping
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        return "companies/list";
    }

    // 2. Hiển thị form thêm mới (Create - Step 1)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("pageTitle", "Thêm Công ty mới");
        return "companies/form";
    }

    // 3. Xử lý lưu (Create - Step 2 & Update)
    @PostMapping("/save")
    public String saveCompany(@Valid @ModelAttribute("company") Company company,
                              BindingResult result, Model model) {
        // Kiểm tra validation
        if (result.hasErrors()) {
            // Nếu có lỗi, đặt lại pageTitle và trả về form
            String pageTitle = company.getId() == null ? "Thêm Công ty mới" : "Sửa Công ty";
            model.addAttribute("pageTitle", pageTitle);
            return "companies/form";
        }

        // Nếu không lỗi, lưu vào DB
        companyRepository.save(company);
        return "redirect:/companies";
    }

    // 4. Hiển thị form cập nhật (Update - Step 1)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));

        model.addAttribute("company", company);
        model.addAttribute("pageTitle", "Sửa Công ty");
        return "companies/form";
    }

    // 5. Xử lý xóa (Delete)
    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));

        companyRepository.delete(company);
        return "redirect:/companies";
    }
}