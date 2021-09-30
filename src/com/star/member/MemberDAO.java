package com.star.member;

import com.star.util.DBUtil;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MemberDAO {
    static String sysMessage = "SYSTEM>> ";

    public MemberDTO makeMember(ResultSet rs) throws SQLException{
        return new MemberDTO(rs.getInt("mNO"), rs.getString("mID"), rs.getString("mNAME"), rs.getString("mPW"), rs.getString("mEMAIL"), rs.getString("mPHONE"));
    }

    public boolean login(String id, String pw) {
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;

        String selectPWSql = "select mID, mPW from members where mID = ?";
        String mID = null;
        String mPW = null;

        try {
            st = conn.prepareStatement(selectPWSql);
            st.setString(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                mID = rs.getString(1);
                mPW = rs.getString(2);
            }

            if (id.equals(mID) && pw.equals(mPW)) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }
    public int getmNO(String id) {
        // select mNO from members where mID = ?
        int mNO = 0;
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select mNO from members where mID = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                mNO = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return mNO;
    }

    public String getmID(int mNO) {
        // select mID from members where mNO = ?
        String mID = null;
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select mID from members where mNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNO);
            rs = st.executeQuery();

            while (rs.next()) {
                mID = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return mID;
    }

    public String getmName(int mNO) {
        // select mName from members where mNO = ?
        String mName = null;
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select mName from members where mNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNO);
            rs = st.executeQuery();

            while (rs.next()) {
                mName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return mName;
    }

    public void signIn(MemberDTO member) {
        //insert into MEMBERS values(id, name, pw, email, phone)

        String sysMessage = "SYSTEM>> ";
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "insert into members (mID, mNAME, mPW, mEMAIL, mPHONE, pwNO, pwANSWER) values(?, ?, ?, ?, ?, ?, ?)";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, member.getmID());
            st.setString(2, member.getmName());
            st.setString(3, member.getmPW());
            st.setString(4, member.getmEmail());
            st.setString(5, member.getmPhone());
            st.setInt(6, member.getPwNO());
            st.setString(7, member.getPwAnswer());
            rs = st.executeQuery();

            System.out.println(sysMessage + "회원가입이 완료되었습니다.");
            System.out.println(sysMessage + "다시 로그인해 주세요.");
        } catch (SQLException e) {
            System.out.println(sysMessage + "회원가입 실패");
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }

    public List<PWQuestionDTO> selectPwQuestion() {
        // select * from pwquestion;
        List<PWQuestionDTO> questionList = new ArrayList<>();

        Connection conn = DBUtil.dbConnect();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from PWQUESTION";
        int pwNO = 0;
        String pwQuestion = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                pwNO = rs.getInt(1);
                pwQuestion = rs.getString(2);

                questionList.add(new PWQuestionDTO(pwNO, pwQuestion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return questionList;
    }

    public String selectPWQuestion (int pwNO) {
        //select PWQUESTION from PWQUESTION where pwNO = ?
        String pwQuestion = null;

        Connection conn = DBUtil.dbConnect();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select p.PWQUESTION from PWQUESTION p where pwNO = " + pwNO;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                pwQuestion = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return pwQuestion;
    }

    public List<Integer> selectPWNO() {
        List<Integer> pwNOArr = new ArrayList<>();
        //select pwNO from pwQuestion;

        Connection conn = DBUtil.dbConnect();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select pwNO from pwQuestion";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                pwNOArr.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        return pwNOArr;
    }

    public boolean checkID(String id) {
        //select count(*) from members where mid = ?
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select count(*) from MEMBERS where mID = ?";
        int result = 0;

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }

        if (result == 0) return true; // 중복된 아이디 없으므로 가입 가능
        else return false;
    }

    public boolean checkPW(String id, String pw) {
        // select mNAME from members where mID = id and mPW = pw
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select mNAME from MEMBERS where mID = ? and mPW = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, pw);
            rs = st.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }

    public void updateMNO(MemberDTO member) {
        //select mNO from members where mID = ?
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select cNO from MEMBERS where mID = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, member.getmID());
            rs = st.executeQuery();

            while (rs.next()) {
                int newMNO = rs.getInt(1);
                member.setmNO(newMNO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }

    public MemberDTO selectAllFromMember(String id) {
        //select * from members where mID = ?
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from MEMBERS where mID = ?";
        int mNO = 0;
        String mID = null;
        String mNAME = null;
        String mPW = null;
        String mPHONE = null;
        String mEMAIL = null;
        int pwNO = 0;
        String pwANSWER = null;

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                mNO = rs.getInt(1);
                mID = rs.getString(2);
                mNAME = rs.getString(3);
                mPW = rs.getString(4);
                mPHONE = rs.getString(5);
                mEMAIL = rs.getString(6);
                pwNO = rs.getInt(7);
                pwANSWER = rs.getString(8);
            }
        } catch (SQLException e) {
            System.out.println("아이디를 찾을 수 없습니다. 다시 확인하세요.");
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return new MemberDTO(mNO, mID, mNAME, mPW, mPHONE, mEMAIL, pwNO, pwANSWER);
    }

    public boolean withdrawal(MemberDTO member) {
        // delete from members where mID = ?
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "delete from members where mID = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, member.getmID());
            rs = st.executeQuery();
        } catch (Exception e) {
            System.out.println("회원탈퇴에 실패했습니다.");
            return false;
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return true;
    }

    public void modifyPW(String mID, String newPW) {
        // update members set mPW = newPW where mID = ?

        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "update members set mPW = ? where mID = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, newPW);
            st.setString(2, mID);
            rs = st.executeQuery();
        } catch (Exception e) {
            System.out.println(sysMessage + "비밀번호 수정에 실패했습니다.");
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }

    public void modifyEmail(String mID, String newEmail) {
        // update members set mEMAIL = ? where mID = ?

        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "update members set mEMAIL = ? where mID = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, newEmail);
            st.setString(2, mID);
            rs = st.executeQuery();
        } catch (Exception e) {
            System.out.println(sysMessage + "메일주소 수정에 실패했습니다.");
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }

    public void modifyPhone(String mID, String newPhone) {
        // update members set mPHONE = ? where mID = ?
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "update members set mPHONE = ? where mID = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, newPhone);
            st.setString(2, mID);
            rs = st.executeQuery();
        } catch (Exception e) {
            System.out.println(sysMessage + "전화번호 수정에 실패했습니다.");
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }

    public void modifyPWQuestion(String mID, int newPWNO, String newPWAnswer) {
        // update members set pwNO = ? and pwAnswer = ? where mID = ?

    }

    public String findID(String name, String phone, int pwNO, String pwAnswer) {
        //select mID from members where mNAME = ? and mPHONE = ? and pwNO = ? and pwANSWER = ?
        String mID = null;

        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select mID from members where mNAME = ? and mPHONE = ? and pwNO = ? and pwANSWER = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, phone);
            st.setInt(3, pwNO);
            st.setString(4, pwAnswer);
            rs = st.executeQuery();

            while (rs.next()) {
                mID = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return mID;
    }

    public boolean findPW(String id, String name, String phone, int pwNO, String pwAnswer) {
        //select * from members where mID = ? and mNAME = ? and mPHONE = ? and pwNO = ? and pwANSWER = ?
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from members where mid = ? and mname = ? and mphone = ? and pwno = ? and pwanswer = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, phone);
            st.setInt(4, pwNO);
            st.setString(5, pwAnswer);
            rs = st.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
    }

    public static String selectmNameBymNO (int mNo) {
        Connection conn = DBUtil.dbConnect();
        PreparedStatement st = null;
        ResultSet rs = null;
        String mName = null;
        String sql = "select mNAME from members where mNO = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mNo);
            rs = st.executeQuery();

            while (rs.next()) {
                mName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, st, rs);
        }
        return mName;
    }
}
