package org.teamproject.teamproject.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.teamproject.teamproject.Service.*;
import org.teamproject.teamproject.Vo.GalleryVo;
import org.teamproject.teamproject.Vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MypageController {

    private final UserService userService;
    private final ProjectService projectService;
    private final GalleryService galleryService;
    private final UserLikeService userLikeService;
    private final UserViewService userViewService;

    @Autowired
    public MypageController(UserService userService, ProjectService projectService,
                            GalleryService galleryService, UserLikeService userLikeService,
                            UserViewService userViewService) {
        this.userService = userService;
        this.projectService = projectService;
        this.galleryService = galleryService;
        this.userLikeService = userLikeService;
        this.userViewService = userViewService;

    }

    //마이페이지 연결시 해당 유저의 정보 가져오기
    @GetMapping("/mypage")
    public String mypage(Model model, @SessionAttribute("userId" )Optional<Integer> userIdOptional,HttpSession session) {
        Integer userId = userIdOptional.orElse(null);

        if (userId != null) {

            //무드보드 리스트
            List<Map<String, Object>> galleryList = galleryService.GalleryList(userId);
            if (galleryList != null) {
                model.addAttribute("galleryList", galleryList);
            }

            //프로젝트 무드보드추가  타이틀 리스트
            List<Map<String, Object>> galleryTitleList = galleryService.GalleryTitelList(userId);
            if (galleryTitleList != null) {
                model.addAttribute("galleryTitleList", galleryTitleList);
            }

            //업로드 프로젝트
            List<Map<String, Object>> projectList = projectService.UserProjectList(userId);
            if (projectList != null) {
                //photo : path/Name, project : id 넘어옴 id로 리스트 생성
                model.addAttribute("projectList", projectList);
            }

            //유저 좋아요 수
            int userLike = userLikeService.countUserLikeByUserId(userId);
            if(userLike > 0) {
                model.addAttribute("userLike", userLike);
            }else {
                model.addAttribute("userLike", 0);
            }


            //유저 조회수
            int userView = userViewService.countUserViewByUserId(userId);
            if(userView > 0) {
                model.addAttribute("userView", userView);
            }else{
                model.addAttribute("userView", 0);
            }

            //유저정보
            Optional<UserVo> user = userService.selectbyId(userId);
            if (user.isPresent()) {
                UserVo loginUser = user.get();
                model.addAttribute("user", loginUser);
                session.setAttribute("user", loginUser);
            } else {
                return "redirect:/login";
            }
            return "mypage";
        }else {
            return "redirect:/login";
        }
    }

    //프로젝트 무드보드에 추가(AJAX)
    @PostMapping("/Addboard")
    public ResponseEntity<String> AddMoodboard(GalleryVo galleryVo, @SessionAttribute("userId" )Optional<Integer> userIdOptional) {
        //세션에서 USERID 가져오기
        Integer userId = userIdOptional.orElse(null);

        if(userId != null) {
            try{
                System.out.println("무드보드제목 : " + galleryVo.getGalleryName());
                System.out.println("프젝아이디 : " + galleryVo.getProjectId());
                //데이터를 AJAX->VO 형태로 전송 받아 그대로 DB에 전달
                galleryVo.setUserId(userId);
                int result = galleryService.addBoard(galleryVo);

                if(result > 0) {
                    //응답 성공
                    System.out.println("무드보드 추가 성공");
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //무드보드 디테일 페이지
    @GetMapping("/mboard_detail/{galleryName}")
    public String mboardDetail(@PathVariable("galleryName") String galleryName,Model model, @SessionAttribute("userId" )Optional<Integer> userIdOptional, GalleryVo galleryVo) {

        Integer userId = userIdOptional.orElse(null);
        if(userId != null) {

            //보드 디테일

            List<Map<String, Object>> deatailList = galleryService.MboardDetailList(galleryName , userId);
            if (deatailList != null) {
                model.addAttribute("DetailList", deatailList);
            }

            //유저정보
            Optional<UserVo> user = userService.selectbyId(userId);
            if (user.isPresent()) {
                UserVo loginUser = user.get();
                model.addAttribute("user", loginUser);
            } else {
                return "redirect:/login";
            }

            return "/mboard_detail";
        }

        return "redirect:/login";
    }

    //프로젝트 삭제
    @PostMapping("/deleteProject")
    public ResponseEntity<String> DeleteProject(@SessionAttribute("userId") Optional<Integer> userIdOptional,
                                                @RequestParam("projectId") Integer projectId) {
        Integer userId = userIdOptional.orElse(null);

        if (userId != null) {
            try {
                System.out.println("삭제할 프로젝트 아이디 : " + projectId);
                int result = projectService.delProject(projectId);

                if (result > 0) {
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //무드보드(갤러리)삭제
    @PostMapping("/deleteGallery")
    public ResponseEntity<String> DeleteGallery(@SessionAttribute("userId") Optional<Integer> userIdOptional,
                                                @RequestParam("GalleryName") String GalleryName) {
        Integer userId = userIdOptional.orElse(null);

        if (userId != null) {
            try{
                System.out.println("삭제할 갤러리 명 : " + GalleryName);
                int result = galleryService.delGallery(GalleryName);
                if (result > 0) {
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //무드보드 이름 변경
    @PostMapping("/GalleryRename")
    public ResponseEntity<String> RenameGallery(@SessionAttribute("userId") Optional<Integer> userIdOptional,
                                                @RequestParam("newName") String galleryRename,
                                                @RequestParam("OriginName") String OriginName) {
        Integer userId = userIdOptional.orElse(null);

        if (userId != null) {
            try {
                System.out.println("기존 무드보드명 : " + OriginName);
                System.out.println("변경한 무드보드명 : " + galleryRename);
                int result = galleryService.ReNameGalleryName(OriginName, galleryRename);
                if (result > 0) {
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

