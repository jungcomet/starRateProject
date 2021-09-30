package com.star.rating;

import com.star.contents.ContentsDAO;
import com.star.contents.ContentsDTO;

import java.util.HashMap;
import java.util.Scanner;

public class RatingService {
    RatingDAO ratingDAO = new RatingDAO();
    Scanner sc = new Scanner(System.in);

    String sysMessage = "SYSTEM>> ";
    String selectMessage = "       * ";
    int mNO;
    int rScore;

    public void makeRating(int cNO) {
        ContentsDAO contentsDAO = new ContentsDAO();
        /*
        HashMap<Integer, String> contentNameMap = contentsDAO.selectContentsByCNO(cNO);
        System.out.println(sysMessage + "\"" + contentNameMap.get(cNO) + "\"" + " 작품의 별점을 매기겠습니다.");
         */

        while (true) {
            System.out.println(sysMessage + "0 ~ 10점까지 점수를 정수로 입력하세요.");
            System.out.print(selectMessage + "입력 >> ");
            rScore = sc.nextInt();

            boolean rScoreScope = rScore >= 0 && rScore <= 10;

            if (rScoreScope) {
                ratingDAO.rating(mNO, cNO, rScore);
                ratingDAO.updateRating(cNO, rScore);
                break;
            } else {
                System.out.println(sysMessage + "점수를 다시 입력해주세요.");
            }
        }
    }
}
