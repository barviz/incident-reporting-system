package vista;

import modelo.DatosContacto;
import modelo.Tecnico;

import java.util.Scanner;

public class TecnicoVista {

    public Tecnico cargarTecnicoNuevo(int legajo) {

        Tecnico tecnico = new Tecnico();

        System.out.println("Ingrese el apellido del Tecnico");
        tecnico.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del tecnico");
        tecnico.setNombre(new Scanner(System.in).nextLine());
        tecnico.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();

        System.out.println("Ingrese el celular del Tecnico");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Tecnico");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Tecnico");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        tecnico.setDatosContacto(datosContacto);

        return tecnico;
    }

    public Tecnico modificarTecnico(Tecnico tecnico, int legajo) {

        System.out.println("Ingrese el apellido del Tecnico");
        tecnico.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del Tecnico");
        tecnico.setNombre(new Scanner(System.in).nextLine());
        tecnico.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();

        System.out.println("Ingrese el celular del Tecnico");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Tecnico");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Tecnico");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());

        tecnico.setDatosContacto(datosContacto);

        return tecnico;
    }

}