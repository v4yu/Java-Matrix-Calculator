/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.milosz.cebula.matrixcalculator2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.milosz.cebula.matrixcalculator2.enumoperation.EnumOperation;
import pl.polsl.milosz.cebula.matrixcalculator2.model.Model;
import pl.polsl.milosz.cebula.matrixcalculator2.model.Jdbc;

/**
 * Class implements History Page servlet
 * @author Miłosz Cebula
 * @version 1.0
 */

@WebServlet(name = "HistoryPage", urlPatterns = {"/HistoryPage"})
public class HistoryPage extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            Map<String,Double> historyMap = new HashMap<>();
            HttpSession session = request.getSession();            
            
            historyMap = (HashMap<String,Double>)session.getAttribute("history");
            
            if(historyMap==null){
                historyMap = new HashMap<>();
            }
            
            Cookie[] cookies = request.getCookies();
            Integer numberOfVisits = 0;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("numberOfVisits")) {
                        numberOfVisits = Integer.valueOf(cookie.getValue());
                        break;
                    }
                }
            }
           
            numberOfVisits++;
            Cookie cookie = new Cookie("numberOfVisits", (numberOfVisits).toString());
            response.addCookie(cookie);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>History of Operations</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<p>You have visited this site "+numberOfVisits.toString()+" times");
            out.println("<table style=\"border: 1px solid black; border-collapse: collapse; width: 300px;\">");
            
            for(HashMap.Entry<String,Double> entry : historyMap.entrySet()){
                out.println("<tr style=\"border: 1px solid black; border-collapse: collapse; width: 300px;\">");
                
                out.println("<td style=\"border: 1px solid black; border-collapse: collapse; width: 300px;\">");
                out.println(entry.getKey());
                out.println("</td>");
                
                out.println("<td style=\"border: 1px solid black; border-collapse: collapse; width: 300px;\">");
                out.println(entry.getValue());
                out.println("</td>");
                
                out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            
            session.setAttribute("history", historyMap);
            out.close();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}