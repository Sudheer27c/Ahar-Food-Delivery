package com.tap.DAO;

import java.util.List;

import com.tap.model.Coupon;

public interface CouponDAO {

	Coupon getCouponByCode(
			String couponCode);

	List<Coupon> getAllCoupons();

	void addCoupon(
			Coupon coupon);

	void updateCoupon(
			Coupon coupon);

	void deleteCoupon(
			int couponId);
}
