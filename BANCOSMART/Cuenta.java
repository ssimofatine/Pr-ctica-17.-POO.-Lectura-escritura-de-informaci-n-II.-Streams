import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Cuenta {
    UUID id;
    Cliente prop;
    double sdo;
    List<Transaccion> trans;

    public Cuenta(Cliente prop) {
        this.id = UUID.randomUUID();
        this.prop = prop;
        this.sdo = 0.0;
        this.trans = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public Cliente getProp() {
        return prop;
    }

    public double getSdo() {
        return sdo;
    }

    public List<Transaccion> getTrans() {
        return trans;
    }

    public void addTransaccion(Transaccion t) {
        this.trans.add(t);
        if (t.getTipo() == TipoTransaccion.INGRESO) {
            this.sdo += t.getImp();
        } else if (t.getTipo() == TipoTransaccion.GASTO) {
            this.sdo -= t.getImp();
        }
    }

    @Override
    public String toString() {
        return id + "-" + prop.getDni() + "|Sdo:" + String.format("%.2f", sdo) + "e";
    }
}