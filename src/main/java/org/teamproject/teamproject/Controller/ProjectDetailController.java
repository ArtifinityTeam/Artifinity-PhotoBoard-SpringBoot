package org.teamproject.teamproject.Controller;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.teamproject.teamproject.Service.*;
import org.teamproject.teamproject.Vo.CommentVo;
import org.teamproject.teamproject.Vo.ProjectVo;
import org.teamproject.teamproject.Vo.UserVo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProjectDetailController {

    private final UserService userService;
    private final CommentService commentService;
    private final ProjectService projectService;
    private final TagService tagService;
    private final UserLikeService userLikeService;
    private final CreatorService creatorService;

    // 생성자를 통해 UserService를 주입받습니다.
    @Autowired
    public ProjectDetailController(UserService userService, CommentService commentService, ProjectService projectService, TagService tagService, UserLikeService userLikeService, CreatorService creatorService) {
        this.userService = userService;
        this.commentService = commentService;
        this.projectService = projectService;
        this.tagService = tagService;
        this.userLikeService = userLikeService;
        this.creatorService = creatorService;
    }

    /* -------------------------------------------------------------------------------------
        @PathVariable("projectId") 꼭 수정하기!! 다른 부분에도 path 있으면 이거 추가 안하면 오류납니다.
       -------------------------------------------------------------------------------------*/
    @GetMapping("project_detail/{projectId}")
    public String projectDetail(@PathVariable("projectId") int projectId, @SessionAttribute("userId") Optional<Integer> userIdOptional, @ModelAttribute("projectName") Optional<String> projectNameOptional, HttpSession session, Model model) {

        // Optional을 사용하여 userId가 세션에 있는지 체크합니다.
        Integer userId = userIdOptional.orElse(null);

        if (userId == null) {
            return "redirect:/login";
        } else {

            // Optional을 사용하여 리다이렉트된 projectName이 있는지 체크하고 모델에 추가합니다.
            projectNameOptional.ifPresent(name -> model.addAttribute("projectName", name));
            // 뷰 카운트 증가 로직 호출
            projectService.incrementProjectViews(projectId, userId);

            ProjectVo project = projectService.getProjectById(projectId);
            List<CommentVo> comments = commentService.getCommentsByProjectId(projectId);
            UserVo userVo = userService.getUserById(userId);
            int commentCount = commentService.countComments(projectId);
            String tagName = tagService.getTagNameById(project.getTagId());
            int userLikeCount = userLikeService.countUserLikeByProjectId(projectId);
            boolean isLikedByUser = userLikeService.checkIfUserLikedProject(userId, projectId);
            int background = projectService.background(projectId);


            model.addAttribute("project", project);
            model.addAttribute("user", userVo);
            model.addAttribute("comments", comments);
            model.addAttribute("commentCount", commentCount);
            model.addAttribute("tagName", tagName);
            model.addAttribute("userLikeCount", userLikeCount);
            model.addAttribute("isLikedByUser", isLikedByUser);
            model.addAttribute("background", background);

            return "project_detail";
        }
    }
    @GetMapping("/download/{projectId}")
    public void downloadFile(@PathVariable("projectId") int projectId, HttpServletResponse response) throws IOException {
        ProjectVo project = projectService.getProjectById(projectId);
        if (project != null) {
            String filePath = project.getFilePath();
            System.out.println("-- 1. 경로 : " + filePath);

            if (filePath != null) {
                File file = new File(filePath);
                System.out.println("-- 2. 경로 : " + file);
                if (file.exists()) {
                    // 파일 다운로드 설정
                    response.setContentType("application/octet-stream");
                    response.setContentLength((int) file.length());
                    response.setHeader("Content-disposition", "attachment; filename=\"" + project.getFileName() + "\"");

                    // 파일 입력 객체 생성
                    FileInputStream fis = new FileInputStream(file);
                    // 출력 스트림 생성
                    OutputStream os = response.getOutputStream();
                    System.out.println("-- 3. 경로 : " + file);

                    // 파일 복사
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }

                    // 스트림 닫기
                    fis.close();
                    os.close();
                } else {
                    System.out.println("-- 파일 존재 안 함: " + filePath);
                }
            } else {
                System.out.println("--경로 없음");
            }
        } else {
            System.out.println("-- 프로젝트 db 없음: " + projectId);
        }
    }
//    @GetMapping("/download/{projectId}")
//    public void downloadFile(@PathVariable("projectId") int projectId, HttpServletResponse response) throws IOException {
//        ProjectVo project = projectService.getProjectById(projectId);
//        if (project != null) {
//            String filePath = project.getFilePath();
//            String fileName = project.getFileName();
//
//            // 파일 객체 생성
//            File file = new File(filePath);
//
//            System.out.println("1. 파일이름 : " + fileName);
//            System.out.println("2. 파일경로 : " + filePath);
//
//            // 파일이 존재하는지 확인
//            if (file.exists()) {
//                // 파일 다운로드 설정
//                response.setContentType("application/octet-stream");
//                response.setContentLength((int) file.length());
//                response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
//
//                // 파일 입력 객체 생성
//                FileInputStream fis = new FileInputStream(file);
//                // 출력 스트림 생성
//                OutputStream os = response.getOutputStream();
//
//                // 파일 복사
//                byte[] buffer = new byte[4096];
//                int bytesRead;
//                while ((bytesRead = fis.read(buffer)) != -1) {
//                    os.write(buffer, 0, bytesRead);
//                }
//
//                // 스트림 닫기
//                fis.close();
//                os.close();
//            }
//        }
//    }
}