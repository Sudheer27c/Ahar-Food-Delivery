package com.tap.servlet;

import java.io.IOException;

import com.tap.DAOImpl.CouponDAOImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/toggleCoupon")
public class ToggleCouponServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		int couponId =
				Integer.parseInt(
						req.getParameter("couponId"));

		boolean active =
				Boolean.parseBoolean(
						req.getParameter("active"));

		CouponDAOImpl dao =
				new CouponDAOImpl();

		dao.changeStatus(
				couponId,
				!active);

		resp.sendRedirect("couponManagement");
	}
}