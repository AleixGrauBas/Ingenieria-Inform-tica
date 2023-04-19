package Controlador;

import Modelo.GestorViajes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet(name = "ServletReservaViaje", value = "/ServletReservaViaje")
public class ServletReservaViaje extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext contexto = getServletContext();
        GestorViajes gestor = (GestorViajes) contexto.getAttribute("gestor");
        HttpSession session = request.getSession(false);
        if (session.getAttribute("codCli") == null) {
            RequestDispatcher menu = request.getRequestDispatcher("reInicioSesion.jsp");
            menu.forward(request, response);
            return;
        }
        String codViaje = request.getParameter("codViaje");
        String codCli = (String) session.getAttribute("codCli");
        JSONObject res = gestor.reservaViaje(codViaje,codCli);
        request.setAttribute("res",res);
        RequestDispatcher vista = request.getRequestDispatcher("resReserva.jsp");
        vista.forward(request, response);
    }

}
