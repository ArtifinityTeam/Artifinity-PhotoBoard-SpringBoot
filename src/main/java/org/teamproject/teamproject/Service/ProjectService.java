package org.teamproject.teamproject.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.teamproject.teamproject.Vo.ProjectVo;
import org.teamproject.teamproject.Vo.TagVo;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


@Service
public class ProjectService extends DBService{



    //프로젝트 최신순으로 가져오기 (디폴트:최신순)
    public ProjectVo getProjectById(int projectId) {
        ProjectVo projectVo = null;
        conn = getConnect();

        try{
            String sql = "SELECT * FROM UserProject p JOIN user u ON p.userId = u.userId WHERE p.projectId = ? ORDER BY projectCreation DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                projectVo = new ProjectVo();
                projectVo.setProjectId(rs.getInt("projectId"));
                projectVo.setProjectName(rs.getString("projectName"));
                projectVo.setContent(rs.getString("content"));
                projectVo.setFileName(rs.getString("fileName"));
                projectVo.setFilePath(rs.getString("filePath"));
                projectVo.setUserId(rs.getInt("userId"));
                projectVo.setTagId(rs.getInt("tagId"));
                projectVo.setProjectCreation(rs.getTimestamp("projectCreation"));
                projectVo.setViews(rs.getInt("views"));
                projectVo.setUserImage(rs.getString("userImage"));  // 댓글 작성자의 프로필 이미지 설정
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return projectVo;
    }

    // 인기순
    public List<ProjectVo> getPopularProjects() {
        List<ProjectVo> projects = new ArrayList<>();
        conn = getConnect();

        try {
            String sql = "SELECT * FROM UserProject ORDER BY views DESC"; // 조회수 기준으로 내림차순 정렬
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectVo project = new ProjectVo();
                project.setProjectId(rs.getInt("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectCreation(rs.getTimestamp("projectCreation"));
                project.setUserId(rs.getInt("userId"));
                project.setTagId(rs.getInt("tagId"));
                project.setFilePath(rs.getString("filePath"));
                project.setFileName(rs.getString("fileName"));
                project.setBackground(rs.getInt("background"));
                project.setContent(rs.getString("content"));
                project.setViews(rs.getInt("views"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 오류 발생시 콘솔에 출력 (실제 환경에서는 로깅을 사용하는 것이 좋습니다)
        } finally {
            DBClose();
        }
        return projects;
    }

    public List<ProjectVo> getAllProjects() {
        List<ProjectVo> projects = new ArrayList<>();
        conn = getConnect();

        try{
            String sql = "SELECT * FROM UserProject ORDER BY projectCreation DESC";  // 프로젝트를 생성 시간의 내림차순으로 정렬
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectVo project = new ProjectVo();
                project.setProjectId(rs.getInt("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectCreation(rs.getTimestamp("projectCreation"));
                project.setUserId(rs.getInt("userId"));
                project.setTagId(rs.getInt("tagId"));
                project.setFilePath(rs.getString("filePath"));
                project.setFileName(rs.getString("fileName"));
                project.setBackground(rs.getInt("background"));
                project.setContent(rs.getString("content"));
                project.setViews(rs.getInt("views"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 오류 발생시 콘솔에 출력 (실제 환경에서는 로깅을 사용하는 것이 좋습니다)
        }finally {
            DBClose();
        }
        return projects;
    }

    // 배경화면
    public List<ProjectVo> getAllBGProjects() {
        List<ProjectVo> projects = new ArrayList<>();
        conn = getConnect();

        try{
            String sql = "SELECT * FROM UserProject WHERE background = 1 ORDER BY projectCreation DESC";  // 프로젝트를 생성 시간의 내림차순으로 정렬
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectVo project = new ProjectVo();
                project.setProjectId(rs.getInt("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectCreation(rs.getTimestamp("projectCreation"));
                project.setUserId(rs.getInt("userId"));
                project.setTagId(rs.getInt("tagId"));
                project.setFilePath(rs.getString("filePath"));
                project.setFileName(rs.getString("fileName"));
                project.setBackground(rs.getInt("background"));
                project.setContent(rs.getString("content"));
                project.setViews(rs.getInt("views"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 오류 발생시 콘솔에 출력 (실제 환경에서는 로깅을 사용하는 것이 좋습니다)
        }finally {
            DBClose();
        }
        return projects;
    }

    public List<ProjectVo> getPopularBGProjects() {
        List<ProjectVo> projects = new ArrayList<>();
        conn = getConnect();

        try {
            String sql = "SELECT * FROM UserProject WHERE background = 1 ORDER BY views DESC"; // 조회수 기준으로 내림차순 정렬
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProjectVo project = new ProjectVo();
                project.setProjectId(rs.getInt("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectCreation(rs.getTimestamp("projectCreation"));
                project.setUserId(rs.getInt("userId"));
                project.setTagId(rs.getInt("tagId"));
                project.setFilePath(rs.getString("filePath"));
                project.setFileName(rs.getString("fileName"));
                project.setBackground(rs.getInt("background"));
                project.setContent(rs.getString("content"));
                project.setViews(rs.getInt("views"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 오류 발생시 콘솔에 출력 (실제 환경에서는 로깅을 사용하는 것이 좋습니다)
        } finally {
            DBClose();
        }
        return projects;
    }

    // 프로젝트의 뷰 수를 증가시키는 메서드 (함수 아래 3개 필요함)
    public void incrementProjectViews(int projectId, int userId) {
        if (!hasUserViewedProject(projectId, userId)) {
            addUserView(projectId, userId);
            updateUserViews(projectId);
        }
    }

    // 특정 프로젝트를 특정 유저가 이미 조회했는지 확인하는 메서드
    private boolean hasUserViewedProject(int projectId, int userId) {
        conn = getConnect();

        try{
            String sql = "SELECT COUNT(*) FROM userViews WHERE projectId = ? AND userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, projectId);
            pstmt.setLong(2, userId);
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

    // 프로젝트 조회 기록을 추가하는 메서드
    private void addUserView(int projectId, int userId) {
        conn = getConnect();

        try{
            String sql = "INSERT INTO userViews (projectId, userId) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, projectId);
            pstmt.setLong(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
    }

    // 프로젝트의 뷰 수를 업데이트하는 메서드
    private void updateUserViews(int projectId) {
        conn = getConnect();

        try{
            String sql = "UPDATE UserProject SET views = views + 1 WHERE projectId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose();
        }
    }


    //마이페이지 업로드프로젝트 탭 가져오기.
    public List<Map<String, Object>> UserProjectList(int userid) {
        List<Map<String , Object>> projectList = new ArrayList<>();
        conn = getConnect();

        try {
            //프로젝트테이블 -> userID 기반으로 photo 테이블과 연결
            // -> 최신순(기본값)으로 photo 의 경로가져오기
            String sql = "select p.filePath, p.fileName, p.projectId from UserProject as p" +
                    " where p.userId = ? ORDER BY p.projectCreation DESC";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();
            //결과를 리스트에추가
            while(rs.next()) {
                Map<String, Object> project = new HashMap<>();
                project.put("filePath", rs.getString("filePath"));
                project.put("fileName", rs.getString("fileName"));
                project.put("projectId", rs.getInt("projectId"));
                projectList.add(project);
            }
            System.out.println("projectList success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("projectList Fail");
        }finally {
            DBClose();
        }
        //filePath / fileName 전달
        return projectList;
    }

    //프로젝트삭제
    public int delProject(Integer projectId) {
        int result = 0;

        conn = getConnect();

        try {
            String sql = "DELETE FROM Userproject WHERE projectId =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return result;
    }

    //0513 수정 -- 배경화면 다운로드
    public int background (int projectId) {
        int background = 0;
        conn = getConnect();

        try {
            String sql = "SELECT background FROM UserProject WHERE projectId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                background = rs.getInt("background");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose();
        }

        return background;
    }


}

