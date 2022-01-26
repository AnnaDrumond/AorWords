package programa;

import java.io.Serializable;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Superclasse de Publicitário, Programador e Intermediário. Permite criar uma lista de Utilizadores na classe EmpresaAorWords.
 * Contém os atributos comuns a todos os utilizadores, incluíndo um número de identificação único para cada utilizador.
 */
public abstract class Utilizador implements Serializable {

    protected String nome;
    protected String morada;
    protected String telefone;
    protected int idUtilizador;
    protected String email;
    protected String password;
    protected static int geradorId = 1000;

    /**
     * Construtor.
     * Contém os dados comuns a todos os tipos de utilizadores.
     * Implementa o iterador do gerador de ‘id’.
     *
     * @param nome     corresponde ao nome do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param morada   corresponde à morada do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param email    corresponde ao e-mail do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param telefone corresponde ao número de telefone do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param password corresponde à password da conta do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     */

    public Utilizador(String nome, String morada, String telefone, String email, String password) {
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.idUtilizador = geradorId++;
        this.password = password;
    }


    /**
     * Getter do atributo nome.
     *
     * @return retorna o nome do utilizador.
     */
    public String getNome() {
        return nome;
    }


    /**
     * Setter do atributo nome.
     *
     * @param nome modifica o nome do utilizador ativo.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    /**
     * Getter do atributo morada.
     *
     * @return retorna a morada do utilizador.
     */
    public String getMorada() {
        return morada;
    }


    /**
     * Setter do atributo morada.
     *
     * @param morada modifica o morada do utilizador ativo.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }


    /**
     * Getter do atributo telefone.
     *
     * @return retorna o número de telefone do utilizador.
     */
    public String getTelefone() {
        return telefone;
    }


    /**
     * Setter do atributo telefone.
     *
     * @param telefone modifica o número de telefone do utilizador ativo.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    /**
     * Getter do atributo email.
     *
     * @return retorna o email do utilizador.
     */
    public String getEmail() {
        return email;
    }


    /**
     * Getter do atributo password.
     *
     * @return retorna a palavra-chave do utilizador.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Setter do atributo password.
     *
     * @param password modifica a palavra-chave do utilizador ativo.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Getter do atributo idUtilizador.
     *
     * @return retorna o número de identificação único do utilizador.
     */
    public int getIdUtilizador() {
        return idUtilizador;
    }


    /**
     * Recebe o valor do iterador atualizado, para poder gerar novos Ids, a partir do número
     * do último Id gerado.
     *
     * @param novoValor referência do valor atualizado a ser usado pelo geradorId.
     */
    public static void buscarValorAtualizadoParaIterador(int novoValor) {
        geradorId = novoValor;
    }
}

