package segmentation.challenge.challenges;

import segmentation.challenge.window.Processed;

import static segmentation.challenge.utils.ImageConstants.*;

/**
 * Classe para resolver o desafio 2.
 * 
 * Armandinho tirou uma foto de seu cachorro para fazer um Banner. 
 * Porém, a foto está cheia de ruídos. 
 * Faça uma rotina que limpe a imagem, eliminando os ruídos e deixando tão bonita que possa ser ampliada e impressa. 
 * Para isso, utilize o filtro Gaussiano. 
 */
public final class Challenge2 implements Challenge {

    /** Construtor do desafio 2 */
    public Challenge2() {}

    @Override
    public void solveChallenge() {
        Processed p = new Processed(CHALLENGE_2_PATH);
        p.setVisible(true);
    }

}
