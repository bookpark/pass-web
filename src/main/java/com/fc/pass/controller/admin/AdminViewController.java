package com.fc.pass.controller.admin;

import com.fc.pass.service.packagee.PackageService;
import com.fc.pass.service.pass.BulkPassService;
import com.fc.pass.service.statistics.StatisticsService;
import com.fc.pass.service.user.UserGroupMappingService;
import com.fc.pass.util.LocalDateTimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    private final BulkPassService bulkPassService;
    private final PackageService packageService;
    private final UserGroupMappingService userGroupMappingService;
    private final StatisticsService statisticsService;

    public AdminViewController(BulkPassService bulkPassService, PackageService packageService, UserGroupMappingService userGroupMappingService, StatisticsService statisticsService) {
        this.bulkPassService = bulkPassService;
        this.packageService = packageService;
        this.userGroupMappingService = userGroupMappingService;
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ModelAndView home(ModelAndView modelAndView, @RequestParam("to") String toString) {
        LocalDateTime to = LocalDateTimeUtils.parseDate(toString);

        //ChartData 조회
        modelAndView.addObject("chartData", statisticsService.makeChartData(to));
        modelAndView.setViewName("admin/index");
        return modelAndView;
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
