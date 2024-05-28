package org.teamproject.teamproject.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamproject.teamproject.Service.UserService;
import org.teamproject.teamproject.Vo.UserVo;

import java.io.File;
import java.util.Optional;

@Controller
public class UserController {

    //생성자 주입 post에서 userService사용할 때 필요
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    // 로그인 페이지로 이동하는 메서드 - GET 요청
    @GetMapping("/login")
    public String toLoginPage(HttpSession session) { // 로그인 페이지
        Integer id = (Integer) session.getAttribute("userId");
        if (id != null) { // 로그인된 상태
            return "redirect:/";
        }
        return "login"; // 로그인되지 않은 상태
    }

    // 로그인 처리를 하는 메서드 - POST 요청
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes) {
        UserVo userVo = userService.login(email, password);
        if (userVo  == null) { // 로그인 실패
            return "login_fail";
        }
        session.setAttribute("userId", userVo.getUserId()); // 로그인 성공, 세션에 ID 저장
        session.setAttribute("userName", userVo.getUserName());
        redirectAttributes.addFlashAttribute("userId", userVo); // RedirectAttributes에 userId 추가
        return "redirect:/"; // 메인 페이지로 리다이렉트
    }
    //회원가입 페이지 이동
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    //회원가입
    @PostMapping("/signup")
    public String signup(UserVo userVo, RedirectAttributes redirectAttributes) {
        if (userVo.getUserName() == null || userVo.getUserName().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "이름을 입력해주세요.");
            return "redirect:/signup";
        }

        // 사용자 등록 시도 전에 중복 검사
        if (userService.checkUserNameExists(userVo.getUserName())) {
            redirectAttributes.addFlashAttribute("nameError", "이미 존재하는 이름입니다.");
            return "redirect:/signup";
        }

        if (userService.checkUserEmailExists(userVo.getUserEmail())) {
            redirectAttributes.addFlashAttribute("emailError", "이미 존재하는 이메일입니다.");
            return "redirect:/signup";
        }

        // 현재 시간 설정
        java.sql.Timestamp now = new java.sql.Timestamp(new java.util.Date().getTime());
        userVo.setJoinDate(now);

        // 나머지 필드들도 동일하게 검증 후
        userService.signup(userVo);  // 사용자 등록
        return "redirect:/login";  // 로그인 페이지로 리다이렉트
    }



    // 이메일 찾기 페이지로 이동
    @GetMapping("/email_finder")
    public String emailFinderPage() {
        return "email_finder"; // 이메일 찾기 페이지
    }

    // 이메일 찾기 처리
    @PostMapping("/email_finder")
    public String findEmail(UserVo uservo,RedirectAttributes redirectAttributes) {
        String name = uservo.getUserName();
        String phone = uservo.getUserPhone();
        String email = userService.findEmail(name, phone);

        if (email == null) {
            redirectAttributes.addFlashAttribute("emailFinderError", "해당 정보로 이메일을 찾을 수 없습니다.");
            return "redirect:/email_Nexist";
        }

        redirectAttributes.addFlashAttribute("email", email);
        return "redirect:/email_exist";
    }

    // 비밀번호 찾기 페이지로 이동
    @GetMapping("/pw_finder")
    public String passwordFinderPage() {
        return "pw_finder"; // 비밀번호 찾기 페이지
    }

    // 비밀번호 찾기 처리
    @PostMapping("/pw_finder")
    public String findPassword(UserVo uservo, RedirectAttributes redirectAttributes) {
        String name = uservo.getUserName();
        String phone = uservo.getUserPhone();
        String email = uservo.getUserEmail();
        String password = userService.findPassword(email, name, phone);

        if (password == null) {
            redirectAttributes.addFlashAttribute("pwFinderError", "해당 정보로 비밀번호를 찾을 수 없습니다.");
            return "redirect:/pw_Nexist";
        }

        redirectAttributes.addFlashAttribute("password", password);
        return "redirect:/pw_exist";
    }

    // 이메일 찾기 성공 페이지
    @GetMapping("/email_exist")
    public String emailExistPage() {
        return "email_exist";
    }

    // 이메일 찾기 실패 페이지
    @GetMapping("/email_Nexist")
    public String emailNotExistPage() {
        return "email_Nexist";
    }

    // 비밀번호 찾기 성공 페이지
    @GetMapping("/pw_exist")
    public String passwordExistPage() {
        return "pw_exist";
    }

    // 비밀번호 찾기 실패 페이지
    @GetMapping("/pw_Nexist")
    public String passwordNotExistPage() {
        return "pw_Nexist";
    }

    // 로그인 실패 페이지
    @GetMapping("/login_fail")
    public String loginFailPage() {
        return "login_fail"; // 로그인 실패 페이지
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes,@SessionAttribute("userId") Optional<Integer> userIdOptional) {
        Integer userId = userIdOptional.orElse(null);
        System.out.println("mapping ok");

        if (userId != null) {
            session.invalidate();
            System.out.println("session invalidated");
            redirectAttributes.addFlashAttribute("LogOut Success", "로그아웃 되었습니다");

        }
        System.out.println("redirect Ok");
        return "redirect:/";

    }

    @GetMapping("/Nmainpage")
    public String sampleNloginPage() {
        return "Nmainpage";
    }




    //마이페이지->유저정보수정페이지 이동시 해당 유저에 대한 정보 클라이언트로 전송
    @GetMapping("/Changing")
    public String Changepage(Model model, @SessionAttribute("userId" )Optional<Integer> userIdOptional, HttpSession Session) {
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return "redirect:/login";
        }
        System.out.println("정보페이지로딩시 아이디" + userId);
        Optional<UserVo> user = userService.selectbyId(userId);
        if (user.isPresent()) {
            UserVo longinUser = user.get();
            model.addAttribute("user", longinUser);
            Session.setAttribute("userId", longinUser.getUserId());
            return "Changing";
        } else {
            return "redirect:/login";
        }
    }



    //정보변경페이지에서 변경된 유저정보 가져오기
    @PostMapping("/Changing")
    public ResponseEntity<String> userInfChange(UserVo user,@RequestParam("file") MultipartFile file, @SessionAttribute("userId" )Optional<Integer> userIdOptional){

        // 유저아이디 가져오기
        Integer userId = userIdOptional.orElse(null);
        if (userId == null) {
            return new ResponseEntity<>("Failed to update user information", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("정보페이지 전송시 아이디" + userId);
        //변경된 유저 이미지
        try {
            //1. 파일 로컬저장소 경로 설정(변경사항이 바로 업데이트시키기 위해 webapp쪽에 파일생성)
            // 해당 경로도 동일하게 각자 개인 프로젝트 저장되어있는 로컬경로로 변경해야함.

            String uploadPath = "D:/kDigital_workspace/workspace/TEST/IntelliJ_Merge_Folder/final/Teamproject/src/main/webapp/upload";
            //String uploadPath = "C:\\Projects\\Artifinity-PhotoBoard-SpringBoot\\src\\main\\webapp\\upload";


            //2. 파일명  변경
            int randomID = (int) (Math.random() * 10000);
            String savedFileName = userId + "_" + randomID + "_profile.jpg";
//            String filePath = "uploadtest/" + savedFileName;
            //3. 파일 재생성
            File file1 = new File(uploadPath, savedFileName);
            //3-1. 로컬 저장소에 저장
            file.transferTo(file1);
            //4.파일명 유저에 할당
            user.setUserImage(savedFileName);
            //5.유저 정보 할당
            user.setUserId(userId);
            //유저서비스로 데이터 전송 및 데이터반환 값 받기
            int result = userService.updateUserInfo(user);

            // 데이터 반환성공시
            if (result > 0) {
                // 응답 성공
                System.out.println("유저정보업데이트성공");
                return new ResponseEntity<>("Success", HttpStatus.OK);
            } else {
                // 응답 실패
                System.out.println("정보업뎃 실패");
                return new ResponseEntity<>("Failed to update user information",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            //예외 발생시 실패 응답
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed to update user information", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

