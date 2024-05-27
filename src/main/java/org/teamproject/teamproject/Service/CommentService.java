package org.teamproject.teamproject.Service;


import org.springframework.stereotype.Service;
import org.teamproject.teamproject.Vo.CommentVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService extends DBService{


    public  void  addComment(CommentVo comment) {

        conn = getConnect();
        try{
            String sql = "INSERT INTO comment (content, projectId, userId, userName, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getProjectId());
            pstmt.setInt(3, comment.getUserId());
            pstmt.setString(4, comment.getUserName());
            pstmt.setTimestamp(5, comment.getCreated_at());
            pstmt.setTimestamp(6, comment.getUpdated_at());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
    }

    public List<CommentVo> getAllComments() {
        List<CommentVo> comments = new ArrayList<>();
        conn = getConnect();

        try {
            String sql = "SELECT * FROM comment ORDER BY created_at DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            while (rs.next()) {
                CommentVo comment = new CommentVo();
                comment.setCommentId(rs.getLong("commentId"));
                comment.setContent(rs.getString("content"));
                comment.setProjectId(rs.getInt("projectId"));
                comment.setUserId(rs.getInt("userId"));
                comment.setUserName(rs.getString("userName"));
                comment.setCreated_at(rs.getTimestamp("created_at"));
                comment.setUpdated_at(rs.getTimestamp("updated_at"));
                comments.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // 적절한 예외 처리를 해야 합니다.
        }finally {
            DBClose();
        }
        return comments;
    }


    public int countComments(int projectId) {
        int result = 0;
        conn = getConnect();

        try{
            String sql = "SELECT COUNT(*) AS commentCount FROM comment WHERE projectId = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("commentCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return result;
    }

    public List<CommentVo> getCommentsByProjectId(int projectId) {
        List<CommentVo> comments = new ArrayList<>();
        conn = getConnect();

        try {
            String sql = "SELECT c.commentId, c.content, c.projectId, c.userId, c.created_at, c.updated_at, u.userName, u.userImage " +
                    "FROM comment c " +
                    "JOIN user u ON c.userId = u.userId " +
                    "WHERE c.projectId = ? " +
                    "ORDER BY c.created_at DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CommentVo comment = new CommentVo();
                comment.setCommentId(rs.getInt("commentId"));
                comment.setContent(rs.getString("content"));
                comment.setProjectId(rs.getInt("projectId"));
                comment.setUserId(rs.getInt("userId"));
                comment.setCreated_at(rs.getTimestamp("created_at"));
                comment.setUpdated_at(rs.getTimestamp("updated_at"));
                comment.setUserName(rs.getString("userName"));
                comment.setUserImage(rs.getString("userImage"));  // 댓글 작성자의 프로필 이미지 설정
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return comments;
    }


    public CommentVo getCommentById(long commentId) {
        CommentVo commentVo = null;
        conn = getConnect();

        try{  // 수정
            String sql = "SELECT * FROM comment WHERE commentId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, commentId);  // 수정
            rs = pstmt.executeQuery();
            if (rs.next()) {
                commentVo = new CommentVo();
                commentVo.setCommentId(rs.getLong("commentId"));
                commentVo.setContent(rs.getString("content"));
                commentVo.setProjectId(rs.getInt("projectId"));
                commentVo.setUserId(rs.getInt("userId"));
                commentVo.setUserName(rs.getString("userName"));
                commentVo.setCreated_at(rs.getTimestamp("created_at"));
                commentVo.setUpdated_at(rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 적절한 예외 처리를 해야 합니다.
        }finally {
            DBClose();
        }
        return commentVo;
    }


    public void deleteComment(long commentId) {
        conn = getConnect();

        try{
            String sql = "DELETE FROM comment WHERE commentId = ?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, commentId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("삭제 실패: 해당 댓글이 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 오류 로깅 추가 필요
        }
    }

    public void updateComment(long commentId, String newContent) {
        conn = getConnect();

        try{
            String sql = "UPDATE comment SET content = ?, updated_at = ? WHERE commentId = ?;";
            pstmt = conn.prepareStatement(sql);
            // 현재 시간을 설정
            Timestamp now = new Timestamp(new java.util.Date().getTime());

            // 매개변수 설정
            pstmt.setString(1, newContent);
            pstmt.setTimestamp(2, now);
            pstmt.setLong(3, commentId);

            // 쿼리 실행
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("업데이트 실패: 해당 댓글이 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 적절한 예외 처리를 해야 합니다.
        }finally {
            DBClose();
        }
    }

}

