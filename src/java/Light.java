/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mattafire
 */
@WebServlet(urlPatterns = {"/Lights"})
public class Light extends HttpServlet {
    boolean status = false;
    Date lastChange = new Date(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");


    public String getLastChange() {
        return sdf.format(this.lastChange);
    }

    public void setLastChange(long milli) {
        this.lastChange = new Date(milli);
    }


    public String getStatus() {
        if(status)
            return "On";
        else
            return "Off";
    }

    public void setStatus(String status) {
        if(status!=null){
        if(status.equals("Off"))
            this.status = false;
        else
            this.status = true;  
        }
    }
    public void toggleStatus(){
        if(status)
            setStatus("Off");
        else
            setStatus("On");
    }
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
        String changeState = request.getParameter("changeState");
        if(changeState!=getStatus()&&changeState!=null){
            setStatus(changeState);
            setLastChange(System.currentTimeMillis());
        }
            request.setAttribute("state", getStatus());
            request.setAttribute("system", "Lights");
            request.setAttribute("lastChange", getLastChange());
            request.getRequestDispatcher("Status.jsp").forward(request, response);
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
