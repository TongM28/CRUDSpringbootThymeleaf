package com.t2404e.jobboard.repository;

import com.t2404e.jobboard.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// <<< SỬA LỖI: Cần import List
import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    // <<< SỬA LỖI: Thêm các phương thức bị thiếu mà Controller đang gọi

    // Tìm tin tuyển dụng theo tiêu đề (không phân biệt hoa thường)
    List<JobPosting> findByTitleContainingIgnoreCase(String title);

    // Tìm tin tuyển dụng theo địa điểm (không phân biệt hoa thường)
    List<JobPosting> findByLocationContainingIgnoreCase(String location);

    // Tìm tất cả tin tuyển dụng của một công ty
    List<JobPosting> findByCompanyId(Long companyId);
}