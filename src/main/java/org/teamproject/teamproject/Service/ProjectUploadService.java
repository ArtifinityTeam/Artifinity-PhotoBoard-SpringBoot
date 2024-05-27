package org.teamproject.teamproject.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.teamproject.teamproject.Vo.ProjectVo;
import org.teamproject.teamproject.Vo.TagVo;

import java.sql.SQLException;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Service
public class ProjectUploadService extends DBService {

    // 프로젝트 생성
    public int ProjectUpload(ProjectVo projectVo) {
        int result = 0;
        conn = getConnect();

        try{
            String sql = "INSERT INTO UserProject " +
                    "(projectName, projectCreation, userId, content, tagId, fileName, filePath, background) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, projectVo.getProjectName());
            pstmt.setTimestamp(2, projectVo.getProjectCreation());
            pstmt.setInt(3, projectVo.getUserId());
            pstmt.setString(4, projectVo.getContent().replace("\r\n","<br>"));
            pstmt.setInt(5, projectVo.getTagId());
            pstmt.setString(6, projectVo.getFileName());
            pstmt.setString(7, projectVo.getFilePath());
            pstmt.setInt(8,projectVo.getBackground());
            result = pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return result;
    }


}
