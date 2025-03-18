package manejo_datos_de_quipos_uefa;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import manejo_datos_de_quipos_uefa.application.EquiposUsess.Equipos;
import manejo_datos_de_quipos_uefa.application.EquiposUsess.EquiposRepositoryIMLP;

public class Main {
    public static void main(String[] args) {
        EquiposRepositoryIMLP servicio = new EquiposRepositoryIMLP();
        try (Scanner sc = new Scanner(System.in)){
            String menu;
            do {
                System.out.println("1.) equipo de fundacion mayor a 2000");
                System.out.println("2.) todos los entrenadores");
                System.out.println("3.) partidos ganados de cada equipo");
                System.out.println("4.) Listado de Equipos con Más de 20 Victorias");
                System.out.println("5.) Obtención del Jugador Más Alto de Cada Equipo");
                System.out.println("6.) Cálculo del Total de Goles por Equipo");
                System.out.print("Elige una opcion = ");
                menu = sc.nextLine();
                System.out.println("");

                switch (menu) {
                    case "1":
                        try {
                            Map<Integer, Equipos> equipos = servicio.listarEquiposs();
                            Optional<Equipos> equipo = servicio.findFirstByYear(new ArrayList<>(equipos.values()));
                            equipo.ifPresentOrElse(
                                e -> System.out.println("Primer equipo encontrado: " + e.getName()),
                                () -> System.out.println("No hay equipos fundados después del 2000.")
                            );
                            System.out.println("");
                        } catch (Exception e) {
                           System.out.println("Error volviendo al menu");
                        }
                        break;
                    case "2":
                        try {
                            servicio.entrenadores();
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menu");
                        }
                        break;
                    case "3":
                        try {
                            servicio.calcularPromedioEdadJugadores();
                            System.out.println();
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menu");
                        }
                        break;
                   case "4":
                        try {
                            servicio.mostrarEquiposConMasDeWonGames();
                            System.out.println();
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menu");
                        }
                        break;
                    case "5":
                        try {
                            servicio.mostrarJugadormasAlto(servicio.listarEquiposs().values().stream().toList());
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menu");
                        }
                        break;
                    case "6":
                        try {
                            servicio.mostrarEquiposConGolesTotales();
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menu");
                        }
                        break;
                }
            }while(!menu.equals("10"));
        }catch(Exception e) {
            System.out.println("Error por favor vuelve a iniciar el programa");
        }
       
    }
}