package com.kakaopay.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@SpringBootApplication
@RestController
public class CouponApplication {

	private CouponRepository couponRepository;
	private CouponUserRepository couponUserRepository;
	private CouponUsageRepository couponUsageRepository;

	@Autowired
	public CouponApplication(CouponRepository couponRepository,
							 CouponUserRepository couponUserRepository,
							 CouponUsageRepository couponUsageRepository
	) {
		this.couponRepository = couponRepository;
		this.couponUserRepository = couponUserRepository;
		this.couponUsageRepository = couponUsageRepository;
	}


	public static void main(String[] args) {

		SpringApplication.run(CouponApplication.class, args);
	}

	@PostMapping("/generateCoupon")
	public String generateCoupon(@RequestBody GenerateCouponParam param) {
		int numOfCoupon = param.getNumOfCoupon();
		for (int i = 0; i < numOfCoupon; i++) {
			Coupon newCoupon = CouponFactory.generateRandomCoupon();
			couponRepository.save(newCoupon);
		}

		return "Success";
	}

	@GetMapping("/getCoupon")
	public String getCoupon(@RequestParam(value="userId") String userId) {
		ArrayList<Coupon> randomCoupon = (ArrayList<Coupon>) couponRepository.findAll();

		for (Coupon coupon : randomCoupon) {
			if (!coupon.isUsed()) {
				coupon.setUsed(true);
				couponRepository.save(coupon);
				couponUsageRepository.save(new CouponUsage(userId, coupon.serialNumber));
				return coupon.getSerialNumber();
			}
		}
		return "";
	}

	@GetMapping("/getSummitedCoupon")
	public String[] getSummitedCoupon(@RequestParam(value="userId") String userId){
		ArrayList<CouponUsage> allCouponUsage = (ArrayList<CouponUsage>) couponUsageRepository.findAll();
		ArrayList<String> returnArray = new ArrayList<String>();
		for (CouponUsage couponUsage : allCouponUsage) {
			if (couponUsage.getUserId() == userId) {
				returnArray.add(couponUsage.getSerialNumber());
			}
		}
		return (String[]) returnArray.toArray();
	}

	@PostMapping("/useCoupon")
	public String useCoupon(@RequestBody String serialNumer) {
		Optional<Coupon> targetCoupon = couponRepository.findById(serialNumer);
		if(targetCoupon.isPresent()) {
			Coupon targetCouponObj = targetCoupon.get();
			targetCouponObj.setUsed(true);
			couponRepository.save(targetCouponObj);
			return "Success";
		} else {
			return "Failed";
		}
	}

	@PostMapping("/cancelCoupon")
	public String cancelCoupon(@RequestBody String serialNumer){
		Optional<Coupon> targetCoupon = couponRepository.findById(serialNumer);
		if(targetCoupon.isPresent()) {
			Coupon targetCouponObj = targetCoupon.get();
			targetCouponObj.setUsed(false);
			couponRepository.save(targetCouponObj);
			return "Success";
		} else {
			return "Failed";
		}
	}

	@GetMapping("/getExpiredCoupon")
	public String[] getExpiredCoupon(){
		ArrayList<Coupon> allCoupons = (ArrayList<Coupon>) couponRepository.findAll();
		Date currentDate = new Date();
		ArrayList<String> returnCoupons = new ArrayList<String>();
		for(Coupon coupon : allCoupons) {
			Date couponDate = coupon.getExpireDate();
			if(currentDate.compareTo(couponDate) > 0) {
				returnCoupons.add(coupon.getSerialNumber());
			}
		}
		return (String[]) returnCoupons.toArray();
	}
}
