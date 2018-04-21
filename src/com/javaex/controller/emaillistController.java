package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmaillistDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.EmailVO;

@WebServlet("/el") // localhost:8088/emaillist/el
public class emaillistController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String actionName = request.getParameter("a");
		System.out.println(actionName);
		if ("form".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/form.jsp");
			
			/*// form
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/form.jsp");
			rd.forward(request, response);*/
			
			
		} else if ("insert".equals(actionName)) {
			// insert
			String lastName = request.getParameter("ln");
			String firstName = request.getParameter("fn");
			String email = request.getParameter("email");
			EmailVO vo = new EmailVO(lastName, firstName, email);
			EmaillistDao dao = new EmaillistDao();
			dao.insert(vo);
			// 리스트 리다이렉트
			WebUtil.redirect(request, response, "/emaillist2/el");
			
//			response.sendRedirect("/emaillist2/el"); //사용자가 주소를 작성하는 것과 같아서 url형태로 들억야함

		} else {
			// list
			EmaillistDao dao = new EmaillistDao();
			List<EmailVO> list = dao.getList();

			request.setAttribute("list", list);
			
			//WebUtil.forward(request, response, "/WEB-INF/list.jsp");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
