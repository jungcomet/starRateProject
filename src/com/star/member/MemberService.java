package com.star.member;

import com.star.MainController;

import java.util.*;

public class MemberService {
    static Scanner sc = new Scanner(System.in);
    static MemberDAO member = new MemberDAO();

    static String sysMessage = "SYSTEM>> ";
    static String selectMessage = "       * ";


    public int login() throws InterruptedException {
        String id = null;
        String pw = null;
        String name = null;
        int mNO = 0;
        boolean idInputNull;

        while (true) {
            System.out.println(sysMessage + "[1] 로그인을 진행합니다.");
            System.out.print(selectMessage + "아이디 입력 >> ");
            id = sc.next();
            System.out.print(selectMessage + "비밀번호 입력 >> ");
            pw = sc.next();
            if (!id.isEmpty() && !pw.isEmpty()) {
                if (member.login(id, pw)) {
                    mNO = member.getmNO(id);
                    name = member.getmName(mNO);
                    System.out.println(sysMessage + name + "님이 로그인하셨습니다.");
                    break;
                } else {
                    System.out.println(sysMessage + "입력하신 아이디와 비밀번호를 다시 확인하세요.");
                }
            } else System.out.println(sysMessage + "입력하신 아이디와 비밀번호를 다시 확인하세요.");
        }

        return mNO;
    }

    public void signIn() {
        int pwQuestionNum = 0;
        String id = null;
        String pw = null;
        String name = null;
        String email = null;
        String phone = null;
        String pwQuestion = null;
        String pwAnswer = null;
        System.out.println(sysMessage + "[2] 회원가입을 진행합니다.");

        id = this.makeID();
        pw = this.makePW();
        name = this.makeName();
        email = this.makeEmail();
        phone = this.makePhone();
        pwQuestionNum = this.makePWQuestionNum();
        HashMap<Integer, String> pwQuestions = this.makePWQuestion(pwQuestionNum);
        pwQuestion = pwQuestions.get(pwQuestionNum);
        pwAnswer = this.makePWAnswer();

        // 가입정보 확인
        while (true) {
            String input;
            System.out.println(sysMessage + "입력하신 정보는 다음과 같습니다.");
            System.out.println(selectMessage + "아이디" +'\t'+'\t'+'\t'+ " : " + id);
            System.out.println(selectMessage + "이름" +'\t'+'\t'+'\t'+ " : " + name);
            System.out.println(selectMessage + "메일주소" +'\t'+'\t'+ " : " + email);
            System.out.println(selectMessage + "전화번호" +'\t'+'\t'+ " : " + phone);
            System.out.println(selectMessage + "비밀번호 찾기 질문" +'\t'+ " : " + pwQuestion);
            System.out.println(selectMessage + "비밀번호 찾기 답변" +'\t'+ " : " + pwAnswer);
            System.out.print(sysMessage + "이대로 가입하시겠습니까? (Y / N) >> ");
            input = sc.next();

            if (input.equalsIgnoreCase("Y")) {
                MemberDTO newMember =  new MemberDTO(id, name, pw, email, phone, pwQuestionNum, pwAnswer);
                System.out.println(newMember);
                member.signIn(newMember);
                break;
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println(sysMessage + "회원가입을 다시 진행하겠습니다.");
                break;
            } else {
                System.out.println(sysMessage + "잘못 입력하셨습니다.");
            }
        }
    }

    public String makeID() {
        while (true) {
            System.out.print(selectMessage + "아이디를 입력하세요 >> ");
            String id = sc.next();
            System.out.println(sysMessage + "중복된 아이디가 있는지 확인합니다...");
            if (this.checkID(id)) {
                System.out.println(sysMessage + "사용가능한 아이디입니다.");
                return id;
            } else {
                System.out.println(sysMessage + "중복된 아이디가 있습니다. 다른 아이디를 입력하세요.");
            }
        }
    }

    public String makePW() {
        while (true) {
            String pw, pw2;
            System.out.print(selectMessage + "비밀번호를 입력하세요 >> ");
            pw = sc.next();
            System.out.print(selectMessage + "비밀번호를 다시 한 번 입력하세요 >> ");
            pw2 = sc.next();

            if (pw.isEmpty() || pw2.isEmpty()) {
                System.out.println(sysMessage + "비밀번호를 다시 입력해주세요.");
            } else if (!pw.equals(pw2)) {
                System.out.println(sysMessage + "입력하신 비밀번호가 서로 다릅니다. 다시 입력해주세요.");
            } else return pw;
        }
    }

    public String makeName() {
        while (true) {
            String name;
            System.out.print(selectMessage + "이름을 입력하세요 (필수) >> ");
            name = sc.next();
            if (name.isEmpty()) {
                System.out.println(sysMessage + "이름을 다시 입력해주세요.");
            } else return name;
        }
    }

    public String makeEmail() { // null 허용
        String email;
        System.out.print(selectMessage + "메일주소를 입력하세요 >> ");
        email = sc.next();
        return email;
    }

    public String makePhone() {
        while (true) {
            String phone;
            System.out.print(selectMessage + "휴대폰번호를 입력하세요 (필수) >> ");
            phone = sc.next();
            if (phone.isEmpty()) {
                System.out.println(sysMessage + "휴대폰번호를 다시 입력하세요.");
            } else return phone;
        }
    }

    public int makePWQuestionNum() {
        while (true) {
            int pwQuestionNum;
            System.out.println(sysMessage + "비밀번호을 찾기 위해 필요한 질문들입니다.");
            List<Integer> questionNOList = member.selectPWNO();
            List<PWQuestionDTO> questionList = member.selectPwQuestion();

            for (PWQuestionDTO question : questionList) {
                System.out.println(question);
            }
            System.out.print(selectMessage + "질문번호를 입력하세요 (필수) >> ");
            pwQuestionNum = sc.nextInt();

            if (questionNOList.contains(pwQuestionNum)) return pwQuestionNum;
            else System.out.println(sysMessage + "질문번호를 잘못 입력하셨습니다.");
        }
    }

    public HashMap<Integer, String> makePWQuestion(int pwQuestionNum) {
        String pwQuestionStr = member.selectPWQuestion(pwQuestionNum);

        return new HashMap<Integer,String>() {{put(pwQuestionNum, pwQuestionStr);}};
    }

    public String makePWAnswer () {
        while (true) {
            String pwAnswer;
            System.out.print(selectMessage + "답변을 입력하세요 (필수) >> ");
            pwAnswer = sc.next();
            if (pwAnswer.isEmpty()) System.out.println(sysMessage + "답변을 잘못 입력하셨습니다.");
            else return pwAnswer;
        }
    }

    public void myPage(String id) {
        MemberDTO member1 = member.selectAllFromMember(id);
        System.out.println(member1);
        System.out.println(sysMessage + "회원정보조회를 종료합니다.");
    }
    /*
    public static void withdrawal(MemberDTO member) {
        do {
            System.out.println(sysMessage + "회원탈퇴를 진행합니다.");
            System.out.print(selectMessage + "아이디를 입력하세요 >> ");
            String id = sc.next();
            System.out.print(selectMessage + "비밀번호를 다시 한 번 입력하세요 >> ");
            String pw = sc.next();
            if (member.checkPW(id, pw)) {
                System.out.print(selectMessage + "정말로 탈퇴하시겠습니까? (Y / N) >> ");
                String input = null;
                input = sc.next();
                if (input.equalsIgnoreCase("Y")) {
                    if (member.withdrawal(member)) {
                        System.out.println(sysMessage + "회원탈퇴가 완료되었습니다.");
                        break;
                    } else {
                        System.out.println(sysMessage + "회원탈퇴에 실패했습니다.");
                    }
                } else {
                    System.out.println(sysMessage + "다시 입력하세요.");
                }
            } else {
                System.out.println(sysMessage + "아이디 또는 비밀번호가 틀렸습니다.");
            }
        } while (true);
    }

    public void modifyMemberInfo(int member) {
        System.out.println(sysMessage + "회원정보를 수정합니다.");
        // 비밀번호 확인
        System.out.print(selectMessage + "비밀번호를 입력하세요 >> ");
        String pw = sc.next();
        if (this.member.login(member.getmID(), pw)) {
            System.out.println(member);
            System.out.println(sysMessage + "수정하려는 항목" +
                    "의 번호를 입력하세요.");
            // 비밀번호, 메일주소, 전화번호, 비밀번호 찾기 질문 수정가능
            System.out.println(selectMessage + "[1] 비밀번호"+'\t'+"[2] 메일주소"+'\t'+"[3] 전화번호"+'\t'+"[4] 비밀번호 찾기 질문");
            System.out.print(selectMessage + "입력 >> ");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    this.modifyPW(member.getmID());
                    break;
                case 2:
                    this.modifyEmail(member.getmID());
                    break;
                case 3:
                    this.modifyPhone(member.getmID());
                    break;
                case 4:
                    this.modifyPWQuestion(member.getmID());
                    break;
                default:
                    System.out.println(sysMessage + "잘못 입력하셨습니다.");
            }
        } else {
            System.out.println(sysMessage + "비밀번호를 잘못 입력하셨습니다.");
        }
    }
*/
    public void modifyPW(String mID) {
        System.out.println(sysMessage + "비밀번호를 새롭게 수정하겠습니다.");
        member.modifyPW(mID, this.makePW());
    }

    public void modifyEmail(String mID) {
        System.out.println(sysMessage + "메일주소를 수정하겠습니다.");
        member.modifyEmail(mID, makeEmail());
    }

    public void modifyPhone(String mID) {
        System.out.println(sysMessage + "전화번호를 수정하겠습니다.");
        member.modifyPhone(mID, this.makePhone());
    }

    public void modifyPWQuestion(String mID) {
        System.out.println(sysMessage + "비밀번호 찾기 질문과 답변을 수정하겠습니다.");
        member.modifyPWQuestion(mID, this.makePWQuestionNum(), this.makePWAnswer());
    }

    public boolean checkID(String id) { // 중복 아이디 체크
        if (member.checkID(id)) return true;
        else return false;
    }

    public boolean checkPW(String pw, String pw2) {
        if (pw.isEmpty()) return false;
        else if (pw.equals(pw2)) return true;
        else return false;
    }

    public boolean checkName(String name) {
        if (name.isEmpty()) return false;
        else return true;
    }

    public void findIDPW() {
        System.out.println(sysMessage + "아이디, 비밀번호를 잊으셨나요?");
        System.out.println(selectMessage + "[1] 아이디 찾기" +'\t'+ "[2] 비밀번호 찾기");
        System.out.print(selectMessage + "입력 >> ");
        int input = sc.nextInt();
        String id, name, phone, pwAnswer;
        int pwNO;

        switch (input) {
            case 1:
                System.out.print(selectMessage + "이름을 입력하세요 >> ");
                name = sc.next();
                System.out.print(selectMessage + "가입당시 입력한 전화번호를 입력하세요 >> ");
                phone = sc.next();
                System.out.println(sysMessage + "미리 설정한 비밀번호 찾기 질문을 선택하세요.");
                pwNO = this.makePWQuestionNum();
                System.out.print(selectMessage + "답변을 입력하세요 >> ");
                pwAnswer = sc.next();

                id = this.findID(name, phone, pwNO, pwAnswer);
                if (id != null) {
                    System.out.println(sysMessage + name + "님의 아이디는 " + id + "입니다.");
                } else {
                    System.out.println(sysMessage + "입력하신 정보를 찾을 수 없습니다.");
                }
                break;
            case 2:
                System.out.print(selectMessage + "아이디를 입력하세요 >> ");
                id = sc.next();
                System.out.print(selectMessage + "이름을 입력하세요 >> ");
                name = sc.next();
                System.out.print(selectMessage + "가입당시 입력한 전화번호를 입력하세요 >> ");
                phone = sc.next();
                System.out.println(sysMessage + "미리 설정한 비밀번호 찾기 질문을 선택하세요.");
                pwNO = this.makePWQuestionNum();
                System.out.print(selectMessage + "답변을 입력하세요 >> ");
                pwAnswer = sc.next();

                if (this.findPW(id, name, phone, pwNO, pwAnswer)) {
                    System.out.println(sysMessage + "비밀번호를 수정했습니다.");
                } else {
                    System.out.println(sysMessage + "입력하신 정보를 찾을 수 없습니다.");
                }
                break;
            default:
                System.out.println(sysMessage + "다시 입력하세요.");
        }
    }

    public String findID(String name, String phone, int pwNO, String pwAnswer) {
        return member.findID(name, phone, pwNO, pwAnswer);
    }

    public boolean findPW(String id, String name, String phone, int pwNO, String pwAnswer) {
        if (member.findPW(id, name, phone, pwNO, pwAnswer)) {
            this.modifyPW(id);
            return true;
        } else return false;
    }

    public static String getmID(int mNO) {
        return member.getmID(mNO);
    }

    public static String getmName(int mNO) {
        return member.getmName(mNO);
    }
}
