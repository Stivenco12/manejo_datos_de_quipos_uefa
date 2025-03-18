package manejo_datos_de_quipos_uefa.application.statisticsUses;


import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("pj") private int playedGames;
    @JsonProperty("pg") private int wonGames;
    @JsonProperty("pe") private int drawnGames;
    @JsonProperty("pp") private int lostGames;
    @JsonProperty("gf") private int goalsFor;
    @JsonProperty("gc") private int goalsAgainst;
    @JsonProperty("tp") private int totalPoints;

    public Statistics() {}

    public Statistics(int playedGames, int wonGames, int drawnGames, int lostGames, int goalsFor, int goalsAgainst, int totalPoints) {
        this.playedGames = playedGames;
        this.wonGames = wonGames;
        this.drawnGames = drawnGames;
        this.lostGames = lostGames;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.totalPoints = totalPoints;
    }

    public int getPlayedGames() { return playedGames; }
    public int getWonGames() { return wonGames; }
    public int getDrawnGames() { return drawnGames; }
    public int getLostGames() { return lostGames; }
    public int getGoalsFor() { return goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
    public int getTotalPoints() { return totalPoints; }

    public void setPlayedGames(int playedGames) { this.playedGames = playedGames; }
    public void setWonGames(int wonGames) { this.wonGames = wonGames; }
    public void setDrawnGames(int drawnGames) { this.drawnGames = drawnGames; }
    public void setLostGames(int lostGames) { this.lostGames = lostGames; }
    public void setGoalsFor(int goalsFor) { this.goalsFor = goalsFor; }
    public void setGoalsAgainst(int goalsAgainst) { this.goalsAgainst = goalsAgainst; }
    public void setTotalPoints(int totalPoints) { this.totalPoints = totalPoints; }

    @Override
    public String toString() {
        return "Statistics [playedGames=" + playedGames + ", wonGames=" + wonGames +
               ", drawnGames=" + drawnGames + ", lostGames=" + lostGames + ", goalsFor=" + goalsFor +
               ", goalsAgainst=" + goalsAgainst + ", totalPoints=" + totalPoints + "]";
    }
}
