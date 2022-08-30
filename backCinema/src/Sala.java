import java.util.ArrayList;

public class Sala {

    //Atributos
    private long id;
    private String nome;
    private ArrayList<Cadeira> cadeiras;

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
