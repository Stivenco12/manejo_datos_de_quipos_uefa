package manejo_datos_de_quipos_uefa;

import manejo_datos_de_quipos_uefa.application.usecase.EquipoUseCase;

public class Main {
    public static void main(String[] args) {
        EquipoUseCase servicio = new EquipoUseCase();

        servicio.listarEquiposs();
    }
}