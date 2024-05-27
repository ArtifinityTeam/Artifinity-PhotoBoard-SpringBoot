package org.teamproject.teamproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.teamproject.teamproject.Service.ProjectService;
import org.teamproject.teamproject.Vo.ProjectVo;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // 최신순으로 프로젝트 목록을 가져와서 화면에 전달
    @GetMapping("/projects/latest")
    public String latestProjects(Model model) {
        return "projectpg"; // projectpg.jsp로 이동
    }

    // 인기순으로 프로젝트 목록을 가져와서 화면에 전달
    @GetMapping("/projects/popular")
    public String popularProjects(Model model) {
        return "projectpg"; // projectpg.jsp로 이동
    }

    // 최신순으로 프로젝트 목록을 JSON 형태로 반환
    @GetMapping("/projects/latest-json")
    @ResponseBody
    public List<ProjectVo> latestProjectsJson() {
        return projectService.getAllProjects();
    }

    // 인기순으로 프로젝트 목록을 JSON 형태로 반환
    @GetMapping("/projects/popular-json")
    @ResponseBody
    public List<ProjectVo> popularProjectsJson() {
        return projectService.getPopularProjects();
    }

    // 최신순으로 배경화면 목록을 가져와서 화면에 전달
//    @GetMapping("/background_img")
//    public String latestBackgroundProjects(Model model) {
//        List<ProjectVo> projects = projectService.getAllBGProjects();
//        model.addAttribute("projects", projects);
//        return "background_img"; // 배경화면 페이지로 이동
//    }

    // 최신순으로 배경화면 목록을 JSON 형태로 반환
    @GetMapping("/background_img/latest-json")
    @ResponseBody
    public List<ProjectVo> latestBackgroundProjectsJson() {
        return projectService.getAllBGProjects();
    }

    // 인기순으로 배경화면 목록을 JSON 형태로 반환
    @GetMapping("/background_img/popular-json")
    @ResponseBody
    public List<ProjectVo> popularBackgroundProjectsJson() {
        return projectService.getPopularBGProjects();
    }
}