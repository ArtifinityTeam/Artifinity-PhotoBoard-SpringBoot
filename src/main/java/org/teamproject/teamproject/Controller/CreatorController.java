package org.teamproject.teamproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.teamproject.teamproject.Service.*;
import org.teamproject.teamproject.Vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CreatorController {

    private final GalleryService galleryService;
    private final UserService userService;
    private final ProjectService projectService;
    private final UserLikeService userLikeService;
    private final UserViewService userViewService;
    public CreatorController(GalleryService galleryService, UserService userService, ProjectService projectService,
                             UserLikeService userLikeService, UserViewService userViewService) {
        this.galleryService = galleryService;
        this.userService = userService;
        this.projectService = projectService;
        this.userLikeService = userLikeService;
        this.userViewService = userViewService;
    }



    @GetMapping("/increator/{userId}")
    public String increator(@PathVariable("userId") int CreatorId, Model model,@SessionAttribute("userId") Optional<Integer> userIdOptional) {
        Integer userId = userIdOptional.orElse(null);

        if (userId != null) {
            //크리에이터 디테일 정보
            //보드
            List<Map<String, Object>> galleryList = galleryService.GalleryList(CreatorId);
            if (galleryList != null) {
                model.addAttribute("galleryList", galleryList);
            }
            //프로젝트
            List<Map<String, Object>> projectList = projectService.UserProjectList(CreatorId);
            if(projectList != null) {
                model.addAttribute("projectList", projectList);
            }
            //크리에이터정보
            Optional<UserVo> user = userService.selectbyId(CreatorId);
            if (user.isPresent()) {
                UserVo creator = user.get();
                model.addAttribute("user", creator);
            }
            //크리에이터 좋아요
            int creatorLike = userLikeService.countUserLikeByUserId(CreatorId);
            if (creatorLike > 0) {
                model.addAttribute("likeCount", creatorLike);
            }else{
                model.addAttribute("likeCount", 0);
            }
            //크리에이터 조회수
            int userview = userViewService.countUserViewByUserId(CreatorId);
            if (userview > 0) {
                model.addAttribute("viewCount", userview);
            } else {
                model.addAttribute("viewCount", 0);
            }
            return "creator_detail";
        }
        return "redirect:/login";

    }
}
