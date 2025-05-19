import java.time.LocalDateTime;


class Reg {
    LocalDateTime fh;
    Double t;
    Double h;
    public Reg(LocalDateTime fh, Double t, Double h) {
        this.fh = fh;
        this.t = t;
        this.h = h;
    }

    public LocalDateTime getFh() {
        return fh;
    }

    public Double getT() {
        return t;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "FH:" + fh + ", T:" + t + ", H:" + h;
    }
}

