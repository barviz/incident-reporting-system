package modelo;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ap_utn_cliente")
@Getter
@Setter
public class Cliente extends EntidadId{

    @Column(length = 150)
    private String razonSocial;

    @Column(nullable = false, unique = true)
    private long cuit;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClienteServicio> servicios;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddatoscontacto")
    private DatosContacto datosContacto;


}
