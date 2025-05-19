import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

class Banco {
    List<Cuenta> ctas;

    public Banco() {
        this.ctas = new ArrayList<>();
    }

    public void addCuenta(Cuenta c) {
        this.ctas.add(c);
    }

    public Cuenta getCuentaPorId(UUID id) {
        for (Cuenta cta : ctas) {
            if (cta.getId().equals(id)) {
                return cta;
            }
        }
        return null;
    }

    public List<Transaccion> getTodasTransacciones() {
        List<Transaccion> todas = new ArrayList<>();
        for (Cuenta cta : ctas) {
            todas.addAll(cta.getTrans());
        }
        return todas;
    }

    @Override
    public String toString() {
        String res = "Banco Ctas:";
        for (Cuenta cta : ctas) {
            res += cta.toString() + " ";
        }
        return res;
    }


    public void getTransImpMin(double val) {
        System.out.println("Trans>" + val + ":"); // Salida corta
        getTodasTransacciones().stream()
                .filter(t -> t.getImp() > val)
                .sorted(Comparator.comparing(Transaccion::getF))
                .forEach(System.out::println);
    }

    public double getIngTot() {
        return getTodasTransacciones().stream()
                .filter(t -> t.getTipo() == TipoTransaccion.INGRESO)
                .mapToDouble(Transaccion::getImp)
                .sum();
    }

    public double getGastTot() {
        return getTodasTransacciones().stream()
                .filter(t -> t.getTipo() == TipoTransaccion.GASTO)
                .mapToDouble(Transaccion::getImp)
                .sum();
    }

    public void getCtasPorSdo() {
        System.out.println("Ctas x Sdo:");
        this.ctas.stream()
                .sorted(Comparator.comparing(Cuenta::getSdo).reversed())
                .forEach(System.out::println);
    }

    public Map<UUID, Long> getNumTransPorCta() {
        return this.ctas.stream()
                .collect(Collectors.toMap(
                        Cuenta::getId,
                        c -> (long) c.getTrans().size()
                ));
    }

    public void getCtasActivas() {
        System.out.println("Ctas Activas:");
        Month mesAct = LocalDate.now().getMonth();
        int anioAct = LocalDate.now().getYear();

        this.ctas.stream()
                .filter(c -> c.getTrans().stream()
                        .anyMatch(t -> t.getF().getMonth() == mesAct && t.getF().getYear() == anioAct))
                .forEach(System.out::println);
    }




}