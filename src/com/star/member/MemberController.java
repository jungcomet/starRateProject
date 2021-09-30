package com.star.member;

import com.star.MainController;

import java.util.Scanner;

public class MemberController {
    static MemberService service = new MemberService();
    static String sysMessage = "SYSTEM>> ";

    public static void printStartMenu() throws InterruptedException {
        /*
        System.out.print("                                    접속중.");
        for (int i = 0 ; i < 15; i ++) {
            Thread.sleep(150);
            System.out.print(".");
        }
        */
        System.out.println();
        System.out.println();
        //Thread.sleep(500);
    }

    public static void printSelectMenu() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println(sysMessage + "환영합니다!");
        //Thread.sleep(500);
        System.out.println(sysMessage + "별점을 매기려면 로그인하세요.");
        //Thread.sleep(500);
        while (true) {
            System.out.println("         [1] 로그인"+'\t'+ "[2] 회원가입"+'\t'+"[3] 아이디/비밀번호 찾기");
            System.out.println(sysMessage + "메뉴를 선택하세요.");
            System.out.print(service.selectMessage + "입력 >> ");
            int input = sc.nextInt();
            if (input == 1) {
                MainController.mNO = service.login();
                break;
            } else if (input == 2) {
                service.signIn();
            } else if (input == 3) {
                service.findIDPW();
            } else {
                System.out.println(sysMessage + "다시 입력하세요.");
            }
        }
    }
}
