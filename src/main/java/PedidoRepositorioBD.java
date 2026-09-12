public class PedidoRepositorioBD implements IPedidoRepositorio {
    @Override
    public void guardar(Pedido pedido) {
        System.out.println("[BD] Guardando pedido #" + pedido.getId() + " exitosamente.");
    }
}