package Controlador;

import Modelo.GestorViajes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet(name = "ServletAnulaReserva", value = "/ServletAnulaReserva")
public class ServletAnulaReserva extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext contexto = getServletContext();
        GestorViajes gestor = (GestorViajes) contexto.getAttribute("gestor");
        HttpSession session = request.getSession(false);
        if (session.getAttribute("codCli") == null) {
            RequestDispatcher menu = request.getRequestDispatcher("reInicioSesion.jsp");
            menu.forward(request, response);
            return;
        }
        String codCli = session.getAttribute("codCli").toString();
        String codViaje = request.getParameter("codViaje");
        JSONObject res = gestor.anulaReserva(codViaje, codCli);
        request.setAttribute("res", res);
        RequestDispatcher vista = request.getRequestDispatcher("anulaReserva.jsp");
        vista.forward(request, response);
    }
}
