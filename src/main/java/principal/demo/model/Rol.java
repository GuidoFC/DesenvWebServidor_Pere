package principal.demo.model;

public enum Rol {
    // push
    OBSERVADOR,
    ASTRONOMO;

    public static Rol AsignarRol(String rol) {
        switch (rol) {
            case "OBSERVADOR": return OBSERVADOR;
            case "ASTRONOMO": return ASTRONOMO;
            default: return OBSERVADOR;
        }
    }


}
