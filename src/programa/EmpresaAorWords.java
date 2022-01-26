package programa;

import ficheiros.FicheiroDeObjeto;
import ficheiros.FicheiroDeTexto;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe gestora do programa.
 * Possui os atributos que armazenam em memória os dados do Ficheiro de Configuração.
 * Lê os dados do Ficheiro Geral, onde fica guardado o ficheiro de objetos do programa.
 * Possui o atributo que permite guardar o utilizador ativo, bem os atributos que permitem guardar o ecrã e o Jpanel ativo.
 * Possui o atributo booleano que armazena a informação do idioma. Instancia um objeto FicheiroDeTexto e um objeto FicheiroDeObjeto.
 */

public class EmpresaAorWords implements Serializable {

    protected ArrayList<Utilizador> listaUtilizadores;
    protected ArrayList<Conta> listaContas;
    protected ArrayList<Anuncio> listaAnuncios;
    protected File ficheiroGeral;
    protected String emailFundador;
    protected boolean idiomaPortugues;
    protected Utilizador utilizadorAtivo;
    protected JPanel ecraAtivo;
    protected JPanel painelAtivo;
    protected ArrayList<String> dadosConfiguracao;
    protected FicheiroDeObjeto ficheiroDeObjeto = new FicheiroDeObjeto();
    protected FicheiroDeTexto ficheiroDeTexto = new FicheiroDeTexto();


    /**
     * Construtor.
     * Instancia o ArrayList de contas, o de utilizadores e o de anúncios.
     * Chama o método extrairDadosFicheiroDeConfiguracao().
     * Chama o método lerListasDoFicheiroGeral().
     *
     * @param args referência para o vetor de String 'args' do método main
     */
    public EmpresaAorWords(String[] args) {
        listaContas = new ArrayList<>(10);
        listaUtilizadores = new ArrayList<>(10);
        listaAnuncios = new ArrayList<>(10);
        dadosConfiguracao = new ArrayList<>(10);

        extrairDadosFicheiroDeConfiguracao(args);

        if (ficheiroGeral.exists()) {
            lerListasDoFicheiroGeral();
        }
    }


    /**
     * Getter do atributo listaUtilizadores.
     *
     * @return retorna o Arraylist da lista de todos os utilizadores registados.
     */
    public ArrayList<Utilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }

    /**
     * Getter do atributo utilizadorAtivo.
     *
     * @return retorna o utilizador que se encontra que está, naquele momento, com sessão iniciada.
     */
    public Utilizador getUtilizadorAtivo() {
        return utilizadorAtivo;
    }

    /**
     * Setter do atributo utilizadorAtivo.
     *
     * @param utilizadorAtivo modifica o utilizador que se encontra a utilizar a aplicação naquele momento.
     */
    public void setUtilizadorAtivo(Utilizador utilizadorAtivo) {
        this.utilizadorAtivo = utilizadorAtivo;
    }


    /**
     * Getter do atributo idiomaPortugues.
     *
     * @return retorna boolean com valor booleano true se o programa se encontra em português e false se se encontra em inglês.
     */
    public boolean isIdiomaPortugues() {
        return idiomaPortugues;
    }


    /**
     * Setter do atributo idiomaPortugues.
     *
     * @param idiomaPortugues modifica a língua em que o programa se encontra.
     */
    public void setIdiomaPortugues(boolean idiomaPortugues) {
        this.idiomaPortugues = idiomaPortugues;
    }


    /**
     * Getter do atributo ecraAtivo.
     *
     * @return retorna o Ecrã que está, neste momento, a ser mostrado ao utilizador.
     */
    public JPanel getEcraAtivo() {
        return ecraAtivo;
    }


    /**
     * Setter do atributo EcraAtivo.
     *
     * @param ecraAtivo modifica o ecrã que naquele momento está a ser mostrado ao utilizador.
     */
    public void setEcraAtivo(JPanel ecraAtivo) {
        this.ecraAtivo = ecraAtivo;
    }

    /**
     * Getter do atributo painelAtivo.
     *
     * @return retorna o Painel que está, neste momento, a ser mostrado ao utilizador.
     */
    public JPanel getPainelAtivo() {
        return painelAtivo;
    }


    /**
     * Setter do atributo painelAtivo.
     *
     * @param painelAtivo modifica o painel que naquele momento está a ser mostrado ao utilizador.
     */
    public void setPainelAtivo(JPanel painelAtivo) {
        this.painelAtivo = painelAtivo;
    }


    /**
     * Getter do atributo listaContas.
     *
     * @return retorna o Arraylist da lista de todas as contas criadas.
     */
    public ArrayList<Conta> getListaContas() {
        return listaContas;
    }


    /**
     * Getter do atributo ficheiroDeTexto.
     *
     * @return retorna a instância da classe de FicheiroDeTexto.
     */
    public FicheiroDeTexto getFicheiroDeTexto() {
        return ficheiroDeTexto;
    }


    /**
     * Getter do atributo listaAnuncios
     *
     * @return retorna o Arraylist da lista de todos os anúncios que foram criados.
     */
    public ArrayList<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }


    /**
     * Getter do atributo emailFundador.
     *
     * @return retorna o e-mail do utilizador fundador da empresa AoRWords recolhido do ficheiro de configuração.
     */
    public String getEmailFundador() {
        return emailFundador;
    }


    public ArrayList<String> getDadosConfiguracao() {
        return dadosConfiguracao;
    }


    /**
     * Extrai os dados do Ficheiro de Configuração.
     * Armazena estes dados em atributos da Classe EmpresaAorWords.
     *
     * @param args referência para o vetor de String 'args' do método main
     */
    public void extrairDadosFicheiroDeConfiguracao(String[] args) {

        try {

            if (args.length > 0 && args[0].equals("FicheiroDeConfiguracao.dat")) {
                if (!ficheiroDeTexto.abrirLeitura(args[0])) {
                    System.out.println("FicheiroDeConfiguracao.dat não encontrado.");
                    System.exit(1);
                }
            } else if (!ficheiroDeTexto.abrirLeitura("FicheiroDeConfiguracao.dat")) {
                System.out.println("FicheiroDeConfiguracao.dat não encontrado.");
                System.exit(1);
            }

            String auxiliarDeLinha;

            while ((auxiliarDeLinha = ficheiroDeTexto.lerLinha()) != null) {
                dadosConfiguracao.add(auxiliarDeLinha);
            }
            ficheiroDeTexto.fecharLeitura();
        } catch (IOException erro) {
            erro.printStackTrace();
        }

        emailFundador = dadosConfiguracao.get(3);
        ficheiroGeral = new File(dadosConfiguracao.get(5));
        idiomaPortugues = Boolean.parseBoolean(dadosConfiguracao.get(8));

        criarUtilizadorEContaDoFundador();
    }


    /**
     * Cria uma instância de Intermediário com os dados do fundador.
     * Cria uma instância de Conta, criando a conta da empresa administrada pelo fundador.
     * Adiciona a instância de intermediário criada à lista de utilizadores
     */
    public void criarUtilizadorEContaDoFundador() {
        Utilizador fundador = new Intermediario(dadosConfiguracao.get(0), dadosConfiguracao.get(1), dadosConfiguracao.get(2), emailFundador, dadosConfiguracao.get(4));
        Conta contaEmpresa = new Conta(fundador);
        listaUtilizadores.add(fundador);

        if (listaContas.size() == 0) {
            listaContas.add(contaEmpresa);
        }
    }


    /**
     * Atualiza a contagem inicial do iterador que gera os Ids dos utilizadores.
     * Chama o método static buscarValorAtualizadoParaIterador passando este valor
     * atualizado como parâmetro.
     */
    public void atualizarIterador() {
        int novoValor = listaUtilizadores.get(0).getIdUtilizador();

        for (Utilizador utilizador : listaUtilizadores) {
            if (novoValor <= utilizador.getIdUtilizador()) {
                novoValor = utilizador.getIdUtilizador() + 1;
            }
        }
        Utilizador.buscarValorAtualizadoParaIterador(novoValor);
    }


    /**
     * Lê os dados do ficheiro de objetos do programa.
     * Armazena estes dados em memória nas respetivas listas.
     */
    public void lerListasDoFicheiroGeral() {

        try {
            ficheiroDeObjeto.abreLeitura(ficheiroGeral);

            listaUtilizadores = (ArrayList<Utilizador>) ficheiroDeObjeto.leObjeto();
            listaContas = (ArrayList<Conta>) ficheiroDeObjeto.leObjeto();
            listaAnuncios = (ArrayList<Anuncio>) ficheiroDeObjeto.leObjeto();

            ficheiroDeObjeto.fechaLeitura();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Adiciona os utilizadores inseridos no programa dentro da ArrayList de utilizadores.
     *
     * @param utilizador instância de Programador/Publicitário/Intermediário criada em EcraAddUtilizador
     */
    public void adicionarUtilizadorNaArrayList(Utilizador utilizador) {
        if (utilizador != null) {
            listaUtilizadores.add(utilizador);
        }
    }


    /**
     * Adiciona a conta criada aquando da criação de um novo utilizador para a ArrayList de Contas
     *
     * @param conta instância de Conta criada em EcraAddUtilizador
     */

    public void adicionarContaNaArrayList(Conta conta) {
        if (conta != null) {
            listaContas.add(conta);
        }
    }


    /**
     * Chama o método efetuarContasAnúncioEmpresa para calcular as receitas que a empresa gerou com o novo anúncio.
     * Chama o método efetuarContasAnuncioProgramador para calcular as receitas que o programador gerou com o novo anúncio.
     * Chama o método efetuarContasAnuncioPublicitario para calcular o custo que o publiciário possui ao criar o novo anúncio.
     * Faz set das receitas geradas pelo programador e pela empresa com o anúncio.
     * Adiciona o anúncio à listaAnuncios.
     *
     * @param anuncio      instância de Anuncio criada em EcraAddAnuncio.
     * @param programador  instância de Programador criada em EcraAddAnuncio.
     * @param publicitario instância de Publicitario criada em EcraAddAnuncio.
     * @param custoTotal   custo total do anúncio calculado no método verificarAcaoTextFieldQuantidadeExibicoes da classe EcraAddAnuncio.
     */
    public void adicionarAnuncioNaArrayListEAdicionarMovimentos(Anuncio anuncio, Programador programador, Publicitario publicitario, double custoTotal) {

        if (anuncio != null) {

            double receitaEmpresa = efetuarContasAnuncioEmpresa(custoTotal);

            double receitaProgramador = 0.0;

            for (Conta conta : listaContas) {
                if (conta.getUser().equals(programador)) {

                    receitaProgramador = efetuarContasAnuncioProgramador(custoTotal, conta);

                } else if (conta.getUser().equals(publicitario)) {

                    efetuarContasAnuncioPublicitario(custoTotal, conta);
                }
            }
            anuncio.setReceitaEmpresa(receitaEmpresa);
            anuncio.setReceitaProgramador(receitaProgramador);
            listaAnuncios.add(anuncio);
        }
    }


    /**
     * Calcula o volume de receitas que a empresa gera com a criação daquele anúncio.
     * Chama o método efetuarDeposito da classe Conta.
     *
     * @param custoTotal custo total do anúncio calculado no método verificarAcaoTextFieldQuantidadeExibicoes da classe EcraAddAnuncio.
     * @return retorna as receitas que a empresa gerou com a criação daquele anúncio.
     */
    public double efetuarContasAnuncioEmpresa(double custoTotal) {

        double percentagemEmpresa = Double.parseDouble(dadosConfiguracao.get(10));
        double valor = Double.parseDouble(String.valueOf(new BigDecimal(custoTotal * percentagemEmpresa).setScale(2, RoundingMode.UP)));

        for (Conta elemento : listaContas) {
            if (elemento.getUser().getEmail().equals(emailFundador)) {
                elemento.efetuarDeposito(valor);
            }
        }
        return valor;
    }


    /**
     * Calcula o volume de receitas que o programador gera com a criação daquele anúncio.
     * Chama o método efetuarDeposito da classe Conta.
     *
     * @param custoTotal       custo total do anúncio calculado no método verificarAcaoTextFieldQuantidadeExibicoes da classe EcraAddAnuncio.
     * @param contaProgramador instância de Conta que corresponde à conta do programador.
     * @return retorna as receitas que o programador gerou com a criação daquele anúncio.
     */
    public double efetuarContasAnuncioProgramador(double custoTotal, Conta contaProgramador) {

        double percentagemProgramador = Double.parseDouble(dadosConfiguracao.get(9));

        double valor = Double.parseDouble(String.valueOf(new BigDecimal(custoTotal * percentagemProgramador).setScale(2, RoundingMode.UP)));

        contaProgramador.efetuarDeposito(valor);
        return valor;
    }


    /**
     * Chama o método efetuarPagamento da classe Conta.
     *
     * @param custoTotal        custo total do anúncio calculado no método verificarAcaoTextFieldQuantidadeExibicoes da classe EcraAddAnuncio.
     * @param contaPublicitario instância de Conta que corresponde à conta do publicitário.
     */
    public void efetuarContasAnuncioPublicitario(double custoTotal, Conta contaPublicitario) {
        contaPublicitario.efetuarPagamento(custoTotal);
    }

    /**
     * Escreve a informação que se encontra nas Arraylists listaUtilizadores, listaContas e listaAnuncios no ficheiro
     * de objetos quando se sai do programa.
     */

    public void escreverNoFicheiroGeralASaida() {
        try {
            ficheiroDeObjeto.abreEscrita(ficheiroGeral);

            ficheiroDeObjeto.escreveObjeto(listaUtilizadores);
            ficheiroDeObjeto.escreveObjeto(listaContas);
            ficheiroDeObjeto.escreveObjeto(listaAnuncios);

            ficheiroDeObjeto.fechaEscrita();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica o último idioma escolhido pelo utilizador.
     * Atualiza a lista dadosConfiguracao onde estão armazenados os dados do ficheiro de configiração.
     */
    public void atualizarIdiomaDefault() {
        if (isIdiomaPortugues()) {
            dadosConfiguracao.set(8, String.valueOf(true));
        } else {
            dadosConfiguracao.set(8, String.valueOf(false));
        }
    }


    /**
     * Reescreve o ficheiro de configuração com todas as atualizações de dados realizadas em tempo de execução.
     */
    public void atualizarFicheiroDeConfiguracao() {

        atualizarIdiomaDefault();

        try {
            ficheiroDeTexto.abrirEscrita(dadosConfiguracao.get(7));
            for (String elemento : dadosConfiguracao) {
                ficheiroDeTexto.escreverLinha(elemento);
            }
            ficheiroDeTexto.fecharEscrita();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Atualiza os dados do fundador na lista dadosConfiguracao.
     * É chamado na Classe Configurações, caso o fundador opte em    atualizar/alterar algum dos seus dados pessoais.
     */
    public void atualizarDadosFundador() {

        for (int i = 0; i < dadosConfiguracao.size(); i++) {

            if (i == 0 && !dadosConfiguracao.get(0).equals(listaUtilizadores.get(0).getNome())) {
                dadosConfiguracao.set(0, listaUtilizadores.get(0).getNome());
            }
            if (i == 1 && !dadosConfiguracao.get(1).equals(listaUtilizadores.get(0).getMorada())) {
                dadosConfiguracao.set(1, listaUtilizadores.get(0).getMorada());
            }
            if (i == 2 && !dadosConfiguracao.get(2).equals(listaUtilizadores.get(0).getTelefone())) {
                dadosConfiguracao.set(2, listaUtilizadores.get(0).getTelefone());
            }
            if (i == 4 && !dadosConfiguracao.get(4).equals(listaUtilizadores.get(0).getPassword())) {
                dadosConfiguracao.set(4, listaUtilizadores.get(0).getPassword());
            }
        }
    }
}
