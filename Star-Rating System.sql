-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- RATING Table Create SQL
CREATE TABLE RATING
(
    mNO       NUMBER(4)    NOT NULL, 
    cNO       NUMBER(4)    NOT NULL, 
    rSCORE    NUMBER(2)    NULL, 
     PRIMARY KEY (mNO, cNO)
);

CREATE SEQUENCE RATING_SEQ
START WITH 1
INCREMENT BY 1;


CREATE OR REPLACE TRIGGER RATING_AI_TRG
BEFORE INSERT ON RATING
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
    SELECT RATING_SEQ.NEXTVAL
    INTO :NEW.mNO
    FROM DUAL;
END;
/

--DROP TRIGGER RATING_AI_TRG;
/

--DROP SEQUENCE RATING_SEQ;
/

CREATE OR REPLACE TRIGGER RATING_AI_TRG
BEFORE INSERT ON RATING
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
    SELECT RATING_SEQ.NEXTVAL
    INTO :NEW.cNO
    FROM DUAL;
END;
/

--DROP TRIGGER RATING_AI_TRG;
/

--DROP SEQUENCE RATING_SEQ;
/


-- MEMBERS Table Create SQL
CREATE TABLE MEMBERS
(
    mNO       NUMBER(4)       NOT NULL,
    mID       VARCHAR2(20)    NOT NULL,
    mNAME     VARCHAR2(30)    NOT NULL,
    mPW       VARCHAR2(20)    NOT NULL,
    mPHONE    VARCHAR2(20)    NOT NULL,
    mEMAIL    VARCHAR2(50)    NULL,
     PRIMARY KEY (mNO)
);

alter table MEMBERS add (mQuestion varchar2(90) not null, mAnswer varchar2(90) not null);


CREATE SEQUENCE MEMBERS_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER MEMBERS_AI_TRG
BEFORE INSERT ON MEMBERS
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
    SELECT MEMBERS_SEQ.NEXTVAL
    INTO :NEW.mNO
    FROM DUAL;
END;
/

--DROP TRIGGER MEMBERS_AI_TRG;
/

--DROP SEQUENCE MEMBERS_SEQ;
/

-- CONTENTS Table Create SQL
CREATE TABLE CONTENTS
(
    cNO             NUMBER(4)        NOT NULL,
    cNAME           VARCHAR2(60)     NOT NULL,
    cDATE           DATE             NULL,
    cDIRECTOR       VARCHAR2(30)     NULL,
    cACTOR          VARCHAR2(90)     NULL,
    cGENRE          VARCHAR2(30)     NULL,
    cSERISE         NUMBER(2)        NULL,
    cRUNNINGTIME    VARCHAR2(20)     NULL,
    cAGELIMIT       NUMBER(2)        NULL,
    cDETAIL         VARCHAR2(300)    NULL,
    cRATINGCOUNT    NUMBER(10)       DEFAULT 0 NOT NULL,
    cACCRATING      NUMBER(10)       DEFAULT 0 NOT NULL,
     PRIMARY KEY (cNO)
)
/;

CREATE SEQUENCE CONTENTS_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER CONTENTS_AI_TRG
BEFORE INSERT ON CONTENTS
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
    SELECT CONTENTS_SEQ.NEXTVAL
    INTO :NEW.cNO
    FROM DUAL;
END;
/

--DROP TRIGGER CONTENTS_AI_TRG;
/

--DROP SEQUENCE CONTENTS_SEQ;
/

/*
ALTER TABLE RATING
    ADD CONSTRAINT FK_RATING_mNO_MEMBERS_mNO FOREIGN KEY (mNO)
        REFERENCES MEMBERS (mNO)
/

ALTER TABLE RATING
    ADD CONSTRAINT FK_RATING_cNO_CONTENTS_cNO FOREIGN KEY (cNO)
        REFERENCES CONTENTS (cNO)
/
alter table RATING drop constraint FK_RATING_CNO_CONTENTS_CNO;
alter table RATING drop constraint FK_RATING_mNO_MEMBERS_mNO;
commit;
*/


CREATE TABLE PWQUESTION
(
    pwNO          number(2)       NOT NULL,
    pwQUESTION    VARCHAR2(60)    NOT NULL,
     PRIMARY KEY (pwNO)
)
/



ALTER TABLE MEMBERS
    ADD CONSTRAINT FK_MEMBERS_pwNO_PWQUESTION_pwN FOREIGN KEY (pwNO)
        REFERENCES PWQUESTION (pwNO)
/

insert into MEMBERS (mid, mname, mpw, MEMAIL, MPHONE, pwno, pwANSWER) values ('hong', '홍길동', 'hong', 'hong@gmail.com', '010-1234-5678', 10, '일기장');
insert into MEMBERS (mid, mname, mpw, MEMAIL, MPHONE, pwno, pwANSWER) values ('lee', '이순신', 'lee', 'lee@gmail.com', '010-1234-5677', 20, '파란색');
insert into MEMBERS (mid, mname, mpw, MEMAIL, MPHONE, pwno, pwANSWER) values ('kang', '강감찬', 'kang', 'kang@gmail.com', '010-1234-5676', 30, '없음');
insert into MEMBERS (mid, mname, mpw, MEMAIL, MPHONE, pwno, pwANSWER) values ('ulji', '을지문덕', 'ulji', 'ulji@gmail.com', '010-1234-5675', 50, '0415');
insert into MEMBERS (mid, mname, mpw, MEMAIL, MPHONE, pwno, pwANSWER) values ('ee', '이이', 'ee', 'ee@gmail.com', '010-1234-5275', 60, '이순신');
insert into MEMBERS (mid, mname, mpw, MEMAIL, MPHONE, pwno, pwANSWER) values ('a', 'aa', 'aa', 'ee@gmail.com', '010-1234-5275', 60, '이순신');

insert into PWQUESTION values (10, '나의 보물 제1호는?');
insert into PWQUESTION values (20, '내가 좋아하는 색깔은?');
insert into PWQUESTION values (30, '기억에 남는 추억의 장소는?');
insert into PWQUESTION values (40, '자신의 인생 좌우명은?');
insert into PWQUESTION values (50, '추억하고 싶은 날짜가 있다면?');
insert into PWQUESTION values (60, '자신이 두 번째로 존경하는 인물은?');

insert into CONTENTS (cname, cdate, cdirector, cactor, cgenre, cserise, crunningtime, cagelimit, cdetail) values
('퍼시픽 림', '2013-01-01', '기예르모 델 토로', '찰리 헌냄, 이드리스 엘바, 키쿠치 린코', 'SF', 00, 131, 12, '2025년, 태평양 연안의 심해에 거대한 균열이 일어나고 외계괴물 카이주가 나타나 지구를 파괴하기 시작한다. 인류는 지구연합군을 결성하고 초대형 로봇 예거를 창조하여 반격을 시작한다.');

insert into CONTENTS (cname, cdate, cdirector, cactor, cgenre, cserise, crunningtime, cagelimit, cdetail) values
('관상', '2013-01-01', '한재림', '송강호, 이정재, 백윤식', '시대극', 00, 139, 15, '칩거하던 천재 관상가 내경은 연홍의 제안으로 한양을 향한다. 소문이 자자해진 그는 궁 생활을 시작하고, 수양대군이 역모를 꾸미고 있음을 알게 된다.');

insert into CONTENTS (cname, cdate, cdirector, cactor, cgenre, cserise, crunningtime, cagelimit, cdetail) values
('기생충', '2019-01-01', '봉준호', '송강호, 최우식, 박소담', '드라마', 00, 131, 15, '전원 백수로 살 길이 막막하지만 사이는 좋은 기택 가족. 장남 기우에게 명문대생 친구 민혁이 연결해 준 고액 과외는 모처럼 싹튼 고정 수입의 희망이다. 기우는 온 가족의 기대 속에 박 사장 집으로 향한다.');

insert into CONTENTS (cname, cdate, cdirector, cactor, cgenre, cserise, crunningtime, cagelimit, cdetail) values
('어메이징 스파이더맨', '2012-01-01', '마크 웹', '앤드류 가필드, 엠마 스톤, 마틴 쉰', '액션', 01, 136, 12, '부모님을 잃고 삼촌 내외와 지내는 피터. 어느 날 그는 아버지 옛 동료의 실험실에서 겪은 사고로 거미의 능력을 갖게 되고, 그의 도움으로 연구를 완성한 코너스 박사는 숨겨진 자아를 발견한다.');

commit;
--테스트
select * from MEMBERS;
select * from CONTENTS;
select * from RATING;