package org.shoban.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shoban.servlet.helper.DBHelper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		description = "Login Servlet", 
		urlPatterns = { "/LoginServlet.do" }, 
		initParams = { 
				@WebInitParam(name = "user", value = "shoban"), 
				@WebInitParam(name = "password", value = "training")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void init() throws ServletException {
		//we can create DB connection resource here and set it to Servlet context
	ServletContext context = getServletContext();
		String loginName = context.getInitParameter("loginValue");
		String pwd = context.getInitParameter("pwd");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String filePath ="C:\\shoban\\myproject\\training\\servlet\\src\\main\\resources\\mysql-sample-properties.xml";
		DBHelper helper = new DBHelper(filePath);
		try {
			boolean isUserExist = helper.isUserValid(user,pwd);
			//get servlet config init params
			ServletContext context = getServletContext();
			String loginName = context.getInitParameter("loginName");
			String passowrd = context.getInitParameter("pwd");

			//String userID = getServletConfig().getInitParameter("user");
			//String password = getServletConfig().getInitParameter("password");
			//logging example
			log("User="+user+"::password="+pwd);
			
			//if(loginName.equals(user) && passowrd.equals(pwd)){
			if(isUserExist){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/LoginSuccess.jsp");
				rd.forward(request, response);
				//response.sendRedirect("pages/LoginSuccess.jsp");
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
				PrintWriter out= response.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
