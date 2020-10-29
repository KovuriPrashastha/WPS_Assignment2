package in.ac.vce;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class thrirdpage
 */
@WebServlet(name = "thirdpage", urlPatterns = { "/thirdpage" })
public class thrirdpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public thrirdpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		CheckAuthenticationServelet obj = new CheckAuthenticationServelet();
		/*String username =  obj.getUser();
		String password = obj.getPassword();*/
			javax.servlet.http.Cookie[] c = request.getCookies();
		String username = c[0].getValue();
		String password = c[1].getValue();
		username=request.getParameter("username");
		password=request.getParameter("password");
		Cookie c1 = new Cookie("username",username);
		Cookie c2 = new Cookie("password",password);
		response.addCookie(c1);
		response.addCookie(c2);
		PrintWriter out=response.getWriter();
		MySQLDBCon obj2 = new MySQLDBCon();
		Boolean result= obj2.authenticate(username, password);		
		if(result) {
			RequestDispatcher rd =request.getRequestDispatcher("/thirdpage.html");
			rd.forward(request, response);
		}
		else {
			out.print("Login to view thid page");
			RequestDispatcher rd =request.getRequestDispatcher("/index.html");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
