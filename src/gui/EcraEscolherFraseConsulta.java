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
 * Implementa o ecrã que permite aos publicitários, escolher uma das suas frases, para consultar
 * em que aplicações serão exibidas a frase escolhida e o custo atual gerado pelas campanhas publicitárias
 * desta frase.
 * Instancia os componentes da classe.
 */
public class EcraEscolherFraseConsulta extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelFraseConsulta;
    private final JLabel labelFaixaCinza = new JLabel();
    private final JLabel instrucoes = new JLabel();
    private final JButton voltar = new JButton();
    private final JButton pesquisar = new JButton();
    private final JTable jTableFraseConsulta = new JTable();
    private final ArrayList<Frase> fraseListaAuxiliar = new ArrayList<>(10);


    /**
     * Implementa os botões de EcraEscolherFraseConsulta e as suas respetivas ações.
     */
    class BotoesFraseConsulta implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesFraseConsulta() {

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
         * Realiza a pesquisa das aplicações onde a frase será exibida, conforme a frase escolhida pelo publicitário.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosFraseConsulta referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosFraseConsulta) {

            objetoJframe.remove(panelFraseConsulta);

            if (eventosFraseConsulta.getSource() == pesquisar) {

                if (trocaDeIdiomaObjeto.getBufferAuxiliar() == null) {
                    avisosPopUp.mensagemDialogo(147, 88);
                    EcraEscolherFraseConsulta ecraEscolherFraseConsulta = new EcraEscolherFraseConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {

                    int contador = 0;

                    for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                        if (anuncio.getFraseAnunciada().getFrase().equals(trocaDeIdiomaObjeto.getBufferAuxiliar())) {
                            contador++;
                        }
                    }

                    if (contador > 0) {
                        EcraVerAppsOndeExibidas ecraVerAppsOndeExibidas = new EcraVerAppsOndeExibidas(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                    } else {
                        avisosPopUp.mensagemDialogo(115, 88);
                        trocaDeIdiomaObjeto.setBufferAuxiliar(null);
                        EcraEscolherFraseConsulta ecraEscolherFraseConsulta = new EcraEscolherFraseConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                    }
                }
            } else if (eventosFraseConsulta.getSource() == voltar) {
                trocaDeIdiomaObjeto.setBufferAuxiliar(null);
                EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Implementa os Jlabels de EcraEscolherFraseConsulta e as suas respetivas ações.
     */
    class LabelFraseConsulta {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        protected LabelFraseConsulta() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(112)[1]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[1]);

            } else {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(112)[2]);
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
     * Implementa a Jtable de  EcraEscolherFraseConsulta.
     */
    class TabelaFraseConsulta {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public TabelaFraseConsulta() {
            construirCarregarDadosDaJtable();
            jTableFraseConsulta.setBounds(150, 330, 500, 300);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableFraseConsulta);
            jScrollPane.setBounds(150, 330, 500, 300);
            return jScrollPane;
        }


        /**
         * Verifica as frases do publicitário com ‘login’ ativo no momento, para poder as exibir na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {
                if (utilizador.equals(empresaObjeto.getUtilizadorAtivo()) && utilizador instanceof Publicitario) {
                    Publicitario publicitario = (Publicitario) utilizador;
                    for (Frase frase : publicitario.getListaFrases()) {
                        fraseListaAuxiliar.add(frase);
                    }
                }
            }

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2]};
            }

            jTableFraseConsulta.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel modeloTableFrasesGeral = (DefaultTableModel) jTableFraseConsulta.getModel();

            Object[] dadosLinha = new Object[vetorColunasTabela.length];

            for (Frase frase : fraseListaAuxiliar) {
                dadosLinha[0] = frase.getFrase();
                modeloTableFrasesGeral.addRow(dadosLinha);
            }

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTableFrasesGeral);
            jTableFraseConsulta.setRowSorter(sorter);
            jTableFraseConsulta.setDefaultEditor(Object.class, null);
        }
    }


    /**
     * Implementa a lógica necessária para capturar a linha da tabela clicada com o rato pelo utilizador.
     * Armazena o nome da aplicação, que consta na linha selecionada, para poder posteriormente consultar
     * as frases a serem exibidas na aplicação escolhida.
     */
    public void capturarFraseSelecionada() {

        jTableFraseConsulta.getSelectionModel().addListSelectionListener(event -> {
            trocaDeIdiomaObjeto.setBufferAuxiliar(jTableFraseConsulta.getValueAt(jTableFraseConsulta.getSelectedRow(), 0).toString());
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
    public EcraEscolherFraseConsulta(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;

        panelFraseConsulta = new JPanel();
        panelFraseConsulta.setBounds(0, 225, 800, 580);
        panelFraseConsulta.setLayout(null);

        BotoesFraseConsulta botoesFraseConsulta = new BotoesFraseConsulta();
        LabelFraseConsulta labelFraseConsulta = new LabelFraseConsulta();
        TabelaFraseConsulta tabelaFraseConsulta = new TabelaFraseConsulta();

        panelFraseConsulta.add(voltar);
        panelFraseConsulta.add(pesquisar);
        panelFraseConsulta.add(labelFaixaCinza);
        panelFraseConsulta.add(instrucoes);
        panelFraseConsulta.add(tabelaFraseConsulta.definirScroll());

        panelFraseConsulta.setVisible(true);
        this.objetoJframe.add(panelFraseConsulta);
        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelFraseConsulta);

        capturarFraseSelecionada();

        this.objetoJframe.setVisible(true);
    }
}
