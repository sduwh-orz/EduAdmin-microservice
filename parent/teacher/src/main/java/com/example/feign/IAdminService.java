package com.example.feign;

import com.example.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("admin-service")
public interface IAdminService {
    @PostMapping("/examApplication/examApplication")
    public Response examapplication(
            @RequestParam String majorId,
            @RequestParam String courseId,
            @RequestParam String examFormat
    );
}
