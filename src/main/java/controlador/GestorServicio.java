package controlador;

import modelo.Servicio;
import org.hibernate.Query;
import persistencia.ConfigHibernate;

import java.util.Collections;
import java.util.List;

public class GestorServicio extends Gestor {

    public GestorServicio() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public Servicio getServicioXCUIT(String denominacion){
        try {

            Query consulta = sesion.createQuery("SELECT servicio FROM Servicio servicio WHERE servicio.denominacion = :denominacion");
            consulta.setParameter("denominacion", denominacion);

            Servicio servicio = (Servicio) consulta.uniqueResult();
            return servicio;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Servicio> getAllServicios() {
        try {
            Query consulta = sesion.createQuery("FROM Servicio");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
