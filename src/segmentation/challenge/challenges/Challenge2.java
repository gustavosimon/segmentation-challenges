package segmentation.challenge.challenges;

import segmentation.challenge.bean.Image;
import segmentation.challenge.utils.ImageUtils;

import static segmentation.challenge.utils.ImageConstants.*;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.util.Optional;

/**
 * Classe para resolver o desafio 2.
 * 
 * Armandinho tirou uma foto de seu cachorro para fazer um Banner. 
 * Porém, a foto está cheia de ruídos. 
 * Faça uma rotina que limpe a imagem, eliminando os ruídos e deixando tão bonita que possa ser ampliada e impressa. 
 * Para isso, utilize o filtro Gaussiano. 
 */
public final class Challenge2 implements Challenge {

    /** Máscara para aplicar filtro Gaussiano */
    private static final int[][] mask = {{ 1,  2,  1 },
                                         { 2,  4,  2 },
                                         { 1,  2,  1 }};

    /** Construtor do desafio 2 */
    public Challenge2() {}

    @Override
    public void solveChallenge() {
        Optional<Image> optImage = ImageUtils.getImageFromFile(CHALLENGE_2_PATH);
        if (optImage.isEmpty()) {
            return;
        }
        Image image = optImage.get();
        int[][] matrix = image.getMatrix();
        BufferedImage processed = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int newRed = 0;
        int newGreen = 0;
        int newBlue = 0;
        for (int x = 1; x < image.getWidth() - 1; x++) {
            for (int y = 1; y < image.getHeight() - 1; y++) {
                redSum = 0;
                greenSum = 0;
                blueSum =  0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Color color = new Color(matrix[x + (i - 1)][y + (j - 1)]);
                        redSum   += color.getRed()   * mask[i][j];
                        greenSum += color.getGreen() * mask[i][j];
                        blueSum  += color.getBlue()  * mask[i][j];
                    }
                }
                newRed   = (int) redSum / 16;
                newGreen = (int) greenSum / 16;
                newBlue  = (int) blueSum / 16;
                processed.setRGB(x, y, new Color(newRed, newGreen, newBlue).getRGB());
            }
        }
        ImageUtils.writeImage(processed, PROCESSED_IMAGE_PATH);
        ImageUtils.showProcessedImage();
    }

}
