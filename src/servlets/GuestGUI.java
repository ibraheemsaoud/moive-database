package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.VRApplication;
import datatypes.GuestData;
import dbadapter.HolidayOffer;

/**
 * Class responsible for the GUI of the guest
 * 
 * @author swe.uni-due.de
 *
 */
public class GuestGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet is responsible for search form and booking form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Set navtype
		request.setAttribute("navtype", "guest");

		// Catch error if there is no page contained in the request
		String selectedPage = (request.getParameter("page") == null) ? "" : request.getParameter("page");

		// Case: Request booking form
		if (selectedPage.equals("selectHolidayOffer")) {
			// Set request attributes
			request.setAttribute("pagetitle", "Book Offer");
			request.setAttribute("hid", request.getParameter("hid"));

			// Dispatch request to template engine
			try {
				request.getRequestDispatcher("/templates/showBookHolidayOfferForm.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Otherwise show search form
		} else {

			// Set request attributes
			request.setAttribute("pagetitle", "Search Offers");

			// Dispatch request to template engine
			try {
				request.getRequestDispatcher("/templates/defaultWebpageG.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * doPost manages handling of submitted forms.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		// Set attribute for navigation type.
		request.setAttribute("navtype", "guest");

		// Generate and show results of a search
		if (request.getParameter("page").equals("browseAvailableHolidayOffers")) {
			request.setAttribute("pagetitle", "Search results");
			List<HolidayOffer> availableOffers = null;

			// Call application to request the results
			try {
				availableOffers = VRApplication.getInstance().getAvailableHolidayOffers(
						request.getParameter("arrivalTime"), request.getParameter("departureTime"),
						request.getParameter("persons"));

				// Dispatch results to template engine
				request.setAttribute("availableOffers", availableOffers);
				request.getRequestDispatcher("/templates/offersRepresentation.ftl").forward(request, response);
			} catch (Exception e1) {
				try {
					request.setAttribute("errormessage", "Database error: please contact the administator");
					request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("errormessage", "System error: please contact the administrator");
					e.printStackTrace();
				}
				e1.printStackTrace();
			}
			// Insert booking into database
		} else if (request.getParameter("page").equals("bookHolidayOffer")) {
			// Decide wether booking was successfull or not
			if (VRApplication.getInstance().makeBooking(request.getParameter("arrivalTime"),
					request.getParameter("departureTime"), request.getParameter("hid"),
					new GuestData(request.getParameter("name"), request.getParameter("email")),
					request.getParameter("persons")) != null) {

				// Set request attributes
				request.setAttribute("pagetitle", "Booking Successful");
				request.setAttribute("message",
						"Booking successful created. You will receive a confirmation mail shortly");

				// Dispatch to template engine
				try {
					request.getRequestDispatcher("/templates/showOkRepresentation.ftl").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

				// Catch booking error and print an error on the gui
			} else {
				request.setAttribute("pagetitle", "Booking failed");
				request.setAttribute("message",
						"Booking failed. The selected offer is no longer available for your selected parameters.");

				try {
					request.getRequestDispatcher("/templates/showFailInfoRepresentation.ftl").forward(request,
							response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

			}
			// If there is no page request, call doGet to show standard gui for
			// guest
		} else
			doGet(request, response);
	}
}