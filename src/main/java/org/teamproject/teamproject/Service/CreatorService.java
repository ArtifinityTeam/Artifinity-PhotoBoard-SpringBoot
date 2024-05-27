package org.teamproject.teamproject.Service;


import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreatorService extends DBService{


    public List<Map<String, Object>> CreatorList() {
        List<Map<String, Object>> Creatorlist = new ArrayList<Map<String, Object>>();
        conn = getConnect();
        try{

            String sql = "SELECT u.userId, u.userName, u.userImage, GROUP_CONCAT(p.fileName) AS fileNames, IFNULL(l.totalLikes, 0) AS totalLikes, IFNULL(v.totalViews, 0) AS totalViews" +
                    " FROM User AS u LEFT JOIN (SELECT userId, GROUP_CONCAT(fileName) AS fileName FROM UserProject GROUP BY userId) AS p ON u.userId = p.userId" +
                    " LEFT JOIN (SELECT userId, COUNT(*) AS totalLikes FROM userLike GROUP BY userId) AS l ON u.userId = l.userId"+
                    " LEFT JOIN (SELECT userId, COUNT(*) AS totalViews FROM userviews GROUP BY userId) AS v ON u.userId = v.userId GROUP BY u.userId";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> creator = new HashMap<String, Object>();
                creator.put("userId", rs.getString("userId"));
                creator.put("userName", rs.getString("userName"));
                creator.put("userImage", rs.getString("userImage"));
                String imageFile = rs.getString("fileNames");
                creator.put("imageFiles", imageFile);
                creator.put("like", rs.getInt("totalLikes"));
                creator.put("view", rs.getInt("totalViews"));
                Creatorlist.add(creator);
            }

            System.out.println("Creatorlist success");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Creatorlist Fail");
        }finally {
            DBClose();
        }
        return Creatorlist;
    }


}
