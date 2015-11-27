
/**
 * Write a description of class MailClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailClient
{
    // Atributo de la clase MailServer que representara el servidor asociado al usuario.
    private MailServer server;
    // Atributo que guarda en un String el usuario.
    private String user;

    /**
     * Constructor que permitira crear un nuevo cliente a partir de un servidor y asignado
     * a un usuario.
     */
    public MailClient(MailServer server, String user){
        this.server = server;
        this.user = user;
    }
     /**
      * Metodo que nos recuperara el siguiente mail que tenga un usuario, si no tuvira ninguno
      * devolvera null.
      */
    public MailItem getNextMailItem(){
        return server.getNextMailItem(user);
    }
    
    /**
     * Metodo que nos imprimira por pantalla el siguiente mensaje disponible que tenga, si no
     * hubiera ninguno imprimira un mensaje diciendonoslo.
     */
    public void printNextMailItem(){
        // Obtenemos mediante el metodo anterior el siguiente mensaje, sera de la clase MailItem
        MailItem mensaje = getNextMailItem();
        // Si es distinto de null, luego exite un mensaje, lo imprime con un metodo de la clase
        // MailItem
        if(mensaje != null){
            mensaje.print();
        }
        else{
            // Si no imprimimos que no hay ningun mensaje en la bandeja de entrada.
            System.out.println("No hay ningun mensaje en la bandeja");
        }
    }
    
    /**
     * Metodo que nos permite crear un mensaje miEmail de la clase MailItem y dejarlo guardado en
     * el servidor.
     */
    public void sendMailItem(String para, String mensaje){
        // Creamos miEmail de la clase MailItem con un usuario(de quien es), para quien es y 
        // un mensaje.
        MailItem miEmail = new MailItem(user, para, mensaje);
        // Hacemos una llamada a el metodo post que se encarga de "fijarlo" en el servidor, como
        // necesita un parametro le pasamos nuestro miEmail creado.
        server.post(miEmail);
    }
}
