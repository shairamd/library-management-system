/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author MSII
 */
public class CreateUser extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDAO udao= new UserDAO();
        User u= udao.findUserByUsername(username);
        if(u!= null){
            request.setAttribute("message", "*Username really exit");
            request.getRequestDispatcher("CreateUser.jsp").forward(request, resp);
            return;
        }
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        if (!password.equals(passwordConfirm)) {
            request.setAttribute("message", "*Password don't match passwordConfirm");
            request.getRequestDispatcher("CreateUser.jsp").forward(request, resp);
            return;
        }
        String name = request.getParameter("name");
        boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
        String datebirth = request.getParameter("datebirth");
        String phone = request.getParameter("phone");
        String gmail = request.getParameter("gmail");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        user.setDatebirth(datebirth);
        user.setPhone(phone);
        user.setGmail(gmail);
        
        udao.insertUser(user);
        
        //validate session then login again
        resp.sendRedirect("ListUser");
     
    }
}