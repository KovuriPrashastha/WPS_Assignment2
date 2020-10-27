package in.ac.vce;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthenticationServelet
 */
@WebServlet(
		description = "Performs authentication based on user inputs", 
		urlPatterns = { "/auth" }, 
		initParams = { 
				@WebInitParam(name = "x", value = "10")
		})
public class AuthenticationServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read content from user
				String username=request.getParameter("username");
				String password=request.getParameter("password");
				
				PrintWriter out=response.getWriter();
				//DB code
				MySQLBean bean=new MySQLBean();
				Boolean result= bean.authenticate(username, password);
				out.print("Mr."+username);
				//response		
				if(result) {
					RequestDispatcher rd =request.getRequestDispatcher("/inbox.html");
					rd.forward(request, response);
				}
				else {
					out.print("<b style='color:red'>Invalid Credentials:"+username);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
