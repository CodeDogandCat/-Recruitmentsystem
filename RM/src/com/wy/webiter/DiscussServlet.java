package com.wy.webiter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wy.dao.DiscussDao;
import com.wy.form.DiscussForm;
import com.wy.tool.Chinese;

public class DiscussServlet extends HttpServlet {
	private int method;
	private DiscussDao disussDao = null;

	public DiscussServlet() {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("32131");
		method = Integer.parseInt(request.getParameter("method"));
		// System.out.println("32131");
		if (method == 0) {
			addDisuss(request, response);
		}
		if (method == 1) {
			deleteDisuss(request, response);
		}
		if (method == 2) {
			updateDisuss(request, response);
		}
	}

	public void updateDisuss(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		DiscussForm form = new DiscussForm();
		disussDao = new DiscussDao();
		form.setId(Integer.valueOf(request.getParameter("id")));
		form.setBmbh(Integer.valueOf(request.getParameter("ypbm")));
		form.setZwmc(Chinese.toChinese(request.getParameter("ypzw")));
		form.setZprs(Integer.valueOf(request.getParameter("zprs")));
		form.setCnyx(Chinese.toChinese(request.getParameter("cnyx")));
		form.setJtyq(Chinese.toChinese(request.getParameter("jtyq")));
		form.setKssj(Chinese.toChinese(request.getParameter("kssj")));
		form.setJzsj(Chinese.toChinese(request.getParameter("jzsj")));

		if (disussDao.operationDiscuss("�޸�", form)) {
			out.print("<script language=javascript>alert('�޸���Ƹ��Ϣ�ɹ���');window.location.href='back_DiscussSelect.jsp';</script>");
		} else {
			out.print("<script language=javascript>alert('�޸���Ƹ��Ϣʧ�ܣ�');history.go(-1);</script>");
		}
	}

	public void deleteDisuss(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		DiscussForm disussForm = new DiscussForm();
		disussDao = new DiscussDao();
		disussForm.setId(Integer.valueOf(request.getParameter("id")));
		if (disussDao.operationDiscuss("ɾ��", disussForm)) {
			out.print("<script language=javascript>alert('ɾ����Ƹ��Ϣ�ɹ���');window.location.href='back_DiscussSelect.jsp';</script>");
		} else {
			out.print("<script language=javascript>alert('ɾ����Ƹ��Ϣʧ�ܣ�');history.go(-1);</script>");
		}
	}

	public void addDisuss(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		DiscussForm form = new DiscussForm();
		disussDao = new DiscussDao();
		form.setBmbh(Integer.valueOf(request.getParameter("ypbm")));
		form.setZwmc(Chinese.toChinese(request.getParameter("ypzw")));
		form.setZprs(Integer.valueOf(request.getParameter("zprs")));
		form.setCnyx(Chinese.toChinese(request.getParameter("cnyx")));
		form.setJtyq(Chinese.toChinese(request.getParameter("jtyq")));
		form.setKssj(Chinese.toChinese(request.getParameter("kssj")));
		form.setJzsj(Chinese.toChinese(request.getParameter("jzsj")));
		String result = "�����Ƹʧ�ܣ�";
		if (disussDao.operationDiscuss("���", form)) {
			result = "�����Ƹ�ɹ���";
		}
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("back_DiscussAdd.jsp");
		requestDispatcher.forward(request, response);
	}
}
