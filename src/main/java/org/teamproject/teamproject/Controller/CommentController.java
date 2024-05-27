package org.teamproject.teamproject.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamproject.teamproject.Service.CommentService;
import org.teamproject.teamproject.Service.UserService;
import org.teamproject.teamproject.Vo.CommentVo;
import org.teamproject.teamproject.Vo.UserVo;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }
/*
---------------------------------------------------------
                   5/7 병합 완
                   @PathVariable("projectId") <- 추가
---------------------------------------------------------
 */
    @PostMapping("/{projectId}/comment")
    public String addComment(CommentVo commentVo, @PathVariable("projectId") int projectId, UserVo userVo, @SessionAttribute("userId") Optional<Integer> userIdOptional,
                             HttpSession session, Model model) {

        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Integer userId = userIdOptional.orElseThrow(() -> new IllegalArgumentException("User ID must not be null"));
        commentVo.setUserId(userId.intValue()); // CommentVo의 userId 설정

        // 현재 시간 설정
        java.sql.Timestamp now = new java.sql.Timestamp(new java.util.Date().getTime());
        commentVo.setCreated_at(now);
        commentVo.setUpdated_at(now);

        // 사용자 이름 설정 (세션 또는 DB 조회)
        // 예: session.getAttribute("userName") 또는 사용자 서비스 사용
        commentVo.setUserName(session.getAttribute("userName").toString());

        // 댓글 디비에 입력
        commentService.addComment(commentVo);

        // 모든 댓글 목록을 다시 가져와 모델에 추가
        List<CommentVo> comments = commentService.getCommentsByProjectId(projectId);
        model.addAttribute("comments", comments);

        // 사용자정보를 모델에 추가
        userVo = userService.getUserById(userId);
        model.addAttribute("user", userVo);

        // 댓글 정보를 모델에 추가
        commentVo = commentService.getCommentById(commentVo.getCommentId());
        model.addAttribute("commentVo", commentVo);

        int commentCount = commentService.countComments(projectId);
        model.addAttribute("commentCount", commentCount);

        return "redirect:/project_detail/" + projectId; // 사용자를 댓글이 추가된 후 보여줄 페이지로 리다이렉션
    }

    @PostMapping("/delete-comment")
    public String deleteComment(@RequestParam("commentId") Long commentId,
                                @RequestParam("projectId") Long projectId, // projectId 추가
                                RedirectAttributes redirectAttributes) {
        commentService.deleteComment(commentId);
        redirectAttributes.addFlashAttribute("message", "댓글이 삭제되었습니다.");
        return "redirect:/project_detail/" + projectId; // projectId를 포함하여 리다이렉션
    }

    @PostMapping("/update-comment")
    public ResponseEntity<String> updateComment(@RequestParam("commentId") long commentId, @RequestParam("content") String content) {
        commentService.updateComment(commentId, content);
        return null;
    }


// ... 나머지 코드 ...


}
