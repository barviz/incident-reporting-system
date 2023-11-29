package modelo;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "ap_utn_servicio")
@Getter @Setter
public class Servicio  extends EntidadId{

    @Column(nullable = false)
    private String denominacion;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClienteServicio> clientes;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;

}
