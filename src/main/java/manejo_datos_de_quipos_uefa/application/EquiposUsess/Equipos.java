package manejo_datos_de_quipos_uefa.application.EquiposUsess;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import manejo_datos_de_quipos_uefa.application.PlayesUses.Player;
import manejo_datos_de_quipos_uefa.application.statisticsUses.Statistics;


public class Equipos implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("yearfoundation")
    private String yearfoundation;

    @JsonProperty("coach")
    private String coach;

    @JsonProperty("statistics")
    private List<Statistics> statistics; // Nueva lista para estadísticas

    @JsonProperty("players")
    private List<Player> players; // Nueva lista para jugadores

    public Equipos() {}

    public Equipos(int id, String name, String yearFoundation, String coach, List<Statistics> statistics, List<Player> players) {
        this.id = id;
        this.name = name;
        this.yearfoundation = yearFoundation;
        this.coach = coach;
        this.statistics = statistics;
        this.players = players;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getyearfoundation() { return yearfoundation; }
    public String getCoach() { return coach; }
    public List<Statistics> getStatistics() { return statistics; }
    public List<Player> getPlayers() { return players; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setyearfoundation(String yearfoundation) { this.yearfoundation = yearfoundation; }
    public void setCoach(String coach) { this.coach = coach; }
    public void setStatistics(List<Statistics> statistics) { this.statistics = statistics; }
    public void setPlayers(List<Player> players) { this.players = players; }

    @Override
    public String toString() {
        return "Equipos [id=" + id + ", name=" + name + ", yearFoundation=" + yearfoundation +
               ", coach=" + coach + ", statistics=" + statistics + ", players=" + players + "]";
    }
}
