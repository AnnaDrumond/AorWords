package programa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe que permite criar uma nova instância de Conta com a informação do utilizador a quem esta pertence.
 * Classe que é instanciada sempre que um novo utilizador do tipo programador ou publicitario é adicionado.
 * Classe é instanciada aquando da criação do fundador, correspondendo à conta da empresa.
 */

public class Conta implements Serializable {

    protected Utilizador user;
    protected double saldo;
    protected ArrayList<MovimentoFinanceiro> listaMovimentosFinanceiros;


    /**
     * Construtor.
     * Permite criar uma nova instância de Conta.
     * Aquando da criação da conta, esta não possui ainda saldo nem qualquer movimento.
     *
     * @param user instância de Utilizador que corresponde ao dono da conta.
     */
    public Conta(Utilizador user) {
        this.user = user;
        this.saldo = 0.0;
        this.listaMovimentosFinanceiros = new ArrayList<>(10);
    }


    /**
     * Getter do atributo user.
     *
     * @return retorna o utilizador a quem pertence a conta.
     */
    public Utilizador getUser() {
        return user;
    }


    /**
     * Getter do atributo saldo.
     *
     * @return retorna o dinheiro que se encontra, à ordem, naquela conta.
     */
    public double getSaldo() {
        saldo = Double.parseDouble(new BigDecimal(saldo).setScale(2, RoundingMode.UP).toString());
        return saldo;
    }


    /**
     * Getter do atributo ArrayList listaMovimentosFinanceiros.
     *
     * @return retorna a lista de movimentos daquela conta.
     */
    public ArrayList<MovimentoFinanceiro> getListaMovimentosFinanceiros() {
        return listaMovimentosFinanceiros;
    }


    /**
     * Adiciona um novo movimento financeiro à listaMovimentosFinanceiros daquele utilizador.
     *
     * @param movimentoFinanceiro corresponde ao movimento financeiro criado sempre que existe uma nova transação monetária.
     */
    public void adicionarMovFinanceiros(MovimentoFinanceiro movimentoFinanceiro) {
        listaMovimentosFinanceiros.add(movimentoFinanceiro);
    }


    /**
     * Adiciona ao saldo atual o valor introduzido pelo publicitário ou o valor das receitas obtidas com o
     * anúncio (no caso dos programados e empresa).
     *
     * @param valorADepositar corresponde ao valor introduzido pelo publicitário ou o valor calculado nos
     *                        métodos efetuarContasAnuncioEmpresa e efetuarContasAnuncioProgramador da classe EmpresaAorWords.
     */
    public void efetuarDeposito(double valorADepositar) {
        saldo = saldo + valorADepositar;
        MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro(TipoDeMovimento.DEPOSITO, valorADepositar);
        adicionarMovFinanceiros(movimentoFinanceiro);
    }


    /**
     * Retira ao saldo atual daquele utilizador o custo do anúncio.
     *
     * @param valorAPagar corresponde ao custo total do anúncio.
     */
    public void efetuarPagamento(double valorAPagar) {
        saldo = saldo - valorAPagar;
        MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro(TipoDeMovimento.PAGAMENTO, valorAPagar);
        adicionarMovFinanceiros(movimentoFinanceiro);
    }


    /**
     * Retira ao saldo atual o valor introduzido pelo utilizador programador, publicitário ou intermediário fundador.
     *
     * @param valorALevantar corresponde ao valor introduzido que vai ser retirado da conta.
     */
    public void efetuarLevantamento(double valorALevantar) {
        saldo = saldo - valorALevantar;
        MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro(TipoDeMovimento.LEVANTAMENTO, valorALevantar);
        adicionarMovFinanceiros(movimentoFinanceiro);
    }
}
