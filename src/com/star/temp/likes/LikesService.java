package com.star.temp.likes;

import com.star.member.MemberDAO;

import java.util.HashMap;

public class LikesService {
    LikesDAO likesDAO = new LikesDAO();
    MemberDAO memberDAO = new MemberDAO();
    String sysMessage = "SYSTEM>> ";
    String selectMessage = "       * ";

    public void selectLikesBymNO (int mNO) {
        HashMap<Integer, String> likesMap = likesDAO.selectLikesBymNO(mNO);
        String mName = MemberDAO.selectmNameBymNO(mNO);
        System.out.println(sysMessage + mName + "님이 좋아요한 작품 목록입니다.");
        for (int i : likesMap.keySet()) {
            System.out.println("[" + i + "] " + likesMap.get(i));
            System.out.println(selectMessage + "총 " + i + "건");
        }
    }

    public boolean insertLikesBycNO (int mNO, int cNO) {
        boolean insert = LikesDAO.insertLikesBycNO(mNO, cNO);
        String mName = MemberDAO.selectmNameBymNO(mNO);
        if (insert) {
            System.out.println(sysMessage + mName + " 작품에 좋아요를 눌렀습니다.");
            return true;
        } else {
            System.out.println(sysMessage + mName + " 작품에 좋아요를 누르지 못했습니다.");
            return false;
        }
    }

    public boolean deleteLikesBycNO (int mNO, int cNO) {
        boolean delete = LikesDAO.deleteLikesBycNO(mNO, cNO);
        String mName = MemberDAO.selectmNameBymNO(mNO);
        if (delete) {
            System.out.println(sysMessage + mName + " 작품에 좋아요 누르기를 취소했습니다.");
            return true;
        } else {
            System.out.println(sysMessage + mName + " 작품에 좋아요 취소를 실패했습니다.");
            return false;
        }
    }
}
