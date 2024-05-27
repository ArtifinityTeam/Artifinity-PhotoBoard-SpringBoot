package org.teamproject.teamproject.Service;

import org.springframework.stereotype.Service;
import org.teamproject.teamproject.Vo.ApplicationVo;
import org.teamproject.teamproject.Vo.NoticeVo;

import java.sql.SQLException;
import java.util.*;

@Service
public class NoticeService extends DBService{

    // 외주 업로드
    public int NoticeUpload(NoticeVo noticeVo) {
        int result = 0;
        conn = getConnect();

        try{
            String sql = "INSERT INTO Noticeboard (noticeTitle, noticeContent, noticeCreation, userId) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, noticeVo.getNoticeTitle());
            pstmt.setString(2, noticeVo.getNoticeContent().replace("\r\n","<br>"));
            pstmt.setDate(3,noticeVo.getNoticeCreation());
            pstmt.setInt(4, noticeVo.getUserId());
            result = pstmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return result;
    }

    //신청게시글
    public List<Map<String, Object>> getAllList() {
        List<Map<String, Object>> noticeList = new ArrayList<Map<String, Object>>();

        conn = getConnect();

        try{
            String sql = "select n.noticeId,n.noticeTitle,n.noticecontent,n.noticeCreation,n.userId, u.userName " +
                    " from noticeboard n " +
                    " Join user u on n.userId = u.userId";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> notice = new HashMap<String, Object>();
                notice.put("noticeId",rs.getInt("noticeId"));
                notice.put("Title", rs.getString("noticeTitle"));
                notice.put("Content", rs.getString("noticecontent"));
                notice.put("userId", rs.getInt("userId"));
                notice.put("created", rs.getDate("noticeCreation"));
                notice.put("userName", rs.getString("userName"));
                noticeList.add(notice);
                System.out.println("noticeDB success");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return noticeList;
    }
    //신청상세페이지
    public Optional<NoticeVo> getDetail(int noticeId) {

        NoticeVo noticevo = new NoticeVo();
        conn = getConnect();

        try {
            String sql = "select  n.noticeId,n.noticeTitle,n.noticecontent,n.noticeCreation,n.userId, u.userName " +
                    " from noticeboard n" +
                    " join user u on n.userId = u.userId" +
                    " where noticeId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, noticeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                noticevo.setNoticeId(rs.getInt("noticeId"));
                noticevo.setNoticeTitle(rs.getString("noticeTitle"));
                noticevo.setNoticeContent(rs.getString("noticecontent"));
                noticevo.setNoticeCreation(rs.getDate("noticeCreation"));
                noticevo.setUserId(rs.getInt("userId"));
                noticevo.setUserName(rs.getString("userName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            DBClose();
        }
        return Optional.ofNullable(noticevo);
    }

    //제작신청
    public int applyNotice(ApplicationVo applicationVo) {
        int result = 0;

        conn = getConnect();

        try {
            String sql = "insert into application (userId, noticeId) value (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, applicationVo.getUserId());
            pstmt.setInt(2, applicationVo.getNoticeId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            DBClose();
        }
        return result;
    }

    //신청자 목록
    public List<Map<String,Object>> ApplyList(int noticeId) {
        List<Map<String, Object>> applyList = new ArrayList<Map<String, Object>>();
        conn = getConnect();

        try {
            String sql = "select u.userName, a.applicationId from application  a " +
                    "Join user u on a.userId = u.userId " +
                    "where noticeId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,noticeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> apply = new HashMap<String, Object>();
                apply.put("userName", rs.getString("userName"));
                apply.put("applyId", rs.getInt("applicationId"));
                applyList.add(apply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return applyList;
    }

}
