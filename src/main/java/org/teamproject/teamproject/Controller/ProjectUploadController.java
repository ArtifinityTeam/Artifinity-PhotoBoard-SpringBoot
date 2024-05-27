package org.teamproject.teamproject.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamproject.teamproject.Service.ProjectService;
import org.teamproject.teamproject.Service.ProjectUploadService;
import org.teamproject.teamproject.Service.TagService;
import org.teamproject.teamproject.Service.UserService;
import org.teamproject.teamproject.Vo.ProjectVo;
import org.teamproject.teamproject.Vo.TagVo;
import org.teamproject.teamproject.Vo.UserVo;

import java.io.File;
import java.util.List;
import java.util.Optional;


@Controller
public class ProjectUploadController {

    private final ProjectUploadService projectUploadService;
    private final TagService tagService;
    private final UserService userService;
    ProjectService projectService;

    @Autowired
    public ProjectUploadController(ProjectUploadService projectUploadService, TagService tagService , UserService userService, ProjectService projectService) {
        this.projectUploadService = projectUploadService;
        this.tagService = tagService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/project_upload")
    public String projectUploadPage(@SessionAttribute("userId") Optional<Integer> UserIdOptional, Model model) {
        // 세션에서 사용자 ID 가져오기
        Integer userId = UserIdOptional.orElse(null);
        //유저정보
        Optional<UserVo> user = userService.selectbyId(userId);
        if (user.isPresent()) {
            UserVo loginUser = user.get();
            model.addAttribute("user", loginUser);
            System.out.println("업로드로딩 성공 아이디 : " + userId);
            // 태그 목록을 가져와 모델에 추가
            List<TagVo> tagList = tagService.getAllTags();
            model.addAttribute("tagList", tagList);
            // 프로젝트 정보를 가져와 모델에 추가
            List<ProjectVo> projectList = projectService.getAllProjects();
            model.addAttribute("projectList", projectList);
        } else {
            return "redirect:/login";
        }

        return "project_upload";
    }

    @PostMapping("/project_upload")
    public ResponseEntity<Object> uploadProject(ProjectVo projectVo, @RequestParam("file") MultipartFile file, @RequestParam(value = "background", defaultValue = "0") int background,
                                                @SessionAttribute("userId") Optional<Integer> userIdOptional, RedirectAttributes redirectAttributes, Model model) {
        // 유저 id 확인
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return new ResponseEntity<>("Failed to update user information", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 프로젝트 객체에 토글 값 설정
        projectVo.setBackground(background);
        System.out.println("업로드 DB 전송");
        System.out.println("아이디 : " + userId);
        System.out.println("제목 : " + projectVo.getProjectName());
        System.out.println("태그 : " + projectVo.getTagName());
        System.out.println("사진 : " + file.getOriginalFilename());
        // project에서 태그는 빼서 비교하기, 프로젝트 파일 DB에 날리기 이후 반환 값 받아오기.
        String content = projectVo.getContent();
        System.out.println("내용 : " + content);
        System.out.println("background : " + projectVo.getBackground());

        try {
            // 파일 로컬 저장 경로 설정
            //String uploadPath = "D:/kDigital_workspace/workspace/TEST/IntelliJ_Merge_Folder/final/Teamproject/src/main/webapp/upload";
            String uploadPath = "C:\\Project_1\\Project_1 merge\\project_1_H\\real_final\\Teamproject\\src\\main\\webapp\\upload";

            // 프로젝트 파일명 변경
            int randomID = (int) (Math.random() * 10000);
            String NewFileName = userId + "_" + randomID + "_projectImg.jpg";
            // 파일 재생성
            File file1 = new File(uploadPath, NewFileName);
            // 로컬 저장소에 저장
            file.transferTo(file1);
            // 프로젝트 정보에 할당
            projectVo.setFileName(NewFileName);
            projectVo.setFilePath(uploadPath);
            projectVo.setUserId(userId);

            // tag 정보 확인
            TagVo tagVo = new TagVo();
            tagVo.setTagName(projectVo.getTagName());
            int tagid = tagService.getTagName(tagVo);
            if (tagid != 0) {
                projectVo.setTagId(tagid);
            }

            // 현재 시간 설정
            java.sql.Timestamp now = new java.sql.Timestamp(new java.util.Date().getTime());
            projectVo.setProjectCreation(now);

            // DB에 전송 (file / content / tag)
            int result = projectUploadService.ProjectUpload(projectVo);

            //추가 5/8
            UserVo userVo = userService.getUserById(userId);
            model.addAttribute("user", userVo);
//
           // 프로젝트 리스트를 모델에 추가
            List<ProjectVo> projectList = projectService.getAllProjects();
            model.addAttribute("projectList", projectList);

            // 전송 확인
            if (result > 0) {
                System.out.println("프로젝트 업로드 성공");
                return new ResponseEntity<>("Success", HttpStatus.OK);
            } else {
                System.out.println("프로젝트 업로드 실패");
                return new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


