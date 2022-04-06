package segmentation.challenge.challenges;

import segmentation.challenge.window.Processed;

import static segmentation.challenge.utils.ImageConstants.*;

/**
 * Classe para resolver o desafio 3.
 * 
 * Juliano está arrumando o túmulo dos seus pais. 
 * Na lápide, quer colocar as fotos dos mesmos. 
 * Porém, as fotos que tem são velhas e estragadas. 
 * Para este procedimento é necessário ajustar brilho da imagem em 40, o contraste em 1,4 e 
 * suavizar a imagem através do filtro de mediana. 
 */
public final class Challenge3 implements Challenge {

    /** Construtor do desafio 3 */
    public Challenge3() {}

    @Override
    public void solveChallenge() {
        Processed p = new Processed(CHALLENGE_3_PATH);
        p.setVisible(true);
    }

}
