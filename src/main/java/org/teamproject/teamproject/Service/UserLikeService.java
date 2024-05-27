package org.teamproject.teamproject.Service;


import org.springframework.stereotype.Service;
import org.teamproject.teamproject.Vo.UserLikeVo;

import java.sql.*;

@Service
public class UserLikeService extends DBService{



    public UserLikeVo getUserLikeById(long userId) {
        UserLikeVo userlikeVo = null;
        conn = getConnect();

        try{
            String sql = "SELECT * FROM userlike WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);  // 수정
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userlikeVo = new UserLikeVo();
                userlikeVo.setUserId(rs.getLong("userId"));
                userlikeVo.setProjectId(rs.getLong("projectId"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 적절한 예외 처리를 해야 합니다.
        }finally {
            DBClose();
        }
        return userlikeVo;
    }


    public UserLikeVo getUserLikeByProjectId(long projectId) {
        UserLikeVo userLikeVo = null;
        conn = getConnect();


        try{
            String sql = "SELECT * FROM userlike WHERE projectId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, projectId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userLikeVo = new UserLikeVo();
                // 이 부분에서 userLikeVo 객체에 값을 설정합니다.
                userLikeVo.setUserId(rs.getLong("userId"));
                userLikeVo.setProjectId(rs.getLong("projectId"));
                // ... 기타 필요한 필드 설정
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return userLikeVo;
    }



    public void addUserLike(UserLikeVo userLikeVo) {
        conn = getConnect();

        try{
            String sql = "INSERT INTO userlike (userId, projectId) VALUES (?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userLikeVo.getUserId());
            pstmt.setLong(2, userLikeVo.getProjectId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
    }

    public int countUserLikeByProjectId(int projectId) {
        int result = 0;
        conn = getConnect();

        try {
            String sql = "SELECT COUNT(*) AS userLikeCount FROM userlike WHERE projectId = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("userLikeCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return result;
    }

    public void removeUserLikeByUserId(long userId) {
        conn = getConnect();

        try{
            String sql = "DELETE FROM userlike WHERE userId = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // 적절한 예외 처리와 로깅을 추가해야 합니다.
        }finally {
            DBClose();
        }
    }

    public boolean checkIfUserLikedProject(long userId, long projectId) {
        int count = 0;
        conn = getConnect();

        try{
            String sql = "SELECT COUNT(*) FROM userlike WHERE userId = ? AND projectId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, projectId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 적절한 예외 처리 및 로깅
        }finally {
            DBClose();
        }
        return count > 0; // count가 1 이상이면 true, 그렇지 않으면 false 반환
    }



    //마이페이지 해당유저의 총 좋아요 갯수 가져오기
    public int countUserLikeByUserId(int userId) {
        int totalLike = 0;
        conn = getConnect();
        try {
            String sql = "SELECT COUNT(*) as totalLike FROM userlike WHERE userId = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                totalLike = rs.getInt("totalLike");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return totalLike;
    }

}

