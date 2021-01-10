DROP TABLE COUPON;
CREATE TABLE COUPON
(
    serialNumber varchar(255) not null,
    expireDate timestamp not null,
    isUsed varchar(1),
    primary key(serialNumber)
);
DROP TABLE COUPON_USER;
CREATE TABLE COUPON_USER
(
    userId varchar(255) not null,
    primary key(userId)
);
DROP TABLE COUPON_USAGE;
CREATE TABLE COUPON_USAGE
(
    userId varchar(255) not null,
    serialNumber varchar(255) not null
);