package gui;

import ficheiros.FicheiroDeTexto;
import programa.EmpresaAorWords;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Instancia um objeto do tipo FicheiroDeTexto.
 * Responsável por ler os dados do Ficheiro de Idiomas.
 * Armazena os dados do Ficheiro de Idiomas em um ArrayList de String[].
 */
public class TrocaDeIdioma {

    protected ArrayList<String[]> idiomasLista;
    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected FicheiroDeTexto ficheiroDeTexto;
    protected String bufferAuxiliar;

    /**
     * Construtor.
     * Instancia o ArrayList de String[] idiomasLista, onde serão armazenados em memória os textos usados na troca de idiomas do Projeto.
     * Chama o método extrairLinhasFicheiroIdiomas().
     *
     * @param empresaObjeto   instância de EmpresaAorWords criada no método main.
     * @param objetoJframe    instância de JFrame criada em FrameUnica.
     * @param ficheiroDeTexto instância de FicheiroDeTexto criada em EmpresaAorWords.
     */

    public TrocaDeIdioma(EmpresaAorWords empresaObjeto, JFrame objetoJframe, FicheiroDeTexto ficheiroDeTexto) {
        this.idiomasLista = new ArrayList<>();
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.ficheiroDeTexto = ficheiroDeTexto;
        extrairLinhasFicheiroIdiomas(empresaObjeto);
    }


    /**
     * Getter do atributo idiomasLista.
     *
     * @return retorna a lista de linhas extraída do ficheiro de idiomas
     */
    public ArrayList<String[]> getIdiomasLista() {
        return idiomasLista;
    }


    /**
     * Getter do atributo bufferAuxiliar.
     * Utilizado para retornar informações armazenadas em memória transitória.
     *
     * @return retorna o nome da aplicação/frase settada na classe EcraEscolherAppConsulta ou EcraEscolherFraseConsulta
     */
    public String getBufferAuxiliar() {
        return bufferAuxiliar;
    }


    /**
     * Setter do atributo bufferAuxiliar
     * Utilizado para modificar informações que serão armazenadas em memória transitória.
     *
     * @param bufferAuxiliar modifica o nome da aplicação/frase
     */
    public void setBufferAuxiliar(String bufferAuxiliar) {
        this.bufferAuxiliar = bufferAuxiliar;
    }


    /**
     * Lê o ficheiro de idiomas e armazena estes dados em um ArrayList de String[], permitindo
     * que os textos do ficheiro sejam usados para troca de idioma do programa.
     *
     * @param empresaObjeto instância de EmpresaAorWords criada no método main.
     */
    public void extrairLinhasFicheiroIdiomas(EmpresaAorWords empresaObjeto) {
        try {

            if (!ficheiroDeTexto.abrirLeitura(empresaObjeto.getDadosConfiguracao().get(6))){
                System.out.println("Idiomas.dat não encontrado.");
                System.exit(1);
            }

            String auxiliarDeLinha;
            String[] auxiliarDeSplit;

            while ((auxiliarDeLinha = ficheiroDeTexto.lerLinha()) != null) {
                auxiliarDeSplit = auxiliarDeLinha.split(";");
                idiomasLista.add(auxiliarDeSplit);
            }
            ficheiroDeTexto.fecharLeitura();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }
}