package com.wy.webiter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wy.dao.ArticleDao;
import com.wy.dao.ArticleTypeDao;
import com.wy.form.ArticleForm;
import com.wy.form.ArticleTypeForm;
import com.wy.tool.Chinese;

public class ArticleServlet extends HttpServlet {
	private ArticleDao articleDao = null;

	private ArticleTypeDao articleTypeDao = null;

	public ArticleServlet() {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int method = Integer.parseInt(request.getParameter("method"));
		if (method == 0) {
			addArticleType(request, response);
		}
		if (method == 1) {
			deleteArticleType(request, response);
		}
		if (method == 2) {
			addArticle(request, response);
		}
		if (method == 3) {
			deleteArticle(request, response);
		}
		if (method == 4) {
			updateArticle(request, response);
		}

	}

	public void updateArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleForm articleForm = new ArticleForm();
		articleForm.setId(Integer.valueOf(request.getParameter("id")));
		articleForm.setZpbh(Integer.valueOf(request.getParameter("zpbh")));
		articleForm.setGslx(Integer.valueOf(request.getParameter("gslx")));
		articleForm.setKssj(request.getParameter("kssj"));
		articleForm.setKsdd(request.getParameter("ksdd"));
		articleForm.setXxxx(request.getParameter("xxxx"));
		articleDao = new ArticleDao();
		if (articleDao.operationArticle("�޸�", articleForm)) {
			out.print("<script language=javascript>alert('�޸Ĺ�ʾ�ɹ��������²�ѯ��');window.location.href='back_ArticleSelect.jsp';</script>");
		} else {
			out.print("<script language=javascript>alert('�޸Ĺ�ʾʧ�ܣ�');history.go(-1);</script>");
		}
	}

	public void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleForm articleForm = new ArticleForm();
		articleForm.setId(Integer.valueOf(request.getParameter("id")));
		articleDao = new ArticleDao();
		if (articleDao.operationArticle("ɾ��", articleForm)) {
			out.print("<script language=javascript>alert('ɾ����ʾ�ɹ��������²�ѯ��');window.location.href='back_ArticleSelect.jsp';</script>");
		} else {
			out.print("<script language=javascript>alert('ɾ����ʾʧ�ܣ�');history.go(-1);</script>");
		}
	}

	public void addArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		ArticleForm articleForm = new ArticleForm();
		articleDao = new ArticleDao();
		// articleForm.setZpbh(Integer.valueOf(request.getParameter("zpbh")));
		// ͨ�����ź�ְλ����---������Ƹ���
		int bmbh = Integer.valueOf(request.getParameter("ypbm"));
		String zwmc = Chinese.toChinese(request.getParameter("ypzw"));
		System.out.println("��ʾ�Ĳ��ű��----" + bmbh);
		System.out.println("��ʾ��ְλ����----" + zwmc);
		int tmpZpbh = articleDao.getZpbh(bmbh, zwmc);
		articleForm.setZpbh(tmpZpbh);

		String riqi = Chinese.toChinese(request.getParameter("kssj"));// ����
		int shi = Integer.valueOf(request.getParameter("shi"));// ʱ
		String shiString = "";
		int fen = Integer.valueOf(request.getParameter("fen"));// ��
		String fenString = "";
		System.out.println("riqi��---" + riqi);
		System.out.println("shi��---" + shi);
		System.out.println("fen��---" + fen);

		if (shi < 10) {
			shiString = "0" + shi;
		} else {
			shiString = String.valueOf(shi);
		}
		if (fen < 10) {
			fenString = "0" + fen;
		} else {
			fenString = String.valueOf(fen);
		}
		String time = riqi + " " + shiString + ":" + fenString;
		System.out.println("-----------------" + time);
		articleForm.setKssj(time);

		articleForm.setGslx(Integer.valueOf(request.getParameter("gslx")));
		articleForm.setKsdd(Chinese.toChinese(request.getParameter("ksdd")));
		articleForm.setXxxx(Chinese.toChinese(request.getParameter("xxxx")));
		articleDao = new ArticleDao();
		String result = "��ʾ���ʧ�ܣ�";
		if (articleDao.operationArticle("���", articleForm)) {
			result = "��ʾ��ӳɹ���";
		}
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("back_ArticleAdd.jsp");
		requestDispatcher.forward(request, response);
	}

	public void deleteArticleType(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleTypeForm ArticleTypeForm = new ArticleTypeForm();
		ArticleTypeForm.setId(Integer.valueOf(request.getParameter("id")));
		articleTypeDao = new ArticleTypeDao();
		if (articleTypeDao.operationArticleType("ɾ��", ArticleTypeForm)) {
			out.print("<script language=javascript>alert('ɾ����ʾ���ɹ��������²�ѯ��');window.location.href='back_ArticleTypeSelect.jsp';</script>");
		} else {
			out.print("<script language=javascript>alert('����Ҫ��������ڵĹ�ʾɾ��,�ſ�ɾ�������');history.go(-1);</script>");
		}

	}

	public void addArticleType(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		ArticleTypeForm ArticleTypeForm = new ArticleTypeForm();
		ArticleTypeForm.setId(Integer.parseInt(Chinese.toChinese(request.getParameter("typeId"))));
		ArticleTypeForm.setTypeName(Chinese.toChinese(request.getParameter("typeName")));
		articleTypeDao = new ArticleTypeDao();
		if (articleTypeDao.operationArticleType("���", ArticleTypeForm)) {
			out.print("<script language=javascript>alert('��ӹ�ʾ���ɹ��������²�ѯ��');window.location.href='back_ArticleTypeSelect.jsp';</script>");
		} else {
			out.print("<script language=javascript>alert('��ӹ�ʾ���ʧ�ܣ�');history.go(-1);</script>");
		}

	}
}
