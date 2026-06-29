package com.tap.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.tap.model.OTPGenerator;
import com.tap.utility.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sendOTP")
public class SendOTPServlet
extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
					throws IOException {

		String email =
				req.getParameter("email");

		String otp =
				OTPGenerator.generateOTP();

		try {

			Connection con =
					DBConnection.getConnection();

			PreparedStatement pstmt =
					con.prepareStatement(
							"INSERT INTO otpverification VALUES(?,?,?)");

			pstmt.setString(1, email);
			pstmt.setString(2, otp);

			Timestamp expiry =
					new Timestamp(
							System.currentTimeMillis()
							+ 300000);

			pstmt.setTimestamp(
					3,
					expiry);

			pstmt.executeUpdate();

			System.out.println(
					"OTP = " + otp);

			req.getSession()
			.setAttribute(
					"email",
					email);

			resp.sendRedirect(
					"verifyOTP.jsp");

		}
		catch(Exception e){

			e.printStackTrace();
		}
	}
}