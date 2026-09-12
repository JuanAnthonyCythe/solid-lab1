import java.math.BigDecimal;

public class DescuentoPorcentaje implements Descuento {
    private final BigDecimal porcentaje;

    public DescuentoPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public BigDecimal aplicar(BigDecimal monto) {
        BigDecimal rebaja = monto.multiply(porcentaje);
        return monto.subtract(rebaja);
    }
}