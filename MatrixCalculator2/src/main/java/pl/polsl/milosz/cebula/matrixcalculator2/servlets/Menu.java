/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.milosz.cebula.matrixcalculator2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.milosz.cebula.matrixcalculator2.model.Model;
import pl.polsl.milosz.cebula.matrixcalculator2.enumoperation.EnumOperation;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import pl.polsl.milosz.cebula.matrixcalculator2.exception.CalculatorException;

/**
 * Class implements main page servlet
 * @author Miłosz Cebula
 * @version 1.0
 */
@WebServlet(name = "Menu", urlPatterns = {"/Menu"})
public class Menu extends HttpServlet {
    
    private Model matrixModel;

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
            matrixModel = new Model();
               
            HttpSession session = request.getSession();
            
            String matrixInputText = request.getParameter("matrixInput");
            Integer matrixColumns = Integer.valueOf(request.getParameter("matrixcolumns"));
            Integer matrixRows = Integer.valueOf(request.getParameter("matrixrows"));
            String mode = request.getParameter("mode");
            
            EnumOperation choice = EnumOperation.valueOf(mode);
            List<List<Double>> parsedMatrixInput = new ArrayList<List<Double>>();
            try{
                parsedMatrixInput = parseMatrix(matrixInputText,matrixColumns,matrixRows);
            }catch(Exception ex){
                response.sendError(400);
                return;
            }
            


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Menu</title>");            
            out.println("</head>");
            out.println("<body>");
            
            switch(choice){
            case TRANSPOSITION:
                try{
                   matrixModel.matrixSizeCheck(matrixRows,matrixColumns); 
                }catch(CalculatorException ex){
                    response.sendError(400);
                    return;
                }
                
                List<List<Double>> resultMatrix = matrixModel.matrixTransposition(parsedMatrixInput);

                session.setAttribute("transposition_results",resultMatrix );
                getServletContext().getRequestDispatcher("/MatrixDisplay").forward(request, response);
                out.println("after forward");
                break;

            case DETERMINANT:
                try{
                   matrixModel.matrixSizeCheck(matrixRows,matrixColumns); 
                   matrixModel.matrixSquareCheck(matrixRows,matrixColumns); 
                }catch(CalculatorException ex){
                    response.sendError(400);
                    return;
                }
                
                Double resultDeterminant = matrixModel.matrixDeterminant(parsedMatrixInput);
                out.println("Determinant: "+resultDeterminant.toString());
                
                Map<String,Double> historyMap = (HashMap<String,Double>)session.getAttribute("history");
                
                if(historyMap==null){
                    historyMap = new HashMap<>();
                }
                
                historyMap.put(new java.util.Date().toString(), resultDeterminant);
                session.setAttribute("history", historyMap);
                
                break;
            default: 
                out.println("not supported mode");
            }
        
            out.println("</body>");
            out.println("</html>");
        }
        

    }
    /**
     * Methods parses matrix from string
     * @param inputMatrix string containing matrix that should be parsed
     * @param col amount of columns in matrix
     * @param row amount of rows in matrix
     * @throws CalculatorException if there is error during matrix parsing
     * @return 2D list of doubles representing parsed matrix
     */
    public List<List<Double>> parseMatrix(String inputMatrix, Integer col, Integer row) throws CalculatorException{
        List<List<Double>> resultMatrix = new ArrayList<>();
        
        String[] rows;

        rows = inputMatrix.split( "\n" );

        for(int i=0;i<row;i++){
            String temp = rows[i];
                List<Double> inputRow = Arrays.stream(temp.split(" "))
                    .map(Double::parseDouble)
                    .collect(Collectors.toList());
            
            if(inputRow.size()!= col){
                throw new CalculatorException("invalid matrix");
            }
            resultMatrix.add(inputRow);
            }
            return resultMatrix;
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
