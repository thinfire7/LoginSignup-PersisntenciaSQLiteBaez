package proyecto.covid.Utilidades;


public class Utilidades {

    //Constantes campos tabla usuario

    public final static String TABLA_USUARIO="usuario";
    public final static String CAMPO_NOMBRECOMPLETO="nombreCompleto";
    public final static String CAMPO_USUARIOREGISTRADO="usuarioRegistrado";
    public final static String CAMPO_CORREO="correo";
    public final static String CAMPO_CONTRASENA="contrasena";
    public final static String CAMPO_CONFIRMARCONTRASENA="confirmarContrasena";
    public final static String CAMPO_SEXO="sexo";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " ("+CAMPO_NOMBRECOMPLETO+" TEXT, "+CAMPO_USUARIOREGISTRADO+" TEXT, "
            +CAMPO_CORREO+" TEXT, "+CAMPO_CONTRASENA+" TEXT, "+CAMPO_CONFIRMARCONTRASENA+" TEXT, "+CAMPO_SEXO+" TEXT) ";

}
