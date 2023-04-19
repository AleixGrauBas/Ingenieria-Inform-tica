package Controlador;

import Modelo.GestorViajes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONArray;

import java.io.IOException;

@WebServlet(name = "ServletconsultaViajes", value = "/ServletconsultaViajes")
public class ServletconsultaViajes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session.getAttribute("codCli") == null) {
            RequestDispatcher menu = request.getRequestDispatcher("reInicioSesion.jsp");
            menu.forward(request, response);
            return;
        }
        ServletContext contexto = getServletContext();
        GestorViajes gestor = (GestorViajes) contexto.getAttribute("gestor");
        String origen = request.getParameter("origen");
        JSONArray res = gestor.consultaViajes(origen);
        request.setAttribute("res", res);
        RequestDispatcher vista = request.getRequestDispatcher("resConsulta.jsp");
        vista.forward(request,response);
    }

}
