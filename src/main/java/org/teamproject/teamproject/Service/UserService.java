package org.teamproject.teamproject.Service;


import org.springframework.stereotype.Service;
import org.teamproject.teamproject.Vo.UserVo;

import java.sql.*;
import java.util.Optional;

@Service
public class UserService extends DBService{

    //로그인
    public UserVo login(String userEmail, String inputPassword) {
        UserVo user = null;

        try {
            // 데이터베이스 연결
            conn = getConnect();

            // SQL 쿼리 준비
            String sql = "SELECT userId, userName, userPassword FROM user WHERE userEmail = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);

            // 쿼리 실행
            rs = pstmt.executeQuery();

            // 결과 처리
            if (rs.next()) {
                String storedPassword = rs.getString("userPassword");
                if (inputPassword.equals(storedPassword)) {
                    user = new UserVo();
                    user.setUserId(rs.getInt("userId"));
                    user.setUserName(rs.getString("userName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리는 로깅 라이브러리로 로그를 남겨야 합니다.
        } finally {
            DBClose();
        }
        return user; // UserVo 객체 반환 또는 null
    }

    //회원가입
    public void signup(UserVo userVo) {

        conn = getConnect();

        try {
            String sql = "INSERT INTO user (userName, userEmail, userPassword, userPhone, JoinDate) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userVo.getUserName());
            pstmt.setString(2, userVo.getUserEmail());
            pstmt.setString(3, userVo.getUserPassword());
            pstmt.setString(4, userVo.getUserPhone());
            pstmt.setTimestamp(5, userVo.getJoinDate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // 실제 프로젝트에서는 적절한 예외 처리가 필요합니다.
        }finally {
            DBClose();
        }
    }



    //유저 ID 기준으로 프로젝트 가져오기
    public UserVo getUserById(int userId) {
        UserVo userVo = new UserVo();
        conn = getConnect();

        try{
            String sql = "SELECT * FROM user WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,userId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                userVo.setUserId(rs.getInt("userId"));
                userVo.setUserName(rs.getString("userName"));
                userVo.setUserEmail(rs.getString("userEmail"));
                userVo.setUserPassword(rs.getString("userPassword"));
                userVo.setUserPhone(rs.getString("userPhone"));
                userVo.setJoinDate(rs.getTimestamp("JoinDate"));
                userVo.setUserImage(rs.getString("userImage"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return userVo;
    }

    //이메일 찾기
    public String findEmail(String name, String phone) {
        String email = null;
        conn = getConnect();

        try{
            String sql = "select userEmail from user where username=? and userphone=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,phone);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                email = rs.getString("userEmail");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return email;
    }

    //비밀번호찾기
    public String findPassword(String email, String name, String phone) {
        String password = null;
        conn = getConnect();

        try {
            String sql = "SELECT userPassword FROM user WHERE userEmail = ? AND userName = ? AND userPhone = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                password = rs.getString("userPassword");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 로깅 필요
        } finally {
            DBClose();
        }
        return password;
    }

    //이름 중복확인
    public boolean checkUserNameExists(String userName) {

        boolean checkName = false;

        conn = getConnect();

        try{
            String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return checkName;
    }

    //이메일 중복확인
    public boolean checkUserEmailExists(String userEmail) {
        boolean checkEmail = false;
        conn = getConnect();

        try{
            String sql = "SELECT COUNT(*) FROM user WHERE useremail = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return false;
    }

        /*
    -------------------------------------------------------------------------------------------------
     */

    //마이페이지 유저 정보 가져오기
    public Optional<UserVo> selectbyId(int id) {
        UserVo user = new UserVo();

        conn = getConnect();

        try{
            String sql = "select * from user where userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(  1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("UserName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPhone(rs.getString("userPhone"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setUserImage(rs.getString("userImage"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return Optional.ofNullable(user);
    }

    //유저 정보수정
    public int updateUserInfo(UserVo user) {
        int result = 0;

        conn = getConnect();

        try {
            String sql = "UPDATE User SET userName=?, userEmail=?, userPhone=?," +
                    "userPassword=?, userImage=? where userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserEmail());
            pstmt.setString(3, user.getUserPhone());
            pstmt.setString(4, user.getUserPassword());
            pstmt.setString(5, user.getUserImage());
            pstmt.setLong(6, user.getUserId());
            result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("유저정보 업데이트 ");
            }else {
                System.out.println("정보업데이트실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            DBClose();
        }
        return result;
    }

}
