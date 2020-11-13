package servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sysDAO;
import model.ThBean;

/**
 * Servlet implementation class userMainDisp
 */
@WebServlet("/userMainDisp")
public class userMainDisp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session=request.getSession();
		sysDAO sysDAO=new sysDAO();
		List<ThBean> thList =sysDAO.thRead();
		session.setAttribute("thList",thList);
		RequestDispatcher dispatcher=
				request.getRequestDispatcher("/WEB-INF/jsp/thMgt.jsp");
		dispatcher.forward(request, response);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String btn_1=request.getParameter("dlt");
		String btn_2=request.getParameter("upd");
		String btn_3=request.getParameter("II");
		if(btn_1 !=null) {
			HttpSession session=request.getSession();
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
			dispatcher.forward(request, response);
		}else if( btn_3 !=null) {
			String thName=request.getParameter("thName");
			String mail=request.getParameter("mail");


			System.out.println(">>999"  );
			sysDAO sysDAO=new sysDAO();
			sysDAO.thupd(thName,btn_3,mail);
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("/WEB-INF/jsp/comp.jsp");
			dispatcher.forward(request, response);

		}else if(btn_2 !=null) {
			HttpSession session=request.getSession();
			System.out.println(">>" + btn_2);
			sysDAO sysDAO=new sysDAO();
			ThBean thd =sysDAO.thdate(btn_2);
			session.setAttribute("thid", thd);
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("/WEB-INF/jsp/thUpdate.jsp");
			dispatcher.forward(request, response);

		}

	}

}
