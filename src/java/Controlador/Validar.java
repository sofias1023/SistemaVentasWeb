/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class Validar extends HttpServlet {
    EmpleadoDAO edao=new EmpleadoDAO();
    Empleado em=new Empleado();
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
//private String asegurarClave(String textoclaro){
//        String clavesha="";
//        try{
//            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
//            sha256.update(textoclaro.getBytes());
//            clavesha=Base64.getEncoder().encodeToString(sha256.digest());        
//        }catch(NoSuchAlgorithmException e){
//            System.out.println("ERROR EN LA ENCRIPTACION: "+e.getMessage());
//        }
//        return clavesha;
//    }
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
        String accion=request.getParameter("accion");
        if(accion.equalsIgnoreCase("Ingresar")){
            String user=request.getParameter("txtuser");
            String pass=request.getParameter("txtpass");
            em=edao.validar(user, pass);
            if(em.getUser()!=null){
                request.setAttribute("Usuario", em);
               request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
            }else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
    }
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
//  protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String accion = request.getParameter("accion");
//        if (accion.equalsIgnoreCase("Ingresar")) {
//            String user = request.getParameter("txtuser");
//            String pass = request.getParameter("txtpass");
//            request.setAttribute("clave_normal", pass);
//            String ClaveEncriptada = asegurarClave(pass);
//
//            System.out.println("CLAVE ENCRIPTADA: " + ClaveEncriptada);
//
//            em = edao.validar(user, ClaveEncriptada);
//            if (em.getUser() != null) {
//                request.setAttribute("Usuario", em);
//                HttpSession session = request.getSession(true);
//                session.setAttribute("Usuario", em);
//                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response); // Redirección después de la autenticación
//            } else {
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            }
//
//        } else {
//            HttpSession session = request.getSession(false);
//            session.invalidate();
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
//    }