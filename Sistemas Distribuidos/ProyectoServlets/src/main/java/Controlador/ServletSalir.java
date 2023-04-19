package Controlador;

import Modelo.GestorViajes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletSalir", value = "/ServletSalir")
public class ServletSalir extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = getServletContext();
        GestorViajes gestor = (GestorViajes) context.getAttribute("gestor");

        HttpSession session = request.getSession(false);
        gestor.guardaDatos();
        session.removeAttribute("codCli");
        session.invalidate();
        RequestDispatcher vista = request.getRequestDispatcher("resSalir.jsp");
        vista.forward(request, response);
    }
}
