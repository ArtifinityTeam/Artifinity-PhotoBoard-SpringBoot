package org.teamproject.teamproject.Service;


import org.springframework.stereotype.Service;
import org.teamproject.teamproject.Vo.TagVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagService extends DBService{


    public List<TagVo> getAllTags() {
        List<TagVo> tagList = new ArrayList<>();
        conn = getConnect();
        try {

            // SQL 쿼리 준비
            String sql = "SELECT * FROM Tag";
            pstmt = conn.prepareStatement(sql);

            // 쿼리 실행 및 결과 처리
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int tagId = rs.getInt("tagId");
                String tagName = rs.getString("tagName");
                // 태그 객체 생성 후 목록에 추가
                TagVo tag = new TagVo(tagId, tagName);
                tagList.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose();
        }
        return tagList;
    }

    public String getTagNameById(int tagId) {
        String tagName = null;

        conn = getConnect();

        try {
            // SQL 쿼리 준비
            String sql = "SELECT tagName FROM Tag WHERE tagId = ?";
            pstmt = conn.prepareStatement(sql);
            // 쿼리 파라미터 설정
            pstmt.setInt(1, tagId);

            // 쿼리 실행
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tagName = rs.getString("tagName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose();
        }
        return tagName;
    }

    public int getTagName(TagVo tagVo) {
        int tagId = 0;
        conn = getConnect();

        try{
            String sql = "SELECT tagId FROM Tag WHERE tagName = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,tagVo.getTagName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tagId = rs.getInt("TagId");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return tagId;
    }
}

