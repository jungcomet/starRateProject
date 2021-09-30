package com.star;

import com.star.contents.ContentsController;
import com.star.member.MemberController;
import com.star.member.MemberService;
import com.star.rating.RatingController;

import java.lang.reflect.Member;
import java.text.ParseException;
import java.util.Scanner;

public class MainController {
    public static int mNO = 0;
    public static int cNO = 0;
    public static void main(String[] args) {
        String sysMessage = "SYSTEM>> ";
        String selectMessage = "       * ";
        Scanner sc = new Scanner(System.in);
        try {
            MemberController.printSelectMenu();

            boolean run = true;
            while (run) {
                System.out.println(sysMessage + "작품에 별점을 평가하려면 먼저 검색해야 합니다.");
                System.out.println(selectMessage + "[1] 검색" + '\t' + "[2] 회원정보 보기" + '\t' + "[3] 로그아웃");
                System.out.print(selectMessage + "선택 >> ");
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        ContentsController.searchContentsController();
                        RatingController ratingController = new RatingController();
                        ratingController.makeRating(cNO);
                        break;
                    case 2:
                        MemberService memberService = new MemberService();
                        memberService.myPage(MemberService.getmID(mNO));
                        break;
                    case 3:
                        System.out.println(sysMessage + "정상적으로 로그아웃 되었습니다.");
                        run = false;
                        break;
                    default:
                        System.out.println(sysMessage + "다시 입력하세요.");
                        break;
                }
            }
        } catch (InterruptedException | ParseException e) {
            e.printStackTrace();
        }
    }
}
