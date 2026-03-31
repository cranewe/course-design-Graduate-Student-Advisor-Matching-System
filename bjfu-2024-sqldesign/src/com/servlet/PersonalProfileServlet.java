package com.servlet;
/**
 * 展示老师个人信息
 * */

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.dao.ProfessorDAOImpl;
import com.dao.ProfessorDAO;
import com.dao.DAOFactory;
import com.entity.Professor;
import com.entity.ProfessorSubject;
import java.util.List;

@WebServlet("/login")
public class PersonalProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	System.out.println("doGet");
        HttpSession session = request.getSession();
        Integer zhanghao = (Integer) session.getAttribute("zhanghao");

        if (zhanghao == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        DAOFactory fc = new DAOFactory();

        try (Connection connection = fc.getConn()) {
            ProfessorDAO professorDAO = new ProfessorDAOImpl(connection);
            
            List<Integer> professorIds = professorDAO.getProfessorIdsByZhanghao(zhanghao);
            
            if (professorIds.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }

            int professorId = professorIds.get(0);

            Professor professor = professorDAO.getProfessorById(professorId);
            List<ProfessorSubject> subjects = professorDAO.getProfessorSubjects(professorId);

            request.setAttribute("professor", professor);
            request.setAttribute("subjects", subjects);
            request.getRequestDispatcher("personal-profile.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer zhanghao = (Integer) session.getAttribute("zhanghao");
        if (zhanghao == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String photoPath = request.getParameter("photoPath");
        String biography = request.getParameter("biography");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        DAOFactory fc = new DAOFactory();
        try (Connection connection = fc.getConn()) {
            ProfessorDAO professorDAO = new ProfessorDAOImpl(connection);
            
            List<Integer> professorIds = professorDAO.getProfessorIdsByZhanghao(zhanghao);
            
            if (professorIds.isEmpty()) {
                response.sendRedirect("error.jsp");			// where is this file
                return;
            }

            boolean allUpdated = true;
            for (Integer professorId : professorIds) {
                Professor professor = new Professor();
                professor.setProfessorId(professorId);
                professor.setPhotoPath(photoPath);
                professor.setBiography(biography);
                professor.setEmail(email);
                professor.setPhone(phone);

                boolean updated = professorDAO.updateProfessorInfo(professor);
                if (!updated) {
                    allUpdated = false;
                }
            }

            if (allUpdated) {
                response.sendRedirect("personal-profile?success=true");
            } else {
                response.sendRedirect("personal-profile?success=false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}