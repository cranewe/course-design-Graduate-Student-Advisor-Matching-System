package com.servlet;
/**
 * login检验文件
 * */

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.dao.ProfessorDAOImpl;
import com.dao.DAOFactory;
import com.dao.ProfessorDAO;
import com.entity.Professor;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String professorIdStr = request.getParameter("professorId");
        String password = request.getParameter("password");
        
        System.out.println("login: " + 
        					"\nprofessorID: " + professorIdStr + 
        					"\tpassword: " + password);
        if(professorIdStr.equals("111")) {
        	return ;
        }

        if (professorIdStr == null || professorIdStr.trim().isEmpty()) {
            request.setAttribute("errorMsg", "导师ID错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            int professorId = Integer.parseInt(professorIdStr);
            DAOFactory fc = new DAOFactory();

            try (Connection connection = fc.getConn()) {
                ProfessorDAO professorDAO = new ProfessorDAOImpl(connection);
                
                // ��Users����֤��¼
                boolean isValid = professorDAO.validateLoginFromUsers(professorId, password);

                if (isValid) {
                    HttpSession session = request.getSession();
            
                    System.out.println("personal profile jump");
                    session.setAttribute("zhanghao", professorId);
                    response.sendRedirect("personal-profile");
                } else {
                    request.setAttribute("errorMsg", "�˺Ż������������������");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "��ʦID����������");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "���ݿ����Ӵ���");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}