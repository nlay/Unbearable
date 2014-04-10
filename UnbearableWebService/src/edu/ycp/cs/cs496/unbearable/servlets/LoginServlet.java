package edu.ycp.cs.cs496.unbearable.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.unbearable.controllers.GetLogin;
import edu.ycp.cs.cs496.unbearable.model.Login;
import edu.ycp.cs.cs496.unbearable.model.json.JSON;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Login login = JSON.getObjectMapper().readValue(req.getReader(), Login.class);
		String userName = login.getName();
		String password = login.getPassword();
		
		// Use a controller to determine if this username/password corresponds to an existing user
		GetLogin controller = new GetLogin();
		boolean result = controller.getLogin(userName, password);
		
		// TODO: if the login succeeded, set information in the session to
		// record the fact that the client successfully logged in
		
		// Set status code and content type
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");
				
		// Return the item in JSON format
		JSON.getObjectMapper().writeValue(resp.getWriter(), (Boolean)result);
	}
}
