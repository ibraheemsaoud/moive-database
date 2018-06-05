package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.VRApplication;
import datatypes.AddressData;

/**
 * Contains GUI for staffmember
 * 
 * @author swe.uni-due.de
 *
 */
public class StaffmemberGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet contains the insertOffer form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// set pagetitle und navtype
		request.setAttribute("navtype", "staffmember");
		request.setAttribute("pagetitle", "Insert Offer");

		// Dispatch request to template engine
		try {
			request.getRequestDispatcher("/templates/defaultWebpageS.ftl").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Contains handling of insertOffer call
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("navtype", "staffmember");

		// Check wether the call is insertOffer or not
		if (request.getParameter("page").equals("insertOffer")) {

			// Append parameter of request
			String startTime = (String) request.getParameter("startTime");
			String endTime = (String) request.getParameter("endTime");
			String street = (String) request.getParameter("street");
			String town = (String) request.getParameter("town");
			String capacity = (String) request.getParameter("capacity");
			String fee = (String) request.getParameter("fee");

			// Call application to insert offer
			new VRApplication().insertOffer(startTime, endTime, new AddressData(street, town), capacity, fee);

			// Dispatch message to template engine
			try {
				request.setAttribute("pagetitle", "Insert Offer");
				request.setAttribute("message", "New offer successful stored in the database.");
				request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			// Call doGet if request is not equal to insertOffer
		} else
			doGet(request, response);

	}
}