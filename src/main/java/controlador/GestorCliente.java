package controlador;

import modelo.Cliente;
import org.hibernate.Query;
import org.hibernate.Transaction;
import persistencia.ConfigHibernate;

import java.util.Collections;
import java.util.List;

public class GestorCliente extends Gestor {

    public GestorCliente() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public Cliente getClienteXCUIT(Long cuit){
        try {

            Query consulta = sesion.createQuery("SELECT cliente FROM Cliente cliente WHERE cliente.cuit = :cuit");
            consulta.setParameter("cuit", cuit);

            Cliente cliente = (Cliente) consulta.uniqueResult();
            return cliente;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public void agregarCliente(Cliente cliente) {
        Transaction transaction = null;
        try {
            transaction = sesion.beginTransaction();
            sesion.persist(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error al agregar cliente: " + e.getMessage(), e);
        }
    }*/

    public List<Cliente> getAllClientes() {
        try {
            Query consulta = sesion.createQuery("FROM Cliente");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /*public void eliminarCliente(long cuit) {
        try {
            Cliente cliente = getClienteXCUIT(cuit);

            if (cliente != null) {
                sesion.delete(cliente);
                sesion.getTransaction().commit();
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con el CUIT proporcionado.");
            }
        } catch (RuntimeException e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Error al eliminar el cliente.");
        }
    }*/
}
