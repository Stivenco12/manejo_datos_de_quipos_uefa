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
                System.out.println("1.) Listado de Equipos Fundados Después del Año 2000:");
                System.out.println("2.) mpresión de Nombres de Entrenadores:");
                System.out.println("3.) Cálculo del Promedio de Edad de Jugadores por Equipo:");
                System.out.println("4.) Listado de Equipos con Más de 20 Victorias");
                System.out.println("5.) Obtención del Jugador Más Alto de Cada Equipo");
                System.out.println("6.) Cálculo del Total de Goles por Equipo");
                System.out.println("7.) Listar equipos con más de 15 puntos en la fase de liga");
                System.out.println("8.) Obtener el promedio de goles a favor por equipo");
                System.out.println("9.) Mostrar el equipo con más victorias");
                System.out.println("10.) Encontrar el jugador más alto de todos los equipos");
                System.out.println("11.) Contar cuántos jugadores son delanteros en toda la UEFA Champions League");
                System.out.println("12.) Obtener los nombres de los entrenadores de los equipos que han empatado al menos un partido");
                System.out.println("13.) Crear un Map con los nombres de los equipos como clave y la cantidad total de goles a favor como valor");
                System.out.println("14.) Listar los jugadores de nacionalidad Brasileño y ordenarlos por edad");
                System.out.println("15.) Filtrar los equipos cuyo entrenador tiene más de 10 caracteres en su nombre");
                System.out.println("16.) Determinar si algún equipo tiene más de 25 puntos");
                System.out.println("17.) Agrupar los jugadores por posición y contar cuántos hay en cada una");
                System.out.println("18.) Obtener los equipos con más de 20 goles a favor ordenados de mayor a menor");
                System.out.println("19.) saliendo.....");
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
                            System.out.println("Error volviendo al menus");
                        }
                        break;
                    case "7":
                        try {
                            servicio.listarEquiposConMasDe15Puntos(new ArrayList<>(servicio.listarEquiposs().values()));

                        } catch (Exception e) {
                            System.out.println("Error volviendo al menus");
                        }
                        break;
                    case "8":
                        try {
                            servicio.calcularPromedioGolesAFavor(new ArrayList<>(servicio.listarEquiposs().values()));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menus");
                        }
                        break;
                    case "9":
                        try {
                            servicio.mostrarEquipoConMasVictorias(new ArrayList<>(servicio.listarEquiposs().values()));

                        } catch (Exception e) {
                            System.out.println("Error volviendo al menus");
                        }
                        break;
                    case "10":
                        try {
                            servicio.mostrarJugadorMasAlto(new ArrayList<>(servicio.listarEquiposs().values()));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;
                    case "11":
                        try {
                            System.out.println("Total de delanteros: " + servicio.contarDelanteros(new ArrayList<>(servicio.listarEquiposs().values())));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;
                    case "12":
                        try {
                            servicio.obtenerEntrenadoresConEmpates(new ArrayList<>(servicio.listarEquiposs().values()));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;
                    case "13":
                        try {
                            servicio.mapearEquiposConGolesAFavor(new ArrayList<>(servicio.listarEquiposs().values()));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;
                    case "14":
                        try {
                            servicio.listarJugadoresBrasileñosOrdenadosPorEdad(new ArrayList<>(servicio.listarEquiposs().values()));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;    
                    case "15":
                        try {
                            servicio.filtrarEquiposPorNombreEntrenador(new ArrayList<>(servicio.listarEquiposs().values()));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;
                    case "16":
                        try {
                            boolean existeEquipoConMasDe25Puntos = servicio.algunEquipoConMasDe25Puntos(new ArrayList<>(servicio.listarEquiposs().values()));
                            if (existeEquipoConMasDe25Puntos) {
                                System.out.println("Sí, hay al menos un equipo con más de 25 puntos.");
                            } else {
                                System.out.println("No, ningún equipo tiene más de 25 puntos.");
                            }   
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;
                    case "17":
                        try {
                            System.out.println(servicio.contarJugadoresPorPosicion(new ArrayList<>(servicio.listarEquiposs().values())));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        break;
                    case "18":
                        try {
                            servicio.equiposConMasDe20Goles(new ArrayList<>(servicio.listarEquiposs().values()))
                            .forEach(equipo -> System.out.println(equipo.getName()));
                        } catch (Exception e) {
                            System.out.println("Error volviendo al menú");
                        }
                        
                        break;
                    case "19":
                        System.out.println("saliendo");
                        break;
                }
            }while(!menu.equals("19"));
        }catch(Exception e) {
            System.out.println("Error por favor vuelve a iniciar el programa");
        }
       
    }
}