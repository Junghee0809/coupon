package com.kakaopay.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends JpaRepository<Coupon, String> {
}
