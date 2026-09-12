import java.math.BigDecimal;

public interface Descuento {
    BigDecimal aplicar(BigDecimal monto);
}