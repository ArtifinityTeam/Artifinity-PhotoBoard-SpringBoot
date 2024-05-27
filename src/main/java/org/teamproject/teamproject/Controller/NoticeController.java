package org.teamproject.teamproject.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.teamproject.teamproject.Service.NoticeService;
import org.teamproject.teamproject.Service.UserService;
import org.teamproject.teamproject.Vo.ApplicationVo;
import org.teamproject.teamproject.Vo.NoticeVo;
import org.teamproject.teamproject.Vo.UserVo;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class NoticeController {

    public final UserService userService;
    public final NoticeService noticeService;

    public NoticeController(UserService userService, NoticeService noticeService) {
        this.userService = userService;
        this.noticeService = noticeService;
    }

    // 공고 업로드 페이지
    @GetMapping("/notice_upload")
    public String uploadNotice(@SessionAttribute("userId") Optional<Integer> userIdOptional, Model model) {
        // 세션에서 사용자 ID 가져오기
        Integer userId = userIdOptional.orElse(null);

        if (userId == null) {
            return "redirect:/login";
        }

        // 유저정보
        Optional<UserVo> user = userService.selectbyId(userId);
        if (user.isPresent()) {
            UserVo loginUser = user.get();
            model.addAttribute("user", loginUser);
            System.out.println("업로드로딩 성공 아이디 : " + userId);
        } else {
            return "redirect:/login";
        }
        return "notice_upload";
    }

    @PostMapping("/notice_upload")
    public ResponseEntity<Map<String, String>> NoticeUpload(NoticeVo noticeVo, @SessionAttribute("userId") Optional<Integer> userIdOptional) {
        // 유저 id 확인
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("status", "error", "message", "로그인이 필요합니다."));
        }

        System.out.println(userId);
        System.out.println("제목 : " + noticeVo.getNoticeTitle());
        System.out.println("내용 : " + noticeVo.getNoticeContent());

        // noticeVo에 userId 설정
        noticeVo.setUserId(userId);

        // 현재 날짜 설정
        noticeVo.setNoticeCreation(new Date(System.currentTimeMillis()));

        // DB에 전송
        int result = noticeService.NoticeUpload(noticeVo);

        // 전송 확인
        if (result > 0) {
            System.out.println("업로드 성공");
            return ResponseEntity.ok().body(Map.of("status", "success", "message", "공지사항이 성공적으로 업로드되었습니다."));
        } else {
            System.out.println("업로드 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status", "error", "message", "업로드 실패"));
        }
    }

    //notice페이지
    @GetMapping("/notice_list")
    public String notice_list(Model model, @SessionAttribute("userId") Optional<Integer> userIdOptional) {
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


    //notice 디테일페이지
    @GetMapping("/notice/{noticeId}")
    public String noticedetail(Model model, @PathVariable("noticeId") int noticeId,
                               @SessionAttribute("userId") Optional<Integer> userIdOptional){
        Integer userId = userIdOptional.orElse(null);

        if (userId != null) {
            //상세정보
            Optional<NoticeVo> notice = noticeService.getDetail(noticeId);
            if(notice.isPresent()) {
                NoticeVo noticeVo = notice.get();
                model.addAttribute("notice", noticeVo);
            }

            //신청자 목록
            List<Map<String,Object>> applyList = noticeService.ApplyList(noticeId);
            if(applyList != null) {
                model.addAttribute("apply", applyList);
            }

            //로그인 유저 정보
            Optional<UserVo> user = userService.selectbyId(userId);
            if (user.isPresent()) {
                UserVo loginUser = user.get();
                model.addAttribute("user", loginUser);
            }

            return "notice_detail";

        }
        return "redirect:/login";

    }

// 제작신청(json 변환한 코드)
//    @PostMapping("/application")
//    public ResponseEntity<String> application(@SessionAttribute("userId") Optional<Integer> userIdOptional,
//                                              @RequestParam("noticeId") int noticeId) {
//        Integer userId = userIdOptional.orElse(null);
//        if (userId != null) {
//            try {
//                System.out.println("신청 시도: userId=" + userId + ", noticeId=" + noticeId);
//                ApplicationVo applicationVo = new ApplicationVo();
//                applicationVo.setUserId(userId);
//                applicationVo.setNoticeId(noticeId);
//                int result = noticeService.applyNotice(applicationVo);
//                if (result > 0) {
//                    System.out.println("신청 성공: userId=" + userId + ", noticeId=" + noticeId);
//                    return new ResponseEntity<>("Succes", HttpStatus.OK);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("신청 실패: userId=" + userId + ", noticeId=" + noticeId);
//                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //제작신청
    @PostMapping("/application")
    public String application(@SessionAttribute("userId") Optional<Integer> userIdOptional,
                              @RequestParam("noticeId") int noticeId) {
        Integer userId = userIdOptional.orElse(null);
        if (userId != null) {
            try {
                System.out.println("신청 시도: userId=" + userId + ", noticeId=" + noticeId);
                ApplicationVo applicationVo = new ApplicationVo();
                applicationVo.setUserId(userId);
                applicationVo.setNoticeId(noticeId);
                int result = noticeService.applyNotice(applicationVo);
                if (result > 0) {
                    System.out.println("신청 성공: userId=" + userId + ", noticeId=" + noticeId);
                    return "redirect:/notice_list";
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("신청 실패: userId=" + userId + ", noticeId=" + noticeId);
            }
        }
        return "redirect:/notice_detail/{noticeId}";
    }




}

