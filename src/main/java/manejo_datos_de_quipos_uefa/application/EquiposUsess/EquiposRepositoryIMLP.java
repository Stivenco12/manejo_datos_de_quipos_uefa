package manejo_datos_de_quipos_uefa.application.EquiposUsess;

import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import manejo_datos_de_quipos_uefa.application.PlayesUses.Player;
import manejo_datos_de_quipos_uefa.application.statisticsUses.Statistics;

public class EquiposRepositoryIMLP {
    private static final String FILE_PATH = "players.json";
    private Map<Integer, Equipos> equipo;
    private ObjectMapper objectMapper;

    public EquiposRepositoryIMLP() {
        objectMapper = new ObjectMapper();
        equipo = new HashMap<>(); 
        cargarEquiposDesdeJson();
    }

    private void cargarEquiposDesdeJson() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
            if (inputStream == null) {
                System.out.println(" Archivo JSON no encontrado en resources: " + FILE_PATH);
                return;
            }

            List<Equipos> listaEquipos = leerEquiposDesdeJson(inputStream);
            if (listaEquipos.isEmpty()) {
                System.out.println(" No hay equipos en el archivo JSON.");
                return;
            }
            for (Equipos equipoObj : listaEquipos) {
                equipo.put(equipoObj.getId(), equipoObj);
            }
            System.out.println(" Equipos cargados con éxito: " + equipo.size() + " registros.");
        } catch (Exception e) {
            System.out.println(" Error al cargar equipos desde JSON.");
            e.printStackTrace();
        }
    }

    public List<Equipos> leerEquiposDesdeJson(InputStream inputStream) {
        try {
            if (inputStream == null) {
                throw new IllegalArgumentException("Archivo JSON no encontrado");
            }

            EquiposWrapper wrapper = objectMapper.readValue(inputStream, EquiposWrapper.class);
            return wrapper.getEquipos();
        } catch (Exception e) {
            System.out.println(" Error al leer el JSON.");
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    
    public Map<Integer, Equipos> TodaLalista() {
        if (equipo.isEmpty()) {
            System.out.println("No hay equipos disponibles.");
            return new HashMap<>(); 
        }
        equipo.forEach((id, equipoObj) -> System.out.println("ID: " + id + " | " + equipoObj));
        return equipo; 
    }


    public Map<Integer, Equipos> listarEquiposs() {
        if (equipo.isEmpty()) {
            System.out.println("No hay equipos disponibles.");
            return new HashMap<>(); 
        }
        return equipo;
    }
    public Optional<Equipos> findFirstByYear(List<Equipos> equipos) {
        return equipos.stream()
                .filter(e -> {
                    try {
                        return Integer.parseInt(e.getyearfoundation()) > 2000; 
                    } catch (NumberFormatException ex) {
                        return false;
                    }
                })
                .findFirst();
                }

    public Map<Integer, Equipos> entrenadores() {
        BiConsumer<Integer, Equipos> imprimirEntrenador = (id, equipos) -> 
        System.out.println("Equipo: " + equipos.getName() + " | Entrenador: " + equipos.getCoach());
        equipo.forEach(imprimirEntrenador);
        return equipo;
    }

    public void calcularPromedioEdadJugadores() {
        BiConsumer<String, List<Player>> calcularPromedioEdad = (equipoNombre, jugadores) -> {
            double promedio = jugadores.stream()
                    .mapToInt(Player::getAge) 
                    .average() 
                    .orElse(0.0); 

            System.out.println("Equipo: " + equipoNombre + " | Promedio de edad: " + promedio);
        };
        equipo.values().forEach(equipo -> calcularPromedioEdad.accept(equipo.getName(), equipo.getPlayers()));
    }
    
    public void mostrarEquiposConMasDeWonGames() {
        Predicate<Equipos> filtroPG = equipo -> equipo.getStatistics().stream()
                .anyMatch(stats -> stats.getWonGames() > 20);

        List<Equipos> equiposFiltrados = equipo.values().stream()
                .filter(filtroPG)
                .collect(Collectors.toList());

        if (equiposFiltrados.isEmpty()) {
            System.out.println("No hay equipos con más de 20 partidos ganados.");
        } else {
            equiposFiltrados.forEach(e -> {
                int partidosGanados = e.getStatistics().stream()
                        .mapToInt(Statistics::getWonGames) 
                        .sum(); 
                System.out.println("Equipo: " + e.getName() + " | Partidos ganados: " + partidosGanados);
            });
        }
    }
    public void mostrarJugadormasAlto(List<Equipos> equipos) {
        BiConsumer<String, List<Player>> calcularAltura = (nombreEquipo, jugadores) -> {
            jugadores.stream()
                    .max(Comparator.comparingInt(Player::getHeight)) 
                    .ifPresent(jugador -> System.out.println(
                            nombreEquipo + ": " + jugador.getName() + " (" + jugador.getHeight() + " cm)"
                    ));
        };
    
        equipos.forEach(equipo -> 
            calcularAltura.accept(equipo.getName(), equipo.getPlayers())
        );
    }
    
    public void mostrarEquiposConGolesTotales() {
        List<Equipos> equiposFiltrados = equipo.values().stream()
                .collect(Collectors.toList());

        if (equiposFiltrados.isEmpty()) {
            System.out.println("No hay equipos con goles.");
        } else {
            equiposFiltrados.forEach(e -> {
                int partidosGanados = e.getStatistics().stream()
                        .mapToInt(Statistics::getGoalsFor) 
                        .sum(); 
                System.out.println("Equipo: " + e.getName() + " | Goles totales: " + partidosGanados);
            });
        }
    }
    public void listarEquiposConMasDe15Puntos(List<Equipos> equipos) {
        Predicate<Equipos> filtroPuntos = equipo -> equipo.getStatistics().stream()
                .anyMatch(stats -> stats.getTotalPoints() > 15);
                equipos.stream()
                .filter(filtroPuntos)
                .forEach(e -> {
                    int maxPuntos = e.getStatistics().stream()
                                     .mapToInt(Statistics::getTotalPoints)
                                     .max().orElse(0);
                    System.out.println("Equipo: " + e.getName() + " - Puntos: " + maxPuntos);
                });
    }
    public void calcularPromedioGolesAFavor(List<Equipos> equipos) {
        double promedioGoles = equipos.stream()
                .flatMap(equipo -> equipo.getStatistics().stream())
                .collect(Collectors.averagingInt(Statistics::getGoalsFor)); 
    
        System.out.println("Promedio de goles a favor por equipo: " + promedioGoles);
    }

    public void mostrarEquipoConMasVictorias(List<Equipos> equipos) {
        equipos.stream()
            .max(Comparator.comparingInt(equipo -> equipo.getStatistics().stream()
                .mapToInt(Statistics::getWonGames)
                .sum()))
            .ifPresent(equipo -> {
                int totalVictorias = equipo.getStatistics().stream()
                    .mapToInt(Statistics::getWonGames)
                    .sum();
                System.out.println("Equipo con más victorias: " + equipo.getName() + " (" + totalVictorias + " victorias)");
            });
    }

    public void mostrarJugadorMasAlto(List<Equipos> equipos) {
        equipos.stream()
            .flatMap(equipo -> equipo.getPlayers().stream()) 
            .max(Comparator.comparingInt(Player::getHeight)) 
            .ifPresent(jugador -> 
                System.out.println("Jugador más alto: " + jugador.getName() + " (" + jugador.getHeight() + " cm)")
            );
    }

    public long contarDelanteros(List<Equipos> equipos) {
        return equipos.stream()
            .flatMap(equipo -> equipo.getPlayers().stream()) 
            .filter(jugador -> "Delantero".equalsIgnoreCase(jugador.getPosition()))
            .count(); 
    }
 
    public void obtenerEntrenadoresConEmpates(List<Equipos> equipos) {
        List<String> entrenadores = equipos.stream()
            .filter(equipo -> equipo.getStatistics().stream().anyMatch(stat -> stat.getDrawnGames() > 0)) 
            .map(Equipos::getCoach) 
            .distinct() 
            .toList(); 
    
        System.out.println("Entrenadores con al menos un empate: " + entrenadores);
    }
    
    public void mapearEquiposConGolesAFavor(List<Equipos> equipos) {
        Map<String, Integer> golesPorEquipo = equipos.stream()
            .collect(Collectors.toMap(
                Equipos::getName, 
                equipo -> equipo.getStatistics().stream().mapToInt(Statistics::getGoalsFor).sum() 
            ));

        System.out.println("Goles a favor por equipo: " + golesPorEquipo);
    }
    public void listarJugadoresBrasileñosOrdenadosPorEdad(List<Equipos> equipos) {
        List<Player> jugadoresBrasileños = equipos.stream()
            .flatMap(equipo -> equipo.getPlayers().stream()) 
            .filter(jugador -> "Brasileño".equalsIgnoreCase(jugador.getNationality())) 
            .sorted(Comparator.comparingInt(Player::getAge)) 
            .collect(Collectors.toList()); 
    
        jugadoresBrasileños.forEach(jugador -> 
            System.out.println(jugador.getName() + " - Edad: " + jugador.getAge()));
    }

    public void filtrarEquiposPorNombreEntrenador(List<Equipos> equipos) {
        Predicate<Equipos> entrenadorLargo = equipo -> equipo.getCoach().length() > 10;
    
        List<Equipos> equiposFiltrados = equipos.stream()
            .filter(entrenadorLargo)
            .collect(Collectors.toList()); 
    
        equiposFiltrados.forEach(equipo -> 
            System.out.println(equipo.getName() + " - Entrenador: " + equipo.getCoach()));
    }

    public boolean algunEquipoConMasDe25Puntos(List<Equipos> equipos) {
        return equipos.stream()
            .anyMatch(equipo -> equipo.getStatistics().stream()
                .anyMatch(stat -> stat.getTotalPoints() > 25));
    }

    public Map<String, Long> contarJugadoresPorPosicion(List<Equipos> equipos) {
        return equipos.stream()
            .flatMap(equipo -> equipo.getPlayers().stream())
            .collect(Collectors.groupingBy(Player::getPosition, Collectors.counting()));
    }

    public List<Equipos> equiposConMasDe20Goles(List<Equipos> equipos) {
        return equipos.stream()
            .filter(equipo -> equipo.getStatistics().stream()
                .anyMatch(stat -> stat.getGoalsFor() > 20))
            .sorted((e1, e2) -> Integer.compare(
                e2.getStatistics().stream().mapToInt(Statistics::getGoalsFor).sum(),
                e1.getStatistics().stream().mapToInt(Statistics::getGoalsFor).sum()
            )) 
            .collect(Collectors.toList());
    }
}