package Controlador;

import Modelo.GestorViajes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet(name = "ServletBorrarViaje", value = "/ServletBorrarViaje")
public class ServletBorrarViaje extends HttpServlet {
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
        String codCli = (String) session.getAttribute("codCli");
        String codViaje = request.getParameter("codViaje");

        JSONObject res = gestor.borraViaje(codViaje,codCli);

        request.setAttribute("res", res);
        RequestDispatcher vista = request.getRequestDispatcher("resEliminar.jsp");
        vista.forward(request,response);
    }

}
