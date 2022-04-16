package segmentation.challenge.challenges;

import static segmentation.challenge.utils.ImageConstants.*;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.awt.Color;

import java.util.Optional;

import segmentation.challenge.bean.Image;
import segmentation.challenge.utils.ImageUtils;

/**
 * Classe para resolver o desafio 1.
 * 
 * Laureta, uma cliente bem exigente, levou uma foto que não está em boa qualidade.
 * É necessário melhorar o brilho e o contraste da Foto.
 * Desenvolva uma rotina que aumente em 50 o brilho e 1,25 o contraste.
 */
public final class Challenge1 implements Challenge {

    /** Fator para modificar o contraste */
    private static final double CONTRAST_FACTOR = 1.25;
    /** Fator para modificar o brilho */
    private static final double BRIGHTNESS_FACTOR = 50;

    /** Construtor do desafio 1 */
    public Challenge1() {}

    @Override
    public void solveChallenge() {
        Optional<Image> optImage = ImageUtils.getImageFromFile(CHALLENGE_1_PATH);
        if (optImage.isEmpty()) {
            return;
        }
        Image image = optImage.get();
        int[][] matrix = image.getMatrix();
        BufferedImage processed = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color c = new Color(matrix[i][j]);
                double red   = CONTRAST_FACTOR * c.getRed()   + BRIGHTNESS_FACTOR;
                red = red > 255 ? 255 : red;
                red = red < 0   ? 0 : red;
                double green = CONTRAST_FACTOR * c.getGreen() + BRIGHTNESS_FACTOR;
                green = green > 255 ? 255 : green;
                green = green < 0   ? 0 : green;
                double blue  = CONTRAST_FACTOR * c.getBlue()  + BRIGHTNESS_FACTOR;
                blue = blue > 255 ? 255 : blue;
                blue = blue < 0   ? 0 : blue;
                processed.setRGB(i, j, new Color((int) red,(int) green, (int) blue).getRGB());
            }
        }
        ImageUtils.writeImage(processed, PROCESSED_IMAGE_PATH);
        ImageUtils.showProcessedImage();
    }

}
