package albumServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import albumBean.Album;
import albumBean.User;
import albumDao.AlbumDao;
import albumDao.UserDao;
import albumUtils.JDBCTools;

/**
 * Servlet implementation class login
 */

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserDao userDao=new UserDao();
		AlbumDao albumDao=new AlbumDao();
	    Connection connection=null;
	    
	    try {
			connection=JDBCTools.getConnection();
			String sql="select * from user where email=?";
			List<User> users=userDao.getForList(connection, sql, email);
			boolean san=false;
			int userId = 0;
			for(User u : users){
				if(u.getPassword()==password ||u.getPassword().equals(password)){
					 userId=u.getUserid();
					san=true;
				}
			}
			if(san==true){
				sql="select * from album where userid=?";
				List<Album> albums=albumDao.getForList(connection, sql,userId);
				request.setAttribute("albums", albums);
			    request.getRequestDispatcher("showphoto.jsp").forward(request,response);
			}
			else{
				PrintWriter pw=response.getWriter();
				pw.write("失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
