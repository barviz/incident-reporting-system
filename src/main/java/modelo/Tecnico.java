package modelo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ap_utn_tecnico")
@Getter @Setter
public class Tecnico extends Empleado{

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReporteIncidencia> reportesIncidencia;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TecnicoEspecialidad> tecnicoEspecialidades;

    public Tecnico(){}

    public Tecnico(String apellido, String nombre, int legajo, DatosContacto datosContacto) {
        super(apellido, nombre, legajo, datosContacto);
    }

    public void addEspecialidad(TecnicoEspecialidad tecnicoEspecialidad){
        if(this.tecnicoEspecialidades == null)
            this.tecnicoEspecialidades = new ArrayList<TecnicoEspecialidad>();
        this.tecnicoEspecialidades.add(tecnicoEspecialidad);
    }
}
