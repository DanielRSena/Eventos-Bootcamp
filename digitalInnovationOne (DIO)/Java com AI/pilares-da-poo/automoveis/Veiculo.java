package automoveis;
public abstract class Veiculo {
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
    public String getChassi() {
        return this.chassi;
    }
    private String chassi;

    public abstract void ligar();
}
