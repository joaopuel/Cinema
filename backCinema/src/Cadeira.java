public class Cadeira {

    //Atributos
    private long id;
    private String codigo;
    private String tipoCadeira;
    private int fileira;
    private int ordemFileira;

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoCadeira() {
        return tipoCadeira;
    }

    public void setTipoCadeira(String tipoCadeira) {
        this.tipoCadeira = tipoCadeira;
    }

    public int getFileira() {
        return fileira;
    }

    public void setFileira(int fileira) {
        this.fileira = fileira;
    }

    public int getOrdemFileira() {
        return ordemFileira;
    }

    public void setOrdemFileira(int ordemFileira) {
        this.ordemFileira = ordemFileira;
    }
}
