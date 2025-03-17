package manejo_datos_de_quipos_uefa.application.usecase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import manejo_datos_de_quipos_uefa.domain.entities.Equipos;

public class EquipoUseCase {
    private static final String FILE_PATH = "players.json";
    private Map<Integer, Equipos> equiposs;
    private ObjectMapper objectMapper ;

    public EquipoUseCase(){
        objectMapper  = new ObjectMapper();
        equiposs = cargarEquipos();
    }
    
    private Map<Integer, Equipos> cargarEquipos() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new HashMap<>();
            return objectMapper.readValue(file, new TypeReference<Map<Integer, Equipos>>() {});
          
        } catch (IOException e) {
            
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    
    public void listarEquiposs() {
        if (equiposs.isEmpty()) {
            System.out.println("No hay personas disponibles.");
            return;
        }
        equiposs.forEach((id, person) -> System.out.println("ID: " + id + " | " + person));
    }




}
