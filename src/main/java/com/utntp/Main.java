package com.utntp;

import controlador.GestorCliente;
import controlador.GestorEspecialidad;
import controlador.GestorTecnico;
import modelo.*;
import vista.ClienteVista;
import vista.EspecialidadVista;
import vista.TecnicoVista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.println("Seleccione la funcionalidad a ejecutar");
                System.out.println("1- Administrar Clientes");
                System.out.println("2- Administrar Tecnico");
                System.out.println("3- Administrar Especialidad");
                System.out.println("4- Administrar Operador");
                System.out.println("5- Administrar Servicios");
                System.out.println("6- Administrar Reporte Incidencia");
                System.out.println("7- Reporte de incidentes por tecnico por dias ");
                System.out.println("8- Reporte de incidentes resueltos por especialidad");
                System.out.println("9- Reporte Estadistico Técnico mas eficiente");
                System.out.println("10- Cambiar estado de Reporte de incidencia");

                int opcionMenu = scanner.nextInt();
                scanner.nextLine();

                if (opcionMenu == 1) {
                    GestorCliente gCliente = new GestorCliente();

                    System.out.println("Ingrese el cuit del cliente");
                    Long cuit = new Scanner(System.in).nextLong();

                    Cliente cliente = gCliente.getClienteXCUIT(cuit);

                    ClienteVista vistaCliente = new ClienteVista();

                    if (cliente == null) {
                        System.out.println("El cliente buscado no existe. Proceda a cargar uno nuevo");
                        cliente = vistaCliente.cargarClienteNuevo();
                        gCliente.guardar(cliente);
                    } else {
                        System.out.println("El cliente " + cliente.getRazonSocial() + " ya existe. Para modificar ingrese U, si desea eliminar ingrese E");
                        String accion = new Scanner(System.in).nextLine();
                        if (accion.toUpperCase().equals("U")) {
                            cliente = vistaCliente.modificarCliente(cliente);
                            gCliente.guardar(cliente);
                        } else if (accion.toUpperCase().equals("E")) {
                            gCliente.eliminar(cliente);
                        }
                    }
                }
                else if (opcionMenu == 2) {
                    GestorTecnico gTecnico = new GestorTecnico();

                    System.out.println("Ingrese el legajo del tecnico");
                    Integer legajo = new Scanner(System.in).nextInt();

                    Tecnico tecnico = gTecnico.getTecnicoXLegajo(legajo);

                    TecnicoVista vistaTecnico = new TecnicoVista();

                    if (tecnico == null) {
                        System.out.println("El tecnico buscado no existe. Proceda a cargar uno nuevo");
                        tecnico = vistaTecnico.cargarTecnicoNuevo(legajo);
                        EspecialidadVista vistaEspecialidad = new EspecialidadVista();
                        vistaEspecialidad.cargarEspecialidadesTecnico(tecnico);
                        gTecnico.guardar(tecnico);
                    } else {
                        System.out.println("El tecnico " + tecnico.getApellido() + " " + tecnico.getNombre() + " ya existe. Para modificar ingrese U, si desea eliminar ingrese E");
                        String accion = new Scanner(System.in).nextLine();
                        if (accion.toUpperCase().equals("U")) {
                            tecnico = vistaTecnico.modificarTecnico(tecnico, legajo);
                            gTecnico.guardar(tecnico);
                        } else if (accion.toUpperCase().equals("E")) {
                            gTecnico.eliminar(tecnico);
                        }
                    }
                }
                else if (opcionMenu == 3) {

                    GestorEspecialidad gEspecialidad = new GestorEspecialidad();
                    System.out.println("Especialidades");

                    List <Especialidad> especialidades = gEspecialidad.obtenerTodasEspecialidades();
                    for (Especialidad especialidad : especialidades) {
                        System.out.println("ID: " + especialidad.getId() + ", Denominación: " + especialidad.getDenominacion());
                    }

                    System.out.println("Ingrese la denominacion de la especialidad");
                    String denominacion = new Scanner(System.in).nextLine();

                    Especialidad especialidad = gEspecialidad.getEspecialidadXDenominacion(denominacion);

                    EspecialidadVista vistaEspecialidad = new EspecialidadVista();

                    if (especialidad == null) {
                        System.out.println("La especialidad buscada no existe. Proceda a cargar una nueva");
                        especialidad = vistaEspecialidad.cargarEspecialidadNueva();
                        gEspecialidad.guardar(especialidad);
                    } else {
                        System.out.println("La especialidad " + especialidad.getDenominacion() + " ya existe. Para modificar ingrese U, si desea eliminar ingrese E");
                        String accion = new Scanner(System.in).nextLine();
                        if (accion.toUpperCase().equals("U")) {
                            especialidad = vistaEspecialidad.modificarEspecialidad(especialidad);
                            gEspecialidad.guardar(especialidad);
                        } else if (accion.toUpperCase().equals("E")) {
                            gEspecialidad.eliminar(especialidad);
                        }
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }         }
        while (true) ;
    }

    public static void obtenerConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/argprog_utn?useTimezone=true&serverTimezone=UTC", "root", "root");
            if(con != null){
                System.out.println("CONECTADO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}