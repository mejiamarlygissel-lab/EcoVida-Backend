package com.Sistemagestionresiduos;

import com.Sistemagestionresiduos.dao.UsuarioDao;
import com.Sistemagestionresiduos.dao.RecoleccionDao;
import com.Sistemagestionresiduos.model.Usuario;
import com.Sistemagestionresiduos.model.Recoleccion;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final UsuarioDao uDao = new UsuarioDao();
    private static final RecoleccionDao rDao = new RecoleccionDao();

    public static void main(String[] args) {
        int opcion = -1;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
        } while (opcion != 0);
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== ECO VIDA S.A.S =====");
        System.out.println("--- GESTIÓN DE USUARIOS ---");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Listar Usuarios");
        System.out.println("3. Actualizar Usuario");
        System.out.println("4. Eliminar Usuario");
        System.out.println("\n--- GESTIÓN DE RECOLECCIONES ---");
        System.out.println("5. Registrar Recolección");
        System.out.println("6. Listar Recolecciones");
        System.out.println("7. Actualizar Recolección");
        System.out.println("8. Eliminar Recolección");
        System.out.println("\n0. Salir");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarUsuario();
            case 2 -> uDao.listarUsuarios().forEach(System.out::println);
            case 3 -> actualizarUsuario();
            case 4 -> uDao.eliminarUsuario(leerEntero("ID usuario a eliminar: "));
            case 5 -> registrarRecoleccion();
            case 6 -> rDao.listarRecolecciones().forEach(System.out::println);
            case 7 -> actualizarRecoleccion();
            case 8 -> rDao.eliminarRecoleccion(leerEntero("ID recolección a eliminar: "));
            case 0 -> System.out.println("¡Saliendo del sistema!");
            default -> System.out.println("Opción no válida.");
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese un número: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static void registrarUsuario() {
        System.out.println("\n--- Registro de Nuevo Usuario ---");
        String n = leerEntrada("Nombre: ");
        String a = leerEntrada("Apellido: ");
        String c = leerEntrada("Correo: ");
        String tel = leerEntrada("Teléfono: ");
        String p = leerEntrada("Contraseña: ");
        uDao.insertarUsuario(new Usuario(n, a, c, tel, p));
    }

    private static void actualizarUsuario() {
        System.out.print("Ingrese el ID del usuario que desea actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        String n = leerEntrada("Nuevo Nombre: ");
        String a = leerEntrada("Nuevo Apellido: ");
        String c = leerEntrada("Nuevo Correo: ");
        String tel = leerEntrada("Nuevo Teléfono: ");
        String p = leerEntrada("Nueva Contraseña: ");

        Usuario usuarioEditado = new Usuario(id, n, a, c, tel, p);

        uDao.actualizarUsuario(usuarioEditado);

    }

    private static void registrarRecoleccion() {
        int idUser = leerEntero("ID Usuario responsable: ");
        String tipo = leerEntrada("Tipo de Residuo: ");

        double peso = -1;
        while (peso <= 0) {
            try {
                peso = Double.parseDouble(leerEntrada("Peso (KG): "));
                if (peso <= 0) {
                    System.out.println("¡Error! El peso debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido para el peso.");
            }
        }

        String ubicacion = leerEntrada("Ubicación: ");
        String observaciones = leerEntrada("Observaciones: ");

        Usuario usuarioResponsable = new Usuario();
        usuarioResponsable.setIdUsuario(idUser);

        String horaUsuario = leerEntrada("Hora de la recolección (Ej: 7:00, 5:05): ");

        String periodo = leerEntrada("¿Es AM o PM?: ").toUpperCase();

        String horaCompleta = horaUsuario + " " + periodo;

        String horaActual = java.time.LocalTime.parse(horaCompleta,
                java.time.format.DateTimeFormatter.ofPattern("h:mm a", java.util.Locale.ENGLISH))
                .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));

        rDao.insertarRecoleccion(
                new Recoleccion(0, LocalDate.now().toString(), horaActual, ubicacion, tipo, peso, observaciones,
                        usuarioResponsable));
    }

    private static void actualizarRecoleccion() {
        int idRecoleccion = leerEntero("ID recolección a actualizar: ");
        String tipo = leerEntrada("Nuevo Tipo: ");
        double peso = Double.parseDouble(leerEntrada("Nuevo Peso: "));
        String ubicacion = leerEntrada("Nueva Ubicación: ");
        String observaciones = leerEntrada("Observaciones: ");
        int idU = leerEntero("ID Usuario: ");

        Usuario usuarioResponsable = new Usuario();
        usuarioResponsable.setIdUsuario(idU);

        String horaActual = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));

        
        rDao.actualizarRecoleccion(
                new Recoleccion(idRecoleccion, LocalDate.now().toString(), horaActual, ubicacion, tipo, peso,
                        observaciones, usuarioResponsable));
    }

    private static String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }
}