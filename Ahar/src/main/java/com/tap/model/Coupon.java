package com.tap.model;

public class Coupon {

	private int couponId;
	private String couponCode;
	private double discountPercent;
	private double minAmount;
	public java.sql.Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(java.sql.Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private boolean isActive;
	private java.sql.Date expiryDate;
	private String description;

	public Coupon() {}

	public Coupon(int couponId, String couponCode, double discountPercent, double minAmount, boolean isActive) {
		super();
		this.couponId = couponId;
		this.couponCode = couponCode;
		this.discountPercent = discountPercent;
		this.minAmount = minAmount;
		this.isActive = isActive;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



}