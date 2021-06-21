package com.edu.seiryo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seiryo.entity.GoodType;
import com.edu.seiryo.service.GoodTypeService;
import com.edu.seiryo.service.imp.GoodTypeServiceImp;
import com.google.gson.Gson;

/**
 * Servlet implementation class GoodTypeServlet
 */
public class GoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GoodTypeService goodTypeService = new GoodTypeServiceImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * 获取商品类型列表
     */
    protected void goodTypeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	List<GoodType> goodTypes = goodTypeService.getGoodTypeList();

    	Gson gson = new Gson();
    	String jsonGoodTypes = gson.toJson(goodTypes);
    	
    	out.print(jsonGoodTypes);
    	out.flush();
    	out.close();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		System.out.println(method);
		if ("goodTypeList".equals(method)) {
			goodTypeList(request, response);
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
