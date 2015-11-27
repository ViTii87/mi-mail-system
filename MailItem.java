
/**
 * Write a description of class MailItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailItem
{
    // Atributo que nos dira quien nos envia un mensaje
    private String from;
    // Atributo que nos dira a quien enviamos un mensaje
    private String to;
    // Atributo que contendra el cuerpo del mensaje
    private String message;
    // Atributo para definir el asunto del mensaje
    private String subject;
    
    /**
     * Constructor que nos permite crear un nuevo mail
     * Le pasaremos como parametros de quien viene (de)
     * A quien va dirigido (para)
     * Y el mensaje (mensaje)
     */
    public MailItem(String from, String to, String subject, String message){
        // Guardamos en los atributos los Strings pasados como parametros al constructor
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }
    
    /**
     * Metodo que devolvera un String para ver de quien nos viene el mensaje.
     */
    public String getFrom(){
        return from;
    }
    
    /**
     * Metodo que devolvera un String para ver a quien enviamos un mensaje.
     */
    public String getTo(){
        return to;
    }
    
    /**
     * Metodo que devolvera un String con el cuerpo del mensaje.
     */
    public String getMessage(){
        return message;
    }
    
    /**
     * Metodo para obtener el asunto del mensaje.
     */
    public String getSubject(){
        return subject;
    }
    
    /**
     * Metodo que imprimira por pantalla de quien viene el mensaje, para quien va
     * y el cuerpo de ese mensaje.
     */
    public void print(){
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
