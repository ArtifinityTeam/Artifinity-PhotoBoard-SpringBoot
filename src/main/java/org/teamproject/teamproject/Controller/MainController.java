package org.teamproject.teamproject.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.teamproject.teamproject.Service.CreatorService;
import org.teamproject.teamproject.Service.NoticeService;
import org.teamproject.teamproject.Service.ProjectService;
import org.teamproject.teamproject.Service.UserService;
import org.teamproject.teamproject.Vo.NoticeVo;
import org.teamproject.teamproject.Vo.ProjectVo;
import org.teamproject.teamproject.Vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {

    private final UserService userService;
    private final ProjectService projectService;
    private final CreatorService creatorService;
    private final NoticeService noticeService;
    //private final CommentService commentService;
    // 생성자를 통해 UserService를 주입받습니다.
    @Autowired
    public MainController(UserService userService, ProjectService projectService, CreatorService creatorService, NoticeService noticeService) {
        this.userService = userService;
        this.projectService = projectService;
        this.creatorService = creatorService;
        this.noticeService = new NoticeService();
        //this.commentService = commentService;
    }

    //로고 클릭 페이지


    //프로젝트 페이지
    @RequestMapping("/projects")
    public String projectpg(@SessionAttribute("userId") Optional<Integer> userIdOptional, HttpSession session, Model model){

        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Integer userId = userIdOptional.orElse(null);

        if (userId == null) {
            return "redirect:/login";
        } else {
            UserVo userVo = userService.getUserById(userId);
            List<ProjectVo> projects = projectService.getAllProjects();
            //List<CommentVo> comments = commentService.getAllComments();

            model.addAttribute("user", userVo);
            model.addAttribute("projects", projects);
            //model.addAttribute("comments", comments);
            return "projectpg";
        }

    }

    //배경화면 페이지
    @RequestMapping("/background_img")
    public String background_img(@SessionAttribute("userId") Optional<Integer> userIdOptional, Model model){
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return "redirect:/login";
        }
        // 프로젝트
        List<ProjectVo> projects = projectService.getAllBGProjects();
            if(projects != null){
                model.addAttribute("projects", projects);
            }
        //유저
        Optional<UserVo> user = userService.selectbyId(userId);
        if (user.isPresent()) {
            UserVo loginUser = user.get();
            model.addAttribute("user", loginUser);
        } else {
            return "redirect:/login";
        }

        return "background_img";
    }
//    @RequestMapping("/background_img")
//    public String background_img(HttpSession session, Model model) {
//        Integer userId = (Integer) session.getAttribute("userId");
//        if (userId == null) {
//            return "redirect:/login";
//        }
//        List<ProjectVo> projects = projectService.getAllBGProjects();
//        if (projects != null) {
//            model.addAttribute("projects", projects);
//        }
//        Optional<UserVo> user = userService.selectbyId(userId);
//        if (user.isPresent()) {
//            UserVo loginUser = user.get();
//            model.addAttribute("user", loginUser);
//        } else {
//            return "redirect:/login";
//        }
//        return "background_img";
//    }



    //크리에이터 페이지
    @RequestMapping("/creators")
    public String creators(@SessionAttribute("userId") Optional<Integer> userIdOptional, Model model){
        Integer userId = userIdOptional.orElse(null);

        if (userId != null) {

            //크리에이터 리스트
            List<Map<String, Object>> CreatorList = creatorService.CreatorList();
            if(CreatorList != null){
                model.addAttribute("creatorList", CreatorList);
            }
            //유저 정보
            Optional<UserVo> user = userService.selectbyId(userId);
            if (user.isPresent()) {
                UserVo loginUser = user.get();
                model.addAttribute("user", loginUser);
            } else {
                return "redirect:/login";
            }
            return "creators";
        }
        return "redirect:/login";
    }

    //이벤트 베너 페이지
    @RequestMapping("/event_fullpage")
    public String event_fullpage(@SessionAttribute("userId") Optional<Integer> userIdOptional, Model model){
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return "redirect:/login";
        }
        UserVo userVo = userService.getUserById(userId);
        model.addAttribute("user", userVo);

        return "event_fullpage";
    }

    //외주 게시판 페이지
    @RequestMapping("/notice_list")
    public String noticeBoard(@SessionAttribute("userId") Optional<Integer> userIdOptional, Model model){
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return "redirect:/login";
        }
        //notice리스트 불러오기
        List<Map<String,Object>> noticeList = noticeService.getAllList();
        if(noticeList != null) {
            model.addAttribute("notice", noticeList);

            System.out.println("noticeboard success");
        }
        //유저정보
        Optional<UserVo> user = userService.selectbyId(userId);
        if (user.isPresent()) {
            UserVo loginUser = user.get();
            model.addAttribute("user", loginUser);
        } else {
            return "redirect:/login";
        }

        return "notice_list";
    }



}
