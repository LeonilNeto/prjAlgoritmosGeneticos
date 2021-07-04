package model;
/** Classe responsável por representar um objeto do tipo Livro
 * @author Leonil
 */
public class Livro {   
    
    private int idLivro;
    private String titulo;    
    private String autor;
    private String baixa;
    private int disponivel;
    private int quantidade;
    private int emprestimos;
    private int buscas;

    public Livro() { }
    
    /**
     * @param idLivro - int
     * @param titulo - string
     * @param autor - String
     * @param baixa - String
     * @param disponivel - int
     * @param quantidade - int
     * @param emprestimos - int
     * @param buscas - int
     */
    public void recuperar(int idLivro, String titulo, String autor, String baixa, 
            int disponivel, int quantidade, int emprestimos, int buscas) {
        setIdLivro(idLivro);
        setTitulo(titulo);
        setAutor(autor);
        setBaixa(baixa);
        setDisponivel(disponivel);
        setQuantidade(quantidade);
        setEmprestimos(emprestimos);
        setBuscas(buscas);
    }
    
    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    
     public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(int emprestimos) {
        this.emprestimos = emprestimos;
    }   

    public String getBaixa() {
        return baixa;
    }

    public void setBaixa(String baixa) {
        this.baixa = baixa;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }   

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }    

    public int getBuscas() {
        return buscas;
    }

    public void setBuscas(int buscas) {
        this.buscas = buscas;
    }   
}