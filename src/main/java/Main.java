import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        IPedidoRepositorio repo = new PedidoRepositorioBD();
        INotificadorEmail notificador = new NotificadorEmail();
        ProcesadorPedido procesador = new ProcesadorPedido(repo, notificador);

        Pedido pedido1 = new Pedido("ORD-101", new BigDecimal("200.00"), "juan.quichica.27@unsch.edu.pe");
        Descuento descuento10 = new DescuentoPorcentaje(new BigDecimal("0.10"));

        procesador.procesar(pedido1, descuento10);
    }
}