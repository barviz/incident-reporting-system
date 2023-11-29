package controlador;

import org.hibernate.Query;
import persistencia.ConfigHibernate;

import java.util.Collections;
import java.util.List;

public class GestorOperadorMesaAyuda extends Gestor {

    public GestorOperadorMesaAyuda() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public GestorOperadorMesaAyuda getOperadorMesaAyudaXLegajo(int legajo){
        try {

            Query consulta = sesion.createQuery("SELECT operador FROM OperadorMesaAyuda operador WHERE operador.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            GestorOperadorMesaAyuda operador = (GestorOperadorMesaAyuda) consulta.uniqueResult();
            return operador;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GestorOperadorMesaAyuda> getAllOperadorMesaAyuda() {
        try {
            Query consulta = sesion.createQuery("FROM OperadorMesaAyuda");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}