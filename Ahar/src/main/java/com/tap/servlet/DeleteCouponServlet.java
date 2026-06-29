package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.CouponDAOImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/deleteCoupon")
public class DeleteCouponServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		int couponId =
				Integer.parseInt(
						req.getParameter("couponId"));

		CouponDAOImpl dao =
				new CouponDAOImpl();

		dao.deleteCoupon(couponId);

		resp.sendRedirect("couponManagement");
	}
}