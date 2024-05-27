package org.teamproject.teamproject.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.teamproject.teamproject.Service.ProjectSearchService;
import org.teamproject.teamproject.Service.ProjectService;
import org.teamproject.teamproject.Service.UserService;
import org.teamproject.teamproject.Vo.ProjectVo;
import org.teamproject.teamproject.Vo.UserVo;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectSearchService projectSearchService;

    //private final CommentService commentService;
    // 생성자를 통해 UserService를 주입받습니다.
    @Autowired
    public HomeController(UserService userService, ProjectService projectService, /*CommentService commentService*/ProjectSearchService projectSearchService) {
        this.userService = userService;
        this.projectService = projectService;
        //this.commentService = commentService;
        this.projectSearchService = projectSearchService;
    }

/*
---------------------------------------------------------
                   5/7 병합 완
  if(userId == null) 부분 redirect:/ 경로 수정
  기존 login => Nmainpage로 변경
---------------------------------------------------------
 */

    // "/" 경로로 GET 요청이 들어왔을 때의 메소드입니다.
    @RequestMapping("/")
    public String home(@SessionAttribute("userId") Optional<Integer> userIdOptional, HttpSession session, Model model) {
        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Integer userId = userIdOptional.orElse(null);

        if (userId == null) {
            List<ProjectVo> projects = projectService.getAllProjects();
            model.addAttribute("projects", projects);
            return "/Nmainpage";
        } else {
            //로그인유저 정보
            UserVo userVo = userService.getUserById(userId);
            //DB에 올라가있는 모든 프로젝트 리스트
            List<ProjectVo> projects = projectService.getAllProjects();
            //List<CommentVo> comments = commentService.getAllComments();

            model.addAttribute("user", userVo);
            model.addAttribute("projects", projects);
            //model.addAttribute("comments", comments);
            return "/mainpg";
        }
    }

    // 검색 매핑
    @GetMapping("/search")
    public String search(@SessionAttribute("userId") Optional<Integer> userIdOptional, @RequestParam(value = "keyword", required = false) String keyword, Model model) {
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return "redirect:/login";
        }

        List<ProjectVo> projects;
        if (keyword != null && !keyword.trim().isEmpty()) {
            projects = projectSearchService.searchProjectsByName(keyword);
        } else {
            projects = projectService.getAllProjects(); // 전체 프로젝트 목록을 가져오는 메서드
        }

        UserVo userVo = userService.getUserById(userId);
        //DB에 올라가있는 모든 프로젝트 리스트

        model.addAttribute("projects", projects);
        model.addAttribute("user", userVo);

        return "mainpg"; // main.jsp를 가리키도록 합니다
    }
}
