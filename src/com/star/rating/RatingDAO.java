package com.star.rating;

import com.star.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RatingDAO {
    static String sysMessage = "SYSTEM>> ";

    public RatingDTO makeRating(ResultSet rs) throws SQLException {
        return new RatingDTO(rs.getInt("mNO"), rs.getInt("cNO"), rs.getInt("rSCORE"));
    }

    public void rating(int mNo, int cNo, int rScore) {
        // insert into rating values(mNO, cNO, rScore)
        String sysMessage = "SYSTEM>> ";
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "insert into rating values (?, ?, ?)";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNo);
            st.setInt(2, cNo);
            st.setInt(3, rScore);
            rs = st.executeQuery();

            System.out.println(sysMessage + "평점을 매겼습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn,st,rs);
        }
    }

    public void updateRating(int cNO, int rScore) {
        // update contents set cRatingCount = cRatingCount + 1, cACCRating = cACCRating + rScore where cNO = ?

        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "update contents set cRatingCount = cRatingcount + 1, cAccrating = cAccrating + ? where cNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, rScore);
            st.setInt(2, cNO);
            rs = st.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn,st,rs);
        }
    }

    public List<RatingDTO> myRating(int mNO) {
        ArrayList<RatingDTO> ratingList = new ArrayList<>();
        // select cNO, rSCORE from rating where mNO = ?
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select cNO, rSCORE from rating where mNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNO);
            rs = st.executeQuery();

            while (rs.next()) {
                ratingList.add(makeRating(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn,st,rs);
        }

        return ratingList;
    }
}
