
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
    // Atributo para guardar si es spam
    private boolean spam;
    // Atributo para el numero de mensajes enviados
    private int enviados;
    // Atributo para el numero de mensajes recibidos
    private int recibidos;
    // Atributo para guardar el numero de mensajes spam
    private int mensSpam;
    // Atributo para guardar la direecion de la persona que envio el mensaje mas largo
    private String dirPeronaMasLarga;
    // Atributo para guardar el contenido del ultimo mensaje
    private String ultimoMensGuardado;
    // Atributo para guardar el ultimo mensaje de Spam recibido
    private MailItem ultimoSpam;

    /**
     * Constructor que permitira crear un nuevo cliente a partir de un servidor y asignado
     * a un usuario.
     */
    public MailClient(MailServer server, String user){
        this.server = server;
        this.user = user;
        ultimoEmail = null;
        this.spam = false;
        ultimoMensGuardado="";
    }

    /**
     * Metodo que nos recuperara el siguiente mail que tenga un usuario, si no tuvira ninguno
     * devolvera null.
     */
    public MailItem getNextMailItem(){
        // Creamos un objeto email de la clase MailItem y le pido el siguiente email de mi usuario
        MailItem email = server.getNextMailItem(user);
        // Si es distinto de email lo guardamos en nuestro atributo, sino devolvemos null.
        if(email != null){
            String mens = email.getMessage();
            if(mens.length() > ultimoMensGuardado.length() ){
                ultimoMensGuardado = mens;
                dirPeronaMasLarga = email.getFrom();
            }
            if(mens.contains("regalo") || (mens.contains("promocion"))){
                if(mens.contains("trabajo")){
                    spam = false;
                    ultimoEmail = email;
                }
                else{
                    spam = true;
                    ultimoSpam = email;
                    email = null;
                    mensSpam += 1;
                }
            }
            else{
                spam = false;
                ultimoEmail = email;
            }
            recibidos += 1;
        }
        return email;
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
            ultimoEmail = mensaje;
            mensaje.print();
        }
        else{
            if(spam == true){
                System.out.println("Solo tienes Spam");
            }
            else{
                // Si no imprimimos que no hay ningun mensaje en la bandeja de entrada.
                System.out.println("No hay ningun mensaje en la bandeja");
            }
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
        enviados += 1;
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
        // Si existe el mensaje entonces contestamos automaticamente.
        if(mensaje != null){
            sendMailItem(mensaje.getFrom(), "RE " + mensaje.getSubject(), "Estoy de vacaciones.\n\t " + mensaje.getMessage());
        }
        enviados += 1;
    }

    /**
     * Metodo para imprimir el ultimo mensaje obtenido del servidor cuantas veces quiera.
     */
    public void imprimeElUltimo(){
        // Si es distinto de null, hay mensaje, luego lo imprimimos por pantalla, sino escribimos un mensaje por pantalla informando de ello.
        if(ultimoEmail != null){
            ultimoEmail.print();
        }
        else{
            System.out.println("No hay mensajes en la bandeja de entrada");
        }

    }
    
    /**
     * Metod que permite imprimir por pantalla una serie de estadisticas para el usuario
     */
    public void showStats(){
        System.out.println("Numero de mensajes enviados: " + enviados);
        System.out.println("Numero de mensajes recibidos: " + recibidos);
        System.out.println("Numero de mensajes spam: " + mensSpam);
        if(recibidos>0){
            System.out.println("Porcentaje Spam: " + 100*(mensSpam)/recibidos);
        }
        else{
            System.out.println("Porcentaje Spam: 0%");
        }
        if(dirPeronaMasLarga != null){
            System.out.println("Direccion de la persona con mensaje mas largo: " + dirPeronaMasLarga);
            System.out.println("Numero de caracteres del email mas largo: " + ultimoMensGuardado.length());
        }
        else{
            System.out.println("Direccion de la persona con mensaje mas largo: Ninguna");
            System.out.println("Numero de caracteres del email mas largo: 0");
        }
    }
    
    /**
     * Metodo para mostrar el contenido del ultimo correo que tiene spam
     */
    public void showInfoLastSpam(){
        if(ultimoSpam != null){
            System.out.println("Informacion del ultimo correo con Spam");
            ultimoSpam.print();
        }
        else{
            System.out.println("No hay correo con Spam");
        }
    }
}
