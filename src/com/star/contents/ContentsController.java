package com.star.contents;

import com.star.MainController;

import java.text.ParseException;
import java.util.Scanner;

public class ContentsController {
    static ContentsService contentsService = new ContentsService();
    static Scanner sc = new Scanner(System.in);
    static String sysMessage = "SYSTEM>> ";
    static String selectMessage = "       * ";

    public static void searchContentsController() throws ParseException {
        System.out.println(sysMessage + "작품을 검색합니다.");
        System.out.println(sysMessage + "검색 방법을 선택해주세요.");
        System.out.println("         [1] 작품명으로 검색"+'\t'+ "[2] 발매일 검색"+'\t'+"[3] 감독 검색");
        System.out.println("         [4] 배우 검색"+'\t'+ "[5] 장르 검색"+'\t'+ "[6] 별점으로 검색");
        System.out.print(selectMessage + "입력 >> ");
        int input = sc.nextInt();

        if (input < 1 || input > 6) {
            System.out.println(sysMessage + "다시 입력해주세요.");
        } else searchContents(input);
    }

    private static void searchContents(int input) throws ParseException {
        String str = null;
        int input2 = 1;
        double inputGPA = 0.0;
        switch (input) {
            case 1:
                System.out.print(selectMessage + "작품명을 입력해주세요 >> ");
                str = sc.next();
                break;
            case 2:
                System.out.print(selectMessage + "발매일을 yyyy-MM-dd의 형태로 입력해주세요 >> ");
                str = sc.next();
                System.out.print(selectMessage + str + " 이전 발매작을 검색하려면 1을, " + str + " 당일 발매작을 검색하려면 2를, " + str + " 이후 발매작을 검색하려면 3을 입력하세요 >> ");
                int intTmp = sc.nextInt();
                input2 += intTmp;
                break;
            case 3:
                System.out.print(selectMessage + "감독을 입력해주세요 >> ");
                str = sc.next();
                input2 = 5;
                break;
            case 4:
                System.out.print(selectMessage + "배우를 입력해주세요 >> ");
                str = sc.next();
                input2 = 6;
                break;
            case 5:
                System.out.print(selectMessage + "장르를 입력해주세요 >> ");
                str = sc.next();
                input2 = 7;
                break;
            case 6:
                System.out.println(sysMessage + "입력하신 별점 이상 또는 이하 작품을 검색할 수 있습니다.");
                System.out.print(selectMessage + "별점을 입력해주세요 >> ");
                inputGPA = sc.nextDouble();
                System.out.print(selectMessage + inputGPA + "점 이상의 발매작을 검색하려면 1을, " + inputGPA + "점 이하의 발매작을 검색하려면 2를 입력하세요 >> ");
                intTmp = sc.nextInt();
                input2 += (intTmp + 6);
                break;
            default:
                break;
        }
        System.out.println(sysMessage + "검색중입니다...");
        if (input == 6) {
            MainController.cNO = contentsService.printContentsByGPA(inputGPA, input2);
        } else {
            MainController.cNO = contentsService.printContents(str, input2);
        }
    }


    public static void main(String[] args) throws ParseException {
        searchContentsController();
    }
}
