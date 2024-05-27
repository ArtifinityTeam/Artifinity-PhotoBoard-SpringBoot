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
public class ProjectSearchService extends DBService{

    public List<ProjectVo> searchProjectsByName(String keyword) {

        List<ProjectVo> projects = new ArrayList<>();
        conn = getConnect();

        try {
            // 키워드를 공백으로 분리하여 각 단어를 검색하도록 설정
            String[] words = keyword.split("\\s+");
            StringBuilder sql = new StringBuilder("SELECT * FROM UserProject WHERE ");

            for (int i = 0; i < words.length; i++) {
                if (i > 0) {
                    sql.append(" AND ");
                }
                sql.append("(REPLACE(projectName, ' ', '') LIKE REPLACE(?, ' ', ''))");
            }
            sql.append(" ORDER BY projectCreation DESC");

            pstmt = conn.prepareStatement(sql.toString());

            // 각 단어에 대해 '%' 문자를 추가하여 부분 검색이 가능하도록 설정
            for (int i = 0; i < words.length; i++) {
                pstmt.setString(i + 1, "%" + words[i] + "%");
            }

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
                project.setViews(rs.getInt("views")); // assuming views is a column in the table
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 오류 발생시 콘솔에 출력 (실제 환경에서는 로깅을 사용하는 것이 좋습니다)
        } finally {
            DBClose();
        }
        return projects;

    }
}
