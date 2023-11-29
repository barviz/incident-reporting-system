package modelo;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "ap_utn_especialidad")
@Getter @Setter
public class Especialidad  extends EntidadId{

    @Column(length = 175, nullable = false)
    private String denominacion;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TecnicoEspecialidad> tecnicosEspecialidad;

    public Especialidad(){}

    public Especialidad(String denominacion) {
        this.denominacion = denominacion;
    }
}
