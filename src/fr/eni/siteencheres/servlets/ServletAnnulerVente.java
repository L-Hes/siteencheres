package fr.eni.siteencheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.siteencheres.bll.ArticleManager;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/annulervente")
public class ServletAnnulerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int no_article = Integer.parseInt(request.getParameter("no_article"));
		System.out.println("Servlet annuler vente n : " + no_article);
		ArticleManager articleManager = ArticleManager.getInstance();
		try {
			articleManager.supprimerArticle(no_article);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getServletContext().getRequestDispatcher("/encheres").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
