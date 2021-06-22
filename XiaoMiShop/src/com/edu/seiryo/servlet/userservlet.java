package com.edu.seiryo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seiryo.service.imp.UserServiceImp;

import com.edu.seiryo.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class userservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("mothod");
		if ("checkUsername".equals(method)) {
			checkUsername(request, response);
		} else if ("register".equals(method)) {
			register(request, response);
		} else if ("login".equals(method)) {
			login(request, response);
		} else if ("code".equals(method)) {
			code(request, response);
		}
			
	}
	//用户注册 检测用户名是否重复
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String msg = "";
		String username = request.getParameter("username");
		boolean bo = userService.checkUsername(username);
		if (bo) {
			msg = "1";
		} else {
			msg = "0";
		}
		pw.print(msg);
		pw.flush();
		pw.close();
			
	}
	//用户注册
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
			
	}
	//用户登录
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	}
	//用户登录 检测验证码
	protected void code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vcode = request.getParameter("vcode");
	}
}
