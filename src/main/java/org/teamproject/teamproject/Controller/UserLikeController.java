package org.teamproject.teamproject.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.teamproject.teamproject.Service.UserLikeService;
import org.teamproject.teamproject.Vo.UserLikeVo;

import java.util.Optional;

@Controller
public class UserLikeController {

    private final UserLikeService userLikeService;

    public UserLikeController(UserLikeService userLikeService) {
        this.userLikeService = userLikeService;
    }

    @PostMapping("/{projectId}/userlike_add")
    public String addUserLike(UserLikeVo userlikeVo, @PathVariable("projectId") int projectId, @SessionAttribute("userId") Optional<Integer> userIdOptional, HttpSession session, Model model) {

        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Integer userId = userIdOptional.orElseThrow(() -> new IllegalArgumentException("User ID must not be null"));
        userlikeVo.setUserId(userId); //userId 설정
        userlikeVo.setProjectId(projectId); //projectId 설정

        // 좋아요 디비에 입력
        userLikeService.addUserLike(userlikeVo);

        // 좋아요 정보를 모델에 추가
        int userLikeCount = userLikeService.countUserLikeByProjectId(projectId);
        model.addAttribute("userLikeCount", userLikeCount);

        return "redirect:/project_detail/" + projectId;
    }

    @PostMapping("/{projectId}/userlike_remove")
    public String removeUserLike(@SessionAttribute("userId") Optional<Integer> userIdOptional,@PathVariable("projectId") int projectId, Model model) {
        Integer userId = userIdOptional.orElseThrow(() -> new IllegalArgumentException("User ID must not be null"));

        userLikeService.removeUserLikeByUserId(userId);

        return "redirect:/project_detail/" + projectId;
    }

}
