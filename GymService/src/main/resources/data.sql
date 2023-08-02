CREATE TABLE `license`
(
    `license_seq`  int         NOT NULL AUTO_INCREMENT COMMENT '패키지 순번',
    `license_name` varchar(50) NOT NULL COMMENT '패키지 이름',
    `count`        int                  DEFAULT NULL COMMENT '이용권 수, NULL인 경우 무제한',
    `period`       int                  DEFAULT NULL COMMENT '기간(일), NULL인 경우 무제한',
    `created_at`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`  timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`license_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='패키지';

CREATE TABLE `pass_data`
(
    `pass_data_seq`        int         NOT NULL AUTO_INCREMENT COMMENT '이용권 순번',
    `license_seq`     int         NOT NULL COMMENT '패키지 순번',
    `user_info_id`         varchar(20) NOT NULL COMMENT '사용자 ID',
    `status`          varchar(10) NOT NULL COMMENT '상태',
    `remaining_count` int                  DEFAULT NULL COMMENT '잔여 이용권 수, NULL인 경우 무제한',
    `started_at`      timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`        timestamp            DEFAULT NULL COMMENT '종료 일시, NULL인 경우 무제한',
    `expired_at`      timestamp            DEFAULT NULL COMMENT '만료 일시',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`     timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`pass_data_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='이용권';

CREATE TABLE `booking`
(
    `booking_seq`  int         NOT NULL AUTO_INCREMENT COMMENT '예약 순번',
    `pass_data_seq`     int         NOT NULL COMMENT '이용권 순번',
    `user_info_id`      varchar(20) NOT NULL COMMENT '사용자 ID',
    `status`       varchar(10) NOT NULL COMMENT '상태',
    `used_pass_data`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '이용권 사용 여부',
    `attended`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '출석 여부',
    `started_at`   timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`     timestamp   NOT NULL COMMENT '종료 일시',
    `cancelled_at` timestamp            DEFAULT NULL COMMENT '취소 일시',
    `created_at`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`  timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`booking_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='예약';

CREATE TABLE `user_info`
(
    `user_info_id`     varchar(20) NOT NULL COMMENT '사용자 ID',
    `user_info_name`   varchar(50) NOT NULL COMMENT '사용자 이름',
    `status`      varchar(10) NOT NULL COMMENT '상태',
    `phone`       varchar(50)          DEFAULT NULL COMMENT '연락처',
    `meta`        TEXT                 DEFAULT NULL COMMENT '메타 정보, JSON',
    `created_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at` timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';

INSERT INTO license (license_name, count, period, created_at)
VALUES ('Starter PT 10회', 10, 60, '2022-08-01 00:00:00'),
       ('Starter PT 20회', 20, 120, '2022-08-01 00:00:00'),
       ('Starter PT 30회', 30, 180, '2022-08-01 00:00:00'),
       ('무료 이벤트 필라테스 1회', 1, NULL, '2022-08-01 00:00:00'),
       ('바디 챌린지 PT 4주', NULL, 28, '2022-08-01 00:00:00'),
       ('바디 챌린지 PT 8주', NULL, 48, '2022-08-01 00:00:00'),
       ('인바디 상담', NULL, NULL, '2022-08-01 00:00:00');

INSERT INTO user_info (user_info_id, user_info_name, status, phone, meta, created_at)
VALUES ('A1000000', '우영우', 'ACTIVE', '01011112222', NULL, '2022-08-01 00:00:00'),
       ('A1000001', '최수연', 'ACTIVE', '01033334444', NULL, '2022-08-01 00:00:00'),
       ('A1000002', '이준호', 'INACTIVE', '01055556666', NULL, '2022-08-01 00:00:00'),
       ('B1000010', '권민우', 'ACTIVE', '01077778888', NULL, '2022-08-01 00:00:00'),
       ('B1000011', '동그라미', 'INACTIVE', '01088889999', NULL, '2022-08-01 00:00:00'),
       ('B2000000', '한선영', 'ACTIVE', '01099990000', NULL, '2022-08-01 00:00:00'),
       ('B2000001', '태수미', 'ACTIVE', '01000001111', NULL, '2022-08-01 00:00:00');
