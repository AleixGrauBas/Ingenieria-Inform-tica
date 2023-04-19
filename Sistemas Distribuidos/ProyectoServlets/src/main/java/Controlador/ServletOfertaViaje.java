package Controlador;

import Modelo.GestorViajes;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet(name = "ServletOfertaViaje", value = "/ServletOfertaViaje")
public class ServletOfertaViaje extends HttpServlet {

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
        String codCli = (String) session.getAttribute("codCli");
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        String fecha = request.getParameter("fecha");
        long precio = Long.parseLong(request.getParameter("precio"));
        long numPlazas = Long.parseLong(request.getParameter("numPlazas"));

        JSONObject res = gestor.ofertaViaje(codCli,origen,destino,fecha,precio,numPlazas);
        request.setAttribute("res", res);
        RequestDispatcher vista = request.getRequestDispatcher("resOferta.jsp");
        vista.forward(request,response);
    }
}
