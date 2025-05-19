class Cliente {
    String dni;
    String nom;
    String em;
    String tel;
    String dir;
    String ciu;
    String pa;

    public Cliente(String dni, String nom, String em, String tel, String dir, String ciu, String pa) {
        this.dni = dni;
        this.nom = nom;
        this.em = em;
        this.tel = tel;
        this.dir = dir;
        this.ciu = ciu;
        this.pa = pa;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getEm() {
        return em;
    }

    public String getTel() {
        return tel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEm(String em) {
        this.em = em;
    }


    @Override
    public String toString() {
        return "DNI:" + dni + "|Nom:" + nom + "|" + em + "-" + tel;
    }
}