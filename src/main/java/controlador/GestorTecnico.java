package controlador;

import modelo.Tecnico;
import org.hibernate.Query;
import org.hibernate.Transaction;
import persistencia.ConfigHibernate;

import java.util.Collections;
import java.util.List;

public class GestorTecnico extends Gestor {

    public GestorTecnico() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public Tecnico getTecnicoXLegajo(int legajo){
        try {

            Query consulta = sesion.createQuery("SELECT tecnico FROM Tecnico tecnico WHERE tecnico.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            Tecnico tecnico = (Tecnico) consulta.uniqueResult();
            return tecnico;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public void agregarTecnico(Tecnico tecnico) {
        Transaction transaction = null;
        try {
            transaction = sesion.beginTransaction();
            sesion.persist(tecnico);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error al agregar tecnico: " + e.getMessage(), e);
        }
    }*/

    public List<Tecnico> getAllTecnicos() {
        try {
            Query consulta = sesion.createQuery("FROM Tecnico");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /*public void eliminarTecnico(int legajo) {
        try {
            Tecnico tecnico = getTecnicoXLegajo(legajo);

            if (tecnico != null) {
                sesion.delete(tecnico);
                sesion.getTransaction().commit();
                System.out.println("Tecnico eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún tecnico con el legajo proporcionado.");
            }
        } catch (RuntimeException e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Error al eliminar el tecnico.");
        }
    }*/
}