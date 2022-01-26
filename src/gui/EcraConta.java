package gui;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã de visualização da conta do utilizador,
 * consoante o tipo de utilizador.
 * Instancia os JButton e JLabel respetivos da classe
 */
public class EcraConta extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelConta;
    private final JButton depositar = new JButton();
    private final JButton levantar = new JButton();
    private final JButton historico = new JButton();
    private final JButton voltar = new JButton();
    private final JLabel labelContaUser = new JLabel();
    private final JLabel labelSaldo = new JLabel();
    private JButton todosMov = new JButton();


    /**
     * Implementa os botões de EcraConta para as ações de levantamento e depósito de valores,
     * bem como para visualização de movimentos financeiros, consoante a instância do utilizador ativo.
     * Verifica se o utilizador ativo é o fundador, fazendo o controlo de que somente o fundador
     * poderá levantar valores da conta da empresa.
     */
    class BotoesConta implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesConta() {

            if (empresaObjeto.isIdiomaPortugues()) {
                depositar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(44)[1]);
                levantar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(45)[1]);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    historico.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(128)[1]);

                } else {
                    historico.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(46)[1]);
                }
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                todosMov.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(125)[1]);

            } else {

                depositar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(44)[2]);
                levantar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(45)[2]);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    historico.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(128)[2]);

                } else {
                    historico.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(46)[2]);
                }

                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                todosMov.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(125)[2]);
            }

            if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {

                depositar.setBounds(90, 450, 200, 100);
                depositar.addActionListener(this);

                levantar.setBounds(300, 450, 200, 100);
                levantar.addActionListener(this);

                historico.setBounds(510, 450, 200, 100);
                historico.addActionListener(this);

            } else if (empresaObjeto.getUtilizadorAtivo().getEmail().equals(empresaObjeto.getEmailFundador())) {

                levantar.setBounds(90, 450, 200, 100);
                levantar.addActionListener(this);

                historico.setBounds(300, 450, 200, 100);
                historico.addActionListener(this);

                todosMov.setBounds(510, 450, 200, 100);
                todosMov.addActionListener(this);

            } else if (!empresaObjeto.getUtilizadorAtivo().getEmail().equals(empresaObjeto.getEmailFundador()) && empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {

                historico.setBounds(190, 450, 200, 100);
                historico.addActionListener(this);

                todosMov.setBounds(410, 450, 200, 100);
                todosMov.addActionListener(this);

            } else if (empresaObjeto.getUtilizadorAtivo() instanceof Programador) {

                levantar.setBounds(190, 450, 200, 100);
                levantar.addActionListener(this);

                historico.setBounds(410, 450, 200, 100);
                historico.addActionListener(this);

            }

            voltar.setBounds(340, 600, 120, 50);
            voltar.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Chama os métodos efetuarLevantamento() e efetuarDeposito() declarados em Conta.
         * Chama o método validarValorUnitario() declarado em ValidadorGeral.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         * Verifica e cumpre o requisito do Projeto: "Cada utilizador poderá: consultar a sua conta corrente;
         * depositar/levantar dinheiro da sua conta corrente. O levantamento
         * também só será possível se existir saldo disponível".
         *
         * @param eventosEcraConta referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraConta) {

            objetoJframe.remove(panelConta);
            String valor;

            if (eventosEcraConta.getSource() == depositar) {
                double deposito;
                boolean validar;

                valor = avisosPopUp.inputDialog(95);

                if (valor != null) {
                    validar = validadorGeral.validarValorUnitario(valor);

                    if (validar) {
                        valor = valor.replaceAll(",", ".");
                        deposito = Double.parseDouble(valor);

                        for (Conta conta : empresaObjeto.getListaContas()) {
                            if (conta.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {

                                conta.efetuarDeposito(deposito);
                                EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                            }
                        }

                    } else {
                        avisosPopUp.mensagemDialogo(137, 54);
                        EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                    }

                } else {
                    EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraConta.getSource() == levantar) {

                double levantamento;
                boolean validar;

                valor = avisosPopUp.inputDialog(96);

                if (valor != null) {
                    validar = validadorGeral.validarValorUnitario(valor);

                    if (validar) {

                        valor = valor.replaceAll(",", ".");
                        levantamento = Double.parseDouble(valor);

                        for (Conta conta : empresaObjeto.getListaContas()) {
                            if (conta.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {

                                if (conta.getSaldo() >= levantamento) {
                                    conta.efetuarLevantamento(levantamento);

                                } else {
                                    avisosPopUp.mensagemDialogo(132, 54);
                                }
                                EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                            }
                        }
                    } else {
                        avisosPopUp.mensagemDialogo(133, 54);
                        EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                    }

                } else {
                    EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraConta.getSource() == historico) {
                EcraMovimentosFinanceiros ecraMovimentosFinanceiros = new EcraMovimentosFinanceiros(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraConta.getSource() == todosMov) {
                EcraMovimentosUsers ecraMovimentosUsers = new EcraMovimentosUsers(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraConta.getSource() == voltar) {

                objetoJframe.remove(panelConta);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Programador) {
                    EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }
            }
        }
    }


    /**
     * Verifica a instância do utilizador ativo, para então buscar o saldo do referido utilizador.
     *
     * @return retorna o saldo verificado.
     */
    public String carregarSaldoAtualdaConta() {
        String saldo = "";

        if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {

            for (Conta elemento : empresaObjeto.getListaContas()) {
                if (elemento.getUser().getEmail().equals(empresaObjeto.getEmailFundador())) {
                    saldo = String.valueOf(BigDecimal.valueOf(elemento.getSaldo()).setScale(2, RoundingMode.UP));
                }
            }

        } else {
            for (Conta elemento : empresaObjeto.getListaContas()) {
                if (elemento.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {
                    saldo = String.valueOf(BigDecimal.valueOf(elemento.getSaldo()).setScale(2, RoundingMode.UP));
                }
            }
        }
        return saldo;
    }


    /**
     * Implementa a Jlabel de EcraConta
     */
    class LabelConta {


        /**
         * Construtor.
         * Implementa a JLabel de EcraConta e suas características de acordo com o idioma escolhido.
         */
        public LabelConta() {

            String saldo = carregarSaldoAtualdaConta();

            if (empresaObjeto.isIdiomaPortugues()) {
                labelContaUser.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(48)[1]);
                labelSaldo.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(47)[1]));
            } else {
                labelContaUser.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(48)[2]);
                labelSaldo.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(47)[2]));
            }

            labelSaldo.setText("        " + saldo + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1]);

            labelContaUser.setOpaque(true);
            labelContaUser.setBackground(Color.gray);
            labelContaUser.setForeground(Color.WHITE);
            labelContaUser.setBounds(0, 235, 800, 40);
            labelContaUser.setFont(new Font("Garamond", Font.BOLD, 25));

            labelSaldo.setForeground(Color.BLACK);
            labelSaldo.setBounds(300, 320, 200, 50);
            labelSaldo.setFont(new Font("Garamond", Font.BOLD, 20));
        }
    }


    /**
     * Construtor.
     * Instancia o componente JPanel da classe e as suas características.
     * Adiciona os demais componentes da classe ao JPanel. Adiciona o Jpanel ao JFrame.
     * Guarda a referência do ecraAtivo e a referência do painelAtivo através do método ‘setter’ de cada um destes atributos,
     * definidos em EmpresaAorWords.
     * Adiciona componentes ao Jpanel conforme a instância do utilizador ativo.
     *
     * @param empresaObjeto       instância de EmpresaAorWords criada no método main.
     * @param objetoJframe        instância de JFrame criada em FrameUnica.
     * @param trocaDeIdiomaObjeto instância de TrocaDeIdioma criada em FrameUnica.
     * @param validadorGeral      instância de ValidadorGeral criada em FrameUnica.
     * @param avisosPopUp         instância de AvisosPopUp criada em FrameUnica.
     */
    public EcraConta(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;

        BotoesConta botoesConta = new BotoesConta();
        LabelConta labelConta = new LabelConta();

        panelConta = new JPanel();
        panelConta.setBounds(0, 225, 800, 580);
        panelConta.setLayout(null);
        panelConta.add(labelContaUser);
        panelConta.add(labelSaldo);
        panelConta.add(historico);

        if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
            panelConta.add(levantar);
            panelConta.add(depositar);

        } else if (empresaObjeto.getUtilizadorAtivo() instanceof Programador) {
            panelConta.add(levantar);

        } else if (empresaObjeto.getUtilizadorAtivo().getEmail().equals(empresaObjeto.getEmailFundador())) {
            panelConta.add(levantar);
            panelConta.add(depositar);
            panelConta.add(todosMov);

        } else {
            panelConta.add(todosMov);
        }

        panelConta.add(voltar);
        panelConta.setVisible(true);
        objetoJframe.add(panelConta);
        empresaObjeto.setEcraAtivo(this);
        empresaObjeto.setPainelAtivo(panelConta);
        objetoJframe.setVisible(true);
    }
}