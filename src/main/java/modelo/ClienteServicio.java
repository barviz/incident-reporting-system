package modelo;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ap_utn_cliente_servicio")
@Getter
@Setter
public class ClienteServicio extends EntidadId{

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idservicio")
    private Servicio servicio;
}

