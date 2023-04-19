package es.uji.agendaws.servicios;

import es.uji.agendaws.servicios.RecursoAgenda;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author badia
 * Instancia los objetos que ofrecen los servicios web que queremos que despliegue el servidor (p.e. Tomcat)
 * Se almacenan en un conjunto que es devuelto por el método gestSingletons
 *
 */

@ApplicationPath("/servicios") // Innecesario si se incluye como elemento servlet-mapping en el fichero web.xml
public class AplicacionAgenda extends Application {
   private Set<Object> singletons = new HashSet <Object>();

   public AplicacionAgenda() {
      singletons.add(new RecursoAgenda());
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
}
