public class NotificadorEmail implements INotificadorEmail {
    @Override
    public void enviarEmail(String destinatario, String mensaje) {
        System.out.println("[EMAIL enviado a " + destinatario + "]: " + mensaje);
    }
}