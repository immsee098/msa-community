CREATE TABLE IF NOT EXISTS community
(
    `communityId`      int          NOT NULL AUTO_INCREMENT COMMENT '커뮤니티 아이디',
    `communityTitle`   varchar(255) NOT NULL COMMENT '커뮤니티 제목',
    `communityContent` TEXT         NOT NULL COMMENT '커뮤니티 내용',
    `communityShowYN`  int      DEFAULT '0' COMMENT '커뮤니티 노출 여부',
    `memberId`         varchar(100) NOT NULL COMMENT '작성자',
    `insertDt`         datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `updateDt`         datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`communityId`)
);

-- INSERT INTO community()
-- VALUES (  );


