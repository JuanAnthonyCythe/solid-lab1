import java.math.BigDecimal;

public class Pedido {
    private final String id;
    private final BigDecimal montoTotal;
    private final String clienteEmail;

    public Pedido(String id, BigDecimal montoTotal, String clienteEmail) {
        this.id = id;
        this.montoTotal = montoTotal;
        this.clienteEmail = clienteEmail;
    }

    public String getId() { return id; }
    public BigDecimal getMontoTotal() { return montoTotal; }
    public String getClienteEmail() { return clienteEmail; }
}