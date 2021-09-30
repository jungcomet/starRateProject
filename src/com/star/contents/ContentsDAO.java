package com.star.contents;

import com.star.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ContentsDAO {

    public ContentsDTO makeContents(ResultSet rs) throws SQLException {
        return new ContentsDTO(rs.getInt("cNO"),
                rs.getString("cNAME"),
                rs.getDate("cDATE"),
                rs.getString("cDIRECTOR"),
                rs.getString("cACTOR"),
                rs.getString("cGENRE"),
                rs.getInt("cSERISE"),
                rs.getInt("cRUNNINGTIME"),
                rs.getInt("cAGELIMIT"),
                rs.getString("cDETAIL"),
                rs.getInt("cRATINGCOUNT"),
                rs.getInt("cACCRATING"),
                rs.getInt("cWISHCOUNT"));
    }

    public HashMap<Integer, String> selectContentsByCNO(int cNO) {
        HashMap<Integer, String> contentsMap = new HashMap<>();
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select cNAME from contents where cNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, cNO);
            rs = st.executeQuery();
            while (rs.next()) {
                contentsMap.put(cNO, rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return contentsMap;
    }

    public HashMap<Integer, ContentsDTO> selectContents(String inputStr, int controlNum) {

        HashMap<Integer, ContentsDTO> contentsMap = new HashMap<>();
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;

        String NameSQL = "select * from contents where cNAME like '%" + inputStr + "%'";
        String DirectorSQL = "select * from contents where cDIRECTOR = ?";
        String ActorSQL = "select * from contents where cACTOR like '%" + inputStr + "%'";
        String GenreSQL = "select * from contents where cGENRE = ?";

        try {
            switch (controlNum) {
                case 1:
                    st = conn.prepareStatement(NameSQL);
                    break;
                case 5:
                    st = conn.prepareStatement(DirectorSQL);
                    st.setString(1, inputStr);
                    break;
                case 6:
                    st = conn.prepareStatement(ActorSQL);
                    break;
                case 7:
                    st = conn.prepareStatement(GenreSQL);
                    st.setString(1, inputStr);
                    break;
                default:
                    break;
            }

            rs = st.executeQuery();
            int key = 0;
            while (rs.next()) {
                key++;
                contentsMap.put(key, makeContents(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return contentsMap;
    }

    public HashMap<Integer, ContentsDTO> selectContents(Date date, int controlNum) {
        HashMap<Integer, ContentsDTO> contentsMap = new HashMap<>();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;

        String DateBeforeSQL = "select * from contents where cDATE <= ?";
        String DateSameSQL = "select * from contents where cDATE = ?";
        String DateAfterSQL = "select * from contents where cDATE >= ?";

        try {
            switch (controlNum) {
                case 2:
                    st = conn.prepareStatement(DateBeforeSQL);
                    break;
                case 3:
                    st = conn.prepareStatement(DateSameSQL);
                    break;
                case 4:
                    st = conn.prepareStatement(DateAfterSQL);
                    break;
                default:
                    break;
            }

            st.setDate(1, sqlDate);
            rs = st.executeQuery();
            int key = 0;
            while (rs.next()) {
                key++;
                System.out.println(key);
                contentsMap.put(key, makeContents(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return contentsMap;
    }

    public HashMap<Integer, ContentsDTO> selectContentsByGPA(double gpa, int controlNum) {
        HashMap<Integer, ContentsDTO> contentsMap = new HashMap<>();
        HashMap<Integer, ContentsDTO> resultMap = new HashMap<>();
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from contents";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int key = 0;
            while (rs.next()) {
                key++;
                contentsMap.put(key, makeContents(rs));
            }

            switch (controlNum) {
                case 8:
                    for (int i : contentsMap.keySet()) {
                        if (contentsMap.get(i).getGPA() >= gpa) resultMap.put(i, contentsMap.get(i));
                    }
                    break;
                case 9:
                    for (int i : contentsMap.keySet()) {
                        if (contentsMap.get(i).getGPA() <= gpa) resultMap.put(i, contentsMap.get(i));
                    }
                    break;
                default:
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return resultMap;
    }
}