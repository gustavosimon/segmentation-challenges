package segmentation.challenge.challenges;

import segmentation.challenge.window.Processed;

import static segmentation.challenge.utils.ImageConstants.*;

/**
 * Classe para resolver o desafio 1.
 * 
 * Laureta, uma cliente bem exigente, levou uma foto que não está em boa qualidade. 
 * É necessário melhorar o brilho e o contraste da Foto. 
 * Desenvolva uma rotina que aumente em 50 o brilho e 1,25 o contraste.
 */
public final class Challenge1 implements Challenge {

    /** Construtor do desafio 1 */
    public Challenge1() {}

    @Override
    public void solveChallenge() {
        Processed p = new Processed(CHALLENGE_1_PATH);
        p.setVisible(true);
    }

}
