import java.time.LocalDate;


class Transaccion {
    private static long nextId = 1;
    long id;
    Cuenta cta;
    double imp;
    LocalDate f;
    TipoTransaccion tipo;
    String desc;

    public Transaccion(Cuenta cta, double imp, LocalDate f, TipoTransaccion tipo, String desc) {
        this.id = nextId++;
        this.cta = cta;
        this.imp = imp;
        this.f = f;
        this.tipo = tipo;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public Cuenta getCta() {
        return cta;
    }

    public double getImp() {
        return imp;
    }

    public LocalDate getF() {
        return f;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setImp(double imp) { this.imp = imp; }
    public void setF(LocalDate f) { this.f = f; }
    public void setDesc(String desc) { this.desc = desc; }


    @Override
    public String toString() {
        return "ID:" + id + "|Cta:" + cta.getId() + "|" + tipo + " " + String.format("%.2f", imp) + "e " + f + "-" + desc;
    }
}