package com.kakaopay.coupon;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COUPON")
public class Coupon {

    @Id
    public String serialNumber;
    public Date expireDate;
    public boolean isUsed;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }


    public Coupon() {
        this.serialNumber = "";
        this.expireDate = null;
        this.isUsed = false;
    }

    public Coupon(String serialNumber, Date expireDate) {
        this.serialNumber = serialNumber;
        this.expireDate = expireDate;
        this.isUsed = false;
    }


}
