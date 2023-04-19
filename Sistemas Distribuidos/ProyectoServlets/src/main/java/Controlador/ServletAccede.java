package Controlador;

import Modelo.GestorViajes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletAccede", value = "/ServletAccede")
public class ServletAccede extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext contexto = getServletContext();
        GestorViajes gestor = (GestorViajes) contexto.getAttribute("gestor");
        if(gestor == null) {
            gestor = new GestorViajes();
            contexto.setAttribute("gestor", gestor);
        }
        HttpSession session = request.getSession(true);
        String codCli = request.getParameter("codCli");
        session.setAttribute("codCli", codCli);
        RequestDispatcher vista = request.getRequestDispatcher("menu.jsp");
        vista.forward(request,response);
    }

}
