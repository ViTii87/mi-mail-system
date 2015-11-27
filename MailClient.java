
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
    // Atributo para dejar guardado el ultimo email.
    private MailItem ultimoEmail;

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
    public void sendMailItem(String para, String asunto, String mensaje){
        // Creamos miEmail de la clase MailItem con un usuario(de quien es), para quien es y 
        // un mensaje.
        MailItem miEmail = new MailItem(user, para, asunto, mensaje);
        // Hacemos una llamada a el metodo post que se encarga de "fijarlo" en el servidor, como
        // necesita un parametro le pasamos nuestro miEmail creado.
        server.post(miEmail);
    }
     /**
      * Metodo que permite imprimir el numero de Emails que tiene el usuario en el buzon.
      */
    public void cuantosEmailsTengo(){
        // Creamos una variable local guardar el numero de email mediante una llamada a un metodo de la clase server.
        // Nota: no haria falta crear la variable local, se podria usar directamente en println, pero asi queda mas claro
        int numeroEmails = server.howManyMailItems(user);
        System.out.println("Tienes " + numeroEmails + " Emails en el buzon.");
    }
    
    /**
     * Metodo que crea una autorespuesta y se la envia al usuario que nos envio el correo.
     */
    public void getNextMailItemAndAutorespond(){
        // Obtenemos el siguiente mensaje.
        MailItem mensaje = getNextMailItem();
        ultimoEmail = mensaje;
        // Si existe el mensaje entonces contestamos automaticamente.
        if(mensaje != null){
            sendMailItem(mensaje.getFrom(), "RE " + mensaje.getSubject(), "Estoy de vacaciones.\n\t " + mensaje.getMessage());
        }
        
    }
    
    /**
     * Metodo para imprimir el ultimo mensaje obtenido del servidor cuantas veces quiera.
     */
   public void imprimeElUltimo(){
       // Si no tenemos el ultimo email obtenido en autorrespuesta descargaremos el ultimo que este disponible en la bandeja.
       if(ultimoEmail == null){
           MailItem mensaje = getNextMailItem();
           ultimoEmail = mensaje;
       }
       ultimoEmail.print();
    }
}
