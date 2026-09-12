import java.math.BigDecimal;

public class ProcesadorPedido {
    private final IPedidoRepositorio repositorio;
    private final INotificadorEmail notificador;

    public ProcesadorPedido(IPedidoRepositorio repositorio, INotificadorEmail notificador) {
        this.repositorio = repositorio;
        this.notificador = notificador;
    }

    public void procesar(Pedido pedido, Descuento estrategiaDescuento) {
        BigDecimal totalFinal = estrategiaDescuento.aplicar(pedido.getMontoTotal());
        repositorio.guardar(pedido);
        String mensaje = "Su pedido con total final $" + totalFinal + " ha sido procesado.";
        notificador.enviarEmail(pedido.getClienteEmail(), mensaje);
    }
}