import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TestBanco {
    public static void main(String[] args) {
        Banco b = new Banco();
        Random rand = new Random();
        List<Cliente> clis = new ArrayList<>();

        // Crear 10 Clientes
        for (int i = 0; i < 10; i++) {
            Cliente cli = new Cliente(
                    (i + 1) + "DNI",
                    "Nom" + (i + 1),
                    "em" + (i + 1) + "@mail.com",
                    "60000000" + i,
                    "Dir " + (i + 1),
                    "Ciu" + (char)('A' + i),
                    "Pais" + (char)('X' + i % 3)
            );
            clis.add(cli);
            Cuenta cta = new Cuenta(cli);
            b.addCuenta(cta);

            int numTrans = 1 + rand.nextInt(10);
            for (int j = 0; j < numTrans; j++) {
                TipoTransaccion tipo = rand.nextBoolean() ? TipoTransaccion.INGRESO : TipoTransaccion.GASTO;
                double imp = Math.round((10 + (1500 - 10) * rand.nextDouble()) * 100.0) / 100.0;
                LocalDate f = LocalDate.now().minusDays(rand.nextInt(90));
                String desc = (tipo == TipoTransaccion.INGRESO ? "Ing" : "Gast") + (j + 1);

                if (j < 2 && i < 3) {
                    f = LocalDate.now().minusDays(rand.nextInt(LocalDate.now().getDayOfMonth()));
                }
                if (j % 3 == 0) f = f.minusMonths(1);
                if (j % 4 == 0) f = f.minusMonths(2);


                cta.addTransaccion(new Transaccion(cta, imp, f, tipo, desc));
            }
        }

        System.out.println("--- Banco Creado ---");
        System.out.println(b);
        System.out.println("--- Fin Banco Ctas ---");

        System.out.println("--- Pruebas Streams ---");

        // 1.
        b.getTransImpMin(500);
        System.out.println("---");

        // 2.
        System.out.println("Ingresos Totales: " + String.format("%.2f", b.getIngTot()) + "e");
        System.out.println("---");

        // 3.
        System.out.println("Gastos Totales: " + String.format("%.2f", b.getGastTot()) + "e");
        System.out.println("---");

        // 4.
        b.getCtasPorSdo();
        System.out.println("---");

        // 5.
        Map<UUID, Long> numTrans = b.getNumTransPorCta();
        System.out.println("Num Trans x Cta:");
        numTrans.forEach((idC, cnt) -> System.out.println(idC + ":" + cnt));
        System.out.println("---");

        // 6.
        b.getCtasActivas();
        System.out.println("---");



    }
}