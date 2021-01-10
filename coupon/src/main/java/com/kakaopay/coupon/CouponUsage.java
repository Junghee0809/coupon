package com.kakaopay.coupon;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUPON_USAGE")
public class CouponUsage {
    @Id
    public String userId;
    public String serialNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public CouponUsage(String userId, String serialNumber) {
        this.userId = userId;
        this.serialNumber = serialNumber;
    }
}
