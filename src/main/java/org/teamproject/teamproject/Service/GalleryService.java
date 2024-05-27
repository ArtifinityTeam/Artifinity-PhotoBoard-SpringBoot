package org.teamproject.teamproject.Service;



import org.springframework.stereotype.Service;
import org.teamproject.teamproject.Vo.GalleryVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class GalleryService extends DBService implements GalleryITF {



    @Override //무드보드 불러오기
    public List<Map<String, Object>> GalleryList(int userid) {

        List<Map<String, Object>> galleryList = new ArrayList<Map<String, Object>>();
        conn = getConnect();

        try{
            String sql = "select p.filePath, p.fileName, g.galleryId, g.galleryName,  GROUP_CONCAT(p.fileName) AS imageFiles " +
                    "from Gallery as g" +
                    " JOIN UserProject as p on g.projectId = p.projectId" +
                    " where g.userId = ? Group by g.galleryName ORDER BY g.galleryCreation, p.projectCreation DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Map<String, Object> gallery = new HashMap<String, Object>();
                gallery.put("filePath", rs.getString("filePath"));
                gallery.put("fileName", rs.getString("fileName"));
                gallery.put("galleryId", rs.getInt("galleryId"));
                gallery.put("galleryName", rs.getString("galleryName"));
                String imageFiles = rs.getString("imageFiles");
                gallery.put("imageFiles", imageFiles);
                galleryList.add(gallery);
            }
            System.out.println("galleryList success");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("galleryList Fail");
        }finally {
            DBClose();
        }
        return galleryList;
    }

    @Override //프로젝트 무드보드 추가
    public int addBoard(GalleryVo galleryVo) {
        int result = 0;

        conn = getConnect();

        try {
            String sql = "insert into Gallery (GalleryName, projectid, userid, galleryCreation) " +
                    "value (?,?,?,now())";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, galleryVo.getGalleryName());
            pstmt.setInt(2, galleryVo.getProjectId());
            pstmt.setInt(3, galleryVo.getUserId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return result;
    }

    //무드보드 제목리스트 가져오기
    public List<Map<String, Object>> GalleryTitelList(int userid) {

        List<Map<String, Object>> galleryNameList = new ArrayList<Map<String, Object>>();
        conn = getConnect();

        try{
            String sql = "select g.GalleryName from Gallery as g where userid = ?" +
                    " Group By g.GalleryName";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> galleryName = new HashMap<String, Object>();
                galleryName.put("galleryName", rs.getString("galleryName"));
//                galleryName.put("GalleryId", rs.getInt("GalleyId"));
                galleryNameList.add(galleryName);
            }
            System.out.println("galleryNameList success");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("galleryNameList Fail");
        }finally {
            DBClose();
        }
        return galleryNameList;
    }

    //무드보드 디테일페이지 로딩
    public List<Map<String, Object>> MboardDetailList(String galleryName, int userId) {

        List<Map<String, Object>> galleryDetailList = new ArrayList<Map<String,Object>>();
        conn = getConnect();

        try {
            String sql = " select g.galleryName, g.galleryId, p.filePath, p.fileName, p.projectId from Gallery as g" +
                    " JOIN UserProject as p on g.projectId = p.projectId " +
                    " JOIN User as u on g.userId = u.userId" +
                    " where g.UserId = ? AND g.GalleryName = ?" +
                    " order by p.projectCreation ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId);
            pstmt.setString(2, galleryName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> detailList = new HashMap<>();
                detailList.put("galleryName", rs.getString("galleryName"));
                detailList.put("galleryId", rs.getInt("galleryId"));
                detailList.put("filePath", rs.getString("filePath"));
                detailList.put("fileName", rs.getString("fileName"));
                detailList.put("projectId", rs.getInt("projectId"));
                galleryDetailList.add(detailList);
            }
        } catch (SQLException e) {
            e.printStackTrace();;
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return galleryDetailList;
    }

    //무드보드 지우기
    public int delGallery(String galleryName) {
        int result = 0;

        conn = getConnect();

        try {
            String sql = "delete from Gallery where galleryName = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, galleryName);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return result;
    }


    //무드보드 이름 재설정
    public int ReNameGalleryName(String originName, String galleryRename) {
        int result = 0;
        conn = getConnect();
        try{
            String sql = "update Gallery set GalleryName = ? where GalleryName = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,galleryRename);
            pstmt.setString(2,originName);
            result = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBClose();
        }
        return result;
    }
}
