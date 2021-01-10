package com.kakaopay.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CouponUsageRepository extends JpaRepository<CouponUsage, String> {
}
