package modelo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "ap_utn_reporte_incidencia")
@Getter @Setter
public class ReporteIncidencia  extends EntidadId {

    @Column(nullable = false, unique = true)
    private String codigoReporte;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @Column(nullable = false)
    private String descripcionProblema;

    @Column(nullable = false)
    private String tipoProblema;//basico intermedio complejo

    @ManyToOne
    @JoinColumn(name = "idservicio")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idoperador")
    private OperadorMesaAyuda operador;

    @ManyToOne
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idtecnico", nullable = false)
    private Tecnico tecnico;

    private int tiempoEstimadoResolucion;//horas o minutos

    @Temporal(TemporalType.DATE)
    private Date fechaPosibleResolucion;

    private String estado; //pendiente en proceso resuelto anulado

    private String observacionesTecnico;

    @ManyToOne
    @JoinColumn(name = "idespecialidad", nullable = false)
    private Especialidad especialidad;
}
