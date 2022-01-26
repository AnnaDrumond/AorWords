package gui;

import programa.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite aos programadores, escolher uma das suas aplicações, para consultar
 * as frases a serem exibidas na aplicação escolhida e a receita atual gerada por esta aplicação.
 * Instancia os componentes da classe.
 */
public class EcraEscolherAppConsulta extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelAppConsulta;
    private final JLabel labelFaixaCinza = new JLabel();
    private final JLabel instrucoes = new JLabel();
    private final JButton voltar = new JButton();
    private final JButton pesquisar = new JButton();
    private final JTable jTableAppConsulta = new JTable();
    private final ArrayList<Aplicacao> appListaAuxiliar = new ArrayList<>(10);


    /**
     * Implementa os botões de EcraEscolherAppConsulta e as suas respetivas ações.
     */
    class BotoesAppConsulta implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesAppConsulta() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                pesquisar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(111)[1]);

            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                pesquisar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(111)[2]);
            }

            voltar.setBounds(410, 650, 120, 45);
            voltar.addActionListener(this);

            pesquisar.setBounds(270, 650, 120, 45);
            pesquisar.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Realiza a pesquisa de frases a exibir, conforme a aplicação escolhida pelo programador.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosAppConsulta referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosAppConsulta) {

            objetoJframe.remove(panelAppConsulta);

            if (eventosAppConsulta.getSource() == voltar) {
                trocaDeIdiomaObjeto.setBufferAuxiliar(null);
                EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosAppConsulta.getSource() == pesquisar) {

                if (trocaDeIdiomaObjeto.getBufferAuxiliar() == null) {
                    avisosPopUp.mensagemDialogo(87, 88);
                    EcraEscolherAppConsulta ecraEscolherAppConsulta = new EcraEscolherAppConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    int contador = 0;

                    for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                        if (anuncio.getAplicacao().getNome().equals(trocaDeIdiomaObjeto.getBufferAuxiliar())) {
                            contador++;
                        }
                    }

                    if (contador > 0) {
                        EcraVerFrasesPorExibir ecraVerFrasesPorExibir = new EcraVerFrasesPorExibir(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                    } else {
                        avisosPopUp.mensagemDialogo(116, 88);
                        trocaDeIdiomaObjeto.setBufferAuxiliar(null);
                        EcraEscolherAppConsulta ecraEscolherAppConsulta = new EcraEscolherAppConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                    }
                }
            }
        }
    }


    /**
     * Implementa os Jlabels de EcraEscolherAppConsulta e as suas respetivas ações.
     */
    class LabelAppConsulta {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelAppConsulta() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(109)[1]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[1]);

            } else {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(109)[2]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[2]);
            }

            labelFaixaCinza.setOpaque(true);
            labelFaixaCinza.setBackground(Color.gray);
            labelFaixaCinza.setForeground(Color.WHITE);
            labelFaixaCinza.setBounds(0, 235, 800, 40);
            labelFaixaCinza.setFont(new Font("Garamond", Font.BOLD, 25));

            instrucoes.setBounds(155, 295, 600, 20);
            instrucoes.setFont(new Font("Garamond", Font.BOLD, 20));
        }
    }


    /**
     * Implementa a Jtable de  EcraEscolherAppConsulta.
     */
    class TabelaAppConsulta {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public TabelaAppConsulta() {
            construirCarregarDadosDaJtable();
            jTableAppConsulta.setBounds(150, 330, 500, 300);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableAppConsulta);
            jScrollPane.setBounds(150, 330, 500, 300);
            return jScrollPane;
        }


        /**
         * Verifica as aplicações do programador com ‘login’ ativo no momento, para poder as exibir na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {
                if (utilizador.equals(empresaObjeto.getUtilizadorAtivo()) && utilizador instanceof Programador) {
                    Programador programador = (Programador) utilizador;
                    for (Aplicacao aplicacao : programador.getListaAplicacoes()) {
                        appListaAuxiliar.add(aplicacao);
                    }
                }
            }

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2]};
            }

            jTableAppConsulta.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel modeloTableAppsGeral = (DefaultTableModel) jTableAppConsulta.getModel();

            Object dadosLinha[] = new Object[vetorColunasTabela.length];

            for (Aplicacao aplicacao : appListaAuxiliar) {
                dadosLinha[0] = aplicacao.getNome();
                modeloTableAppsGeral.addRow(dadosLinha);
            }

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTableAppsGeral);
            jTableAppConsulta.setRowSorter(sorter);
            jTableAppConsulta.setDefaultEditor(Object.class, null);
        }
    }


    /**
     * Implementa a lógica necessária para capturar a linha da tabela clicada com o rato pelo utilizador.
     * Armazena o nome da aplicação, que consta na linha selecionada, para poder posteriormente consultar
     * as frases a serem exibidas na aplicação escolhida.
     */
    public void capturarAppSelecionada() {

        jTableAppConsulta.getSelectionModel().addListSelectionListener(event -> {

            trocaDeIdiomaObjeto.setBufferAuxiliar(jTableAppConsulta.getValueAt(jTableAppConsulta.getSelectedRow(), 0).toString());
        });
    }


    /**
     * Construtor.
     * Instancia o componente JPanel da classe e as suas características.
     * Adiciona os demais componentes da classe ao JPanel. Adiciona o Jpanel ao JFrame.
     * Guarda a referência do ecraAtivo e a referência do painelAtivo através do método ‘setter’ de cada um destes atributos,
     * definidos em EmpresaAorWords.
     * Chama o método capturarAppSelecionada().
     *
     * @param empresaObjeto       instância de EmpresaAorWords criada no método main.
     * @param objetoJframe        instância de JFrame criada em FrameUnica.
     * @param trocaDeIdiomaObjeto instância de TrocaDeIdioma criada em FrameUnica.
     * @param validadorGeral      instância de ValidadorGeral criada em FrameUnica.
     * @param avisosPopUp         instância de AvisosPopUp criada em FrameUnica.
     */
    public EcraEscolherAppConsulta(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;

        panelAppConsulta = new JPanel();
        panelAppConsulta.setBounds(0, 225, 800, 580);
        panelAppConsulta.setLayout(null);
        LabelAppConsulta labelAppConsulta = new LabelAppConsulta();
        BotoesAppConsulta botoesAppConsulta = new BotoesAppConsulta();
        TabelaAppConsulta tabelaAppConsulta = new TabelaAppConsulta();

        panelAppConsulta.add(voltar);
        panelAppConsulta.add(pesquisar);
        panelAppConsulta.add(labelFaixaCinza);
        panelAppConsulta.add(instrucoes);
        panelAppConsulta.add(tabelaAppConsulta.definirScroll());

        panelAppConsulta.setVisible(true);
        this.objetoJframe.add(panelAppConsulta);
        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelAppConsulta);

        capturarAppSelecionada();

        this.objetoJframe.setVisible(true);
    }
}
