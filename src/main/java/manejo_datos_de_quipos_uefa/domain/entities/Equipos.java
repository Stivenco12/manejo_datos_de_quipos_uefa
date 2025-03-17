package manejo_datos_de_quipos_uefa.domain.entities;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipos implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("team")
    private List<Equipos> team;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name") 
    private String name;

    @JsonProperty("yearfoundation")
    private List<Equipos> yearfoundation;

    @JsonProperty("statistics")
    private String statistics;

    @JsonProperty("players")
    private List<Equipos> players;

    @JsonProperty("coach")
    private String coach;

    // Constructor por defecto
    public Equipos() {}

    // Constructor con parámetros
    public Equipos(List<Equipos> team, int id, String name, List<Equipos> yearfoundation, String statistics, List<Equipos> players, String coach) {
        this.team = team;
        this.id = id;
        this.name = name;
        this.yearfoundation = yearfoundation;
        this.statistics = statistics;
        this.players = players;
        this.coach = coach;
    }

    // Getters
    public List<Equipos> getTeam() { return team; }
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Equipos> getYearfoundation() { return yearfoundation; }
    public String getStatistics() { return statistics; }
    public List<Equipos> getPlayers() { return players; }
    public String getCoach() { return coach; }

    // Setters
    public void setTeam(List<Equipos> team) { this.team = team; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setYearfoundation(List<Equipos> yearfoundation) { this.yearfoundation = yearfoundation; }
    public void setStatistics(String statistics) { this.statistics = statistics; }
    public void setPlayers(List<Equipos> players) { this.players = players; }
    public void setCoach(String coach) { this.coach = coach; }

    @Override
    public String toString() {
        return "Equipos [team=" + team + ", id=" + id + ", name=" + name + ", yearfoundation=" + yearfoundation
                + ", statistics=" + statistics + ", players=" + players + ", coach=" + coach + "]";
    }

    
 
}
