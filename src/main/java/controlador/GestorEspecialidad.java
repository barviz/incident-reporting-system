package controlador;

import modelo.Especialidad;
import org.hibernate.Query;
import org.hibernate.Transaction;
import persistencia.ConfigHibernate;

import java.util.*;

public class GestorEspecialidad extends Gestor {

    public GestorEspecialidad() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public Especialidad getEspecialidadXId(Long idEspecialidad){

        try {

            Query consulta = sesion.createQuery("FROM Especialidad WHERE id = :idEspecialidad");
            consulta.setParameter("idEspecialidad", idEspecialidad);

            Especialidad especialidad = (Especialidad) consulta.uniqueResult();
            return especialidad;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Especialidad getEspecialidadXDenominacion(String denominacion){

        try {

            Query consulta = sesion.createQuery("FROM Especialidad WHERE denominacion = :denominacion");
            consulta.setParameter("denominacion", denominacion);

            Especialidad especialidad = (Especialidad) consulta.uniqueResult();
            return especialidad;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public void agregarEspecialidad(String denominacion) {
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Especialidad especialidad = new Especialidad(denominacion);
            sesion.save(especialidad);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }*/


    public List<Especialidad> obtenerTodasEspecialidades() {
        try {
            Query consulta = sesion.createQuery("FROM Especialidad");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /*public void eliminarEspecialidad(Long idEspecialidad) {
        try {
            Especialidad especialidad = getEspecialidadXId(idEspecialidad);

            if (especialidad != null) {
                sesion.delete(especialidad);
                sesion.getTransaction().commit();
                System.out.println("Especialidad eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningúna especialidad con el id proporcionado.");
            }
        } catch (RuntimeException e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Error al eliminar la especialidad.");
        }
    }*/

}


