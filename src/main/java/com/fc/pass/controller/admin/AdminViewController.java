package com.fc.pass.controller.admin;

import com.fc.pass.service.packagee.PackageService;
import com.fc.pass.service.pass.BulkPassService;
import com.fc.pass.service.user.UserGroupMappingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    private final BulkPassService bulkPassService;
    private final PackageService packageService;
    private final UserGroupMappingService userGroupMappingService;

    public AdminViewController(BulkPassService bulkPassService, PackageService packageService, UserGroupMappingService userGroupMappingService) {
        this.bulkPassService = bulkPassService;
        this.packageService = packageService;
        this.userGroupMappingService = userGroupMappingService;
    }

    @GetMapping("/bulk-pass")
    public ModelAndView registerBulkPass(ModelAndView modelAndView) {
        // 목록 조회
        modelAndView.addObject("bulkPasses", bulkPassService.getAllBulkPasses());
        // 패키지 조회
        modelAndView.addObject("packages", packageService.getAllPackage());
        // 유저 그룹 ID 조회
        modelAndView.addObject("userGroupIds", userGroupMappingService.getAllUserGroupIds());
        // Request 조회
        modelAndView.addObject("request", new BulkPassRequest());
        modelAndView.setViewName("admin/bulk-pass");
        return modelAndView;
    }

    @PostMapping("/bulk-pass")
    public String addBulkPass(@ModelAttribute("request") BulkPassRequest request, Model model) {
        // 벌크 패스 생성
        bulkPassService.addBulkPass(request);
        return "redirect:/admin/bulk-pass";
    }

}
