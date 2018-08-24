/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOMarca;
import Entidades.Marca;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jaque
 */
@WebServlet(name = "MarcaServlet", urlPatterns = {"/marca"})
public class MarcaServlet extends HttpServlet {

    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance(ptBr);

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
        String nomeMarca = "";

        try (PrintWriter out = response.getWriter()) {
            nomeMarca = request.getParameter("nomeMarca");

            String resultado = "";
            if (nomeMarca == null || nomeMarca.equals("")) {
                resultado = listaMarcasCadastrados();
            } else {
                resultado = listaMarcaNome(nomeMarca);
            }
            request.getSession().setAttribute("resultado", resultado);
            response.sendRedirect(request.getContextPath() + "/paginas/marca.jsp");
        }
    }

    protected String listaMarcaNome(String nomeMarca) {
        DAOMarca marca = new DAOMarca();
        String tabela = "";
        List<Marca> lista = marca.listByNome(nomeMarca);
        for (Marca l : lista) {
            tabela += "<tr>"
                    + "<td>" + l.getIdMarca()+ "</td>"
                    + "<td>" + l.getNome()+ "</td>"
                 
                    + "</tr>";
        }
        
        return tabela;
    }

    protected String listaMarcasCadastrados() {
        DAOMarca marca = new DAOMarca();
        String tabela = "";
        List<Marca> lista = marca.listInOrderNome();
        for (Marca l : lista) {
            tabela += "<tr>"
                   + "<td>" + l.getIdMarca()+ "</td>"
                    + "<td>" + l.getNome()+ "</td>"
                    + "</tr>";
        }

        return tabela;
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
        System.out.println("teste doget");
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
        System.out.println("teste dopost");
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
