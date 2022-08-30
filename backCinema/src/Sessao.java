import java.time.LocalDateTime;

public class Sessao {

    //Atributos
    private long id;
    private LocalDateTime dataSessao;
    private double valorInteira;
    private double valorMeia;

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(LocalDateTime dataSessao) {
        this.dataSessao = dataSessao;
    }

    public double getValorInteira() {
        return valorInteira;
    }

    public void setValorInteira(double valorInteira) {
        this.valorInteira = valorInteira;
    }

    public double getValorMeia() {
        return valorMeia;
    }

    public void setValorMeia(double valorMeia) {
        this.valorMeia = valorMeia;
    }
}
