package controlador;

import modelo.ReporteIncidencia;
import org.hibernate.Query;
import org.hibernate.Transaction;
import persistencia.ConfigHibernate;

import java.util.*;

public class GestorReporteIncidencia extends Gestor {

    public GestorReporteIncidencia() {
        if(sesion == null || !sesion.isOpen())
            sesion = ConfigHibernate.openSession();
    }

    public ReporteIncidencia getReporteIncidenciaXCodRep(String codigoReporte){
        try {

            Query consulta = sesion.createQuery("SELECT reporteIncidencia FROM ReporteIncidencia reporteIncidencia WHERE reporteIncidencia.codigoReporte = :codigoReporte");
            consulta.setParameter("codigoReporte", codigoReporte);

            ReporteIncidencia reporteIncidencia = (ReporteIncidencia) consulta.uniqueResult();
            return reporteIncidencia;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object> getReportesXFiltro(Date fechaDesde, Date fechaHasta, String estadoReporte, Long idEspecialidad){

        try {
        StringBuilder jpql = new StringBuilder("SELECT reporteIncidencia FROM ReporteIncidencia reporteIncidencia WHERE 1 = 1");

        Map<String, Object> parameters = new HashMap<>();

        if (fechaDesde != null) {
            jpql.append(" AND reporteIncidencia.fechaAlta >= :fechaDesde");
            parameters.put("fechaDesde", fechaDesde);
        }

        if (fechaHasta != null) {
            jpql.append(" AND reporteIncidencia.fechaPosibleResolucion <= :fechaHasta");
            parameters.put("fechaHasta", fechaHasta);
        }

        if (estadoReporte != null && !estadoReporte.isEmpty()) {
            jpql.append(" AND reporteIncidencia.estado = :estadoReporte");
            parameters.put("estadoReporte", estadoReporte);
        }

        if (idEspecialidad != null) {
            jpql.append(" AND reporteIncidencia.idespecialidad = :idEspecialidad");
            parameters.put("idEspecialidad", idEspecialidad);
        }

        Query consulta = sesion.createQuery(jpql.toString());

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            consulta.setParameter(entry.getKey(), entry.getValue());
        }

        return consulta.list();
    } catch (RuntimeException e) {
        e.printStackTrace();
        return Collections.emptyList();
    }

    }

    /*public void agregarReporteIncidencia(ReporteIncidencia nuevoReporte) {
        Transaction transaction = null;

        try {
            transaction = sesion.beginTransaction();
            sesion.persist(nuevoReporte);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al agregar reporte de incidencia: " + e.getMessage(), e);
        }
    }*/

    public List<ReporteIncidencia> obtenerTodosReportesIncidencia() {
        try {
            Query consulta = sesion.createQuery("FROM ReporteIncidencia");
            return consulta.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /*public void eliminarReporteIncidencia(String codigoReporte) {

        try {
            ReporteIncidencia reporteIncidencia = getReporteIncidenciaXCodRep(codigoReporte);

            if (reporteIncidencia != null) {
                sesion.delete(reporteIncidencia);
                sesion.getTransaction().commit();
                System.out.println("Reporte de incidencia eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún reporte de incidencia con el codigo proporcionado.");
            }
        } catch (RuntimeException e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Error al eliminar el reporte de incidencia.");
        }
    }*/
}