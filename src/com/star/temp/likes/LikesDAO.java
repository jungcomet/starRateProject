package com.star.temp.likes;

import com.star.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LikesDAO {
    static String sysMessage = "SYSTEM>> ";

    public static boolean insertLikesBycNO(int mNO, int cNO) {
        // insert into likes values (mNO, cNO);

        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "insert into likes values (?, ?)";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNO);
            st.setInt(2, cNO);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return true;
    }

    public static boolean deleteLikesBycNO(int mNO, int cNO) {
        // delete from likes where mNO = ? and cNO = ?;

        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "delete from likes where mNO = ? and cNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNO);
            st.setInt(2, cNO);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return true;
    }

    public LikesDTO makeLike(ResultSet rs) throws SQLException {
        return new LikesDTO(rs.getInt("mNO"), rs.getInt("cNO"));
    }

    public HashMap<Integer, String> selectLikesBymNO (int mNO) {
        HashMap<Integer, String> likesMap = new HashMap<>();

        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select likes.cNO, contents.cNAME from likes, CONTENTS where LIKES.mNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNO);
            rs = st.executeQuery();
            while (rs.next()) {
                likesMap.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return likesMap;
    }
}
