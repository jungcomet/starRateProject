package com.star.contents;

import com.star.MainController;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ContentsService {
    ContentsDAO contentsDAO = new ContentsDAO();
    Scanner sc = new Scanner(System.in);
    String sysMessage = "SYSTEM>> ";
    String selectMessage = "       * ";

    public void printContentInfo(ContentsDTO content) {
        System.out.println(content);
    }
    /*
    1. 이름 검색
    2. 날짜 이전검색
    3. 날짜 당일 검색
    4. 날짜 이후검색
    5. 감독 검색
    6. 배우 검색
    7. 장르 검색
    8. 특정별점 이상 작품 검색
    9. 특정별점 이하 작품 검색
    10. 통계
     */

    public HashMap<Integer, ContentsDTO> searchContents(String inputStr, int selectNum) throws ParseException {
        switch (selectNum) {
            case 2: case 3: case 4:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(inputStr);
                return contentsDAO.selectContents(date, selectNum);
            default:
                return contentsDAO.selectContents(inputStr, selectNum);
        }
    }

    public HashMap<Integer, ContentsDTO> searchContents(double inputGPA, int selectNum) throws ParseException {
        return contentsDAO.selectContentsByGPA(inputGPA, selectNum);
    }


    public int printContents(String str, int input2) throws ParseException {
        HashMap<Integer, ContentsDTO> contents = new HashMap<>(searchContents(str, input2));
        int cNO;
        if (contents.isEmpty()) {
            cNO = 0;
        } else {
            for (int i : contents.keySet()) {
                System.out.println("####### 순번 : " + i);
                System.out.println(contents.get(i));
            }
            System.out.print(selectMessage + "별점 평가할 작품의 순번을 입력하세요 >> ");
            int select = sc.nextInt();

            if (contents.containsKey(select)) {
                cNO = contents.get(select).getcNO();
            } else {
                System.out.println(sysMessage + "번호를 잘못 입력하셨습니다.");
                cNO = 0;
            }
        }
        return cNO;
    }


    public int printContentsByGPA(double GPA, int input2) throws ParseException {
        HashMap<Integer, ContentsDTO> contents = new HashMap<>(searchContents(GPA, input2));
        int cNO;
        if (contents.isEmpty()) {
            cNO = 0;
        } else {
            for (int i : contents.keySet()) {
                System.out.println("####### 순번 : " + i);
                System.out.println(contents.get(i));
            }
            System.out.print(selectMessage + "별점 평가할 작품의 순번을 입력하세요 >> ");
            int select = sc.nextInt();

            if (contents.containsKey(select)) {
                cNO = contents.get(select).getcNO();
            } else {
                System.out.println(sysMessage + "번호를 잘못 입력하셨습니다.");
                cNO = 0;
            }
        }
        return cNO;
    }
}
