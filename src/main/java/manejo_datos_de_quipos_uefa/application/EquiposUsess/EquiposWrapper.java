package manejo_datos_de_quipos_uefa.application.EquiposUsess;


import java.util.List;

public class EquiposWrapper {
    private List<Equipos> equipos;

    public EquiposWrapper() {}

    public EquiposWrapper(List<Equipos> equipos) {
        this.equipos = equipos;
    }

    public List<Equipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipos> equipos) {
        this.equipos = equipos;
    }
}

