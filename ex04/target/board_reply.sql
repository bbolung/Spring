-- 댓글
create table tbl_reply(
    rno number(10,0),
    bno number(10,0) not null,
    reply varchar2(1000) not null,
    replyer varchar2(50) not null,
    replyDate date default sysdate,
    updateDate date default sysdate
);

create SEQUENCE seq_reply;

-- rno 기본키 설정(이름 : pk_reply)
alter table tbl_reply
add CONSTRAINT pk_reply primary key(rno);

-- bno : 외래키 설정(이름 : fk_reply_board)
alter table tbl_reply
add CONSTRAINT fk_reply_board FOREIGN key(bno) REFERENCES tbl_board(bno);

