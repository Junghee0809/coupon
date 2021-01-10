package com.kakaopay.coupon;

import java.util.Date;
import java.util.Random;

public class CouponFactory {

    public static Coupon generateRandomCoupon() {
        Random randGenerator = new Random();
        Coupon coupon = new Coupon();
        for (int i = 0; i < 7; i++){
            int randInt = randGenerator.nextInt(10);
            coupon.serialNumber += randInt;
        }
        int bound = 604800000;
        Date date = new Date(Math.abs(System.currentTimeMillis() + randGenerator.nextInt(bound)));
        coupon.expireDate = date;

        return coupon;
    }
}
