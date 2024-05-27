package org.teamproject.teamproject.Service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserViewService extends DBService{


    public int countUserViewByUserId(int userId){
        int totalView = 0;
        conn = getConnect();

        try {
            String sql = "select count(userid) as totalViews from userviews where userid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                totalView = rs.getInt("totalViews");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            DBClose();
        }
        return totalView;
    }
}
