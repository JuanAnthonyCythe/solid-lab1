import java.math.BigDecimal;

public class DescuentoFijo implements Descuento {
    private final BigDecimal rebajaFija;

    public DescuentoFijo(BigDecimal rebajaFija) {
        this.rebajaFija = rebajaFija;
    }

    @Override
    public BigDecimal aplicar(BigDecimal monto) {
        BigDecimal total = monto.subtract(rebajaFija);
        return total.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : total;
    }
}