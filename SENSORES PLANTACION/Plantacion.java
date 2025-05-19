import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Plantacion {

    public static List<Reg> genRegs() {
        List<Reg> regs = new ArrayList<>();
        Random r = new Random();
        LocalDateTime currentFh = LocalDateTime.now().minusDays(5);
        for (int i = 0; i < 100; i++) {
            double temp = 10 + (35 - 10) * r.nextDouble();
            double hum = 50 + (100 - 50) * r.nextDouble();
            regs.add(new Reg(currentFh, temp, hum));
            currentFh = currentFh.plusMinutes(1);
        }
        return regs;
    }

    public static void main(String[] args) {
        List<Reg> regs = genRegs();
        LocalDateTime ahora = LocalDateTime.now();

        System.out.println("1. T>25, H<70, Antes:");
        regs.stream()
                .filter(r -> r.getT() > 25)
                .filter(r -> r.getH() < 70)
                .filter(r -> r.getFh().isBefore(ahora))
                .forEach(System.out::println);

        System.out.println("2. Max T:");
        Optional<Reg> maxTReg = regs.stream()
                .max(Comparator.comparing(Reg::getT));
        maxTReg.ifPresent(System.out::println);

        System.out.println("3. Fechas/Horas:");
        List<LocalDateTime> fechas = regs.stream()
                .map(Reg::getFh)
                .collect(Collectors.toList());

        System.out.println("Lista creada. Items: " + fechas.size());


        System.out.println("4. H+5:");
        regs.stream()
                .peek(r -> r.setH(r.getH() + 5))
                .forEach(r -> System.out.println("T:" + r.getT() + ", H:" + r.getH() + ", FH:" + r.getFh()));

        System.out.println("5. Min T con H > 80:");
        Optional<Reg> minTRegH80 = regs.stream()
                .filter(r -> r.getH() > 80)
                .min(Comparator.comparing(Reg::getT));
        minTRegH80.ifPresent(r -> System.out.println("T:" + r.getT() + ", H:" + r.getH() + ", FH:" + r.getFh()));

        System.out.println("6. Check T>30, H>90, Hoy:");
        boolean cond6 = regs.stream()
                .anyMatch(r -> r.getT() > 30 && r.getH() > 90 && r.getFh().toLocalDate().equals(ahora.toLocalDate()));
        if (cond6) {
            System.out.println("Si hay regs.");
        } else {
            System.out.println("No hay regs.");
        }

        System.out.println("7. Skip 5, Limit 10:");
        regs.stream()
                .skip(5)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("8. Orden por Fecha:");
        regs.stream()
                .sorted(Comparator.comparing(Reg::getFh))
                .forEach(System.out::println);



    }
}