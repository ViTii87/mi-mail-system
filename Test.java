
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    /**
     * Metodo que prueba el segundo apartado de la actividad 0170.
     */
    public void test1(){
        MailServer server = new MailServer();
        MailClient cliente1 = new MailClient(server, "pepe");
        MailClient cliente2 = new MailClient(server, "victor");
        System.out.println("Probando que funciona el metodo getNextMailItemAndAutorespond()");
        System.out.println("###################################################################");
        cliente1.sendMailItem("victor","asunto1","mensaje1");
        cliente2.getNextMailItemAndAutorespond();
        cliente1.printNextMailItem();
        System.out.println();
        System.out.println("Probando cuando no hemos mandado ningun correo");
        System.out.println("###################################################################");
        cliente2.getNextMailItemAndAutorespond();
        cliente1.printNextMailItem();
    }
    
    public void test2(){
        MailServer server = new MailServer();
        MailClient cliente1 = new MailClient(server, "pepe");
        MailClient cliente2 = new MailClient(server, "victor");
        
        System.out.println("Probando que funciona el metodo imprimeElUltimo()");
        System.out.println("###################################################################");
        cliente1.sendMailItem("victor","asunto1","mensaje1");
        cliente2.printNextMailItem();
        System.out.println("###################################################################");
        cliente2.imprimeElUltimo();
        System.out.println("###################################################################");
        cliente2.printNextMailItem();
        System.out.println("###################################################################");
        cliente2.imprimeElUltimo();
    }
}
