package segmentation.challenge.challenges;

import segmentation.challenge.bean.Image;
import segmentation.challenge.utils.ImageUtils;

import static segmentation.challenge.utils.ImageConstants.*;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.awt.Color;
import java.util.Arrays;
import java.util.Optional;

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

    /** Fator para modificar o contraste */
    private static final double CONTRAST_FACTOR = 1.4;
    /** Fator para modificar o brilho */
    private static final double BRIGHTNESS_FACTOR = 40;

    /** Construtor do desafio 3 */
    public Challenge3() {}

    @Override
    public void solveChallenge() {
        Optional<Image> optImage = ImageUtils.getImageFromFile(CHALLENGE_3_PATH);
        if (optImage.isEmpty()) {
            return;
        }
        Image image = optImage.get();
        int[][] matrix = image.getMatrix();
        BufferedImage processed = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        //
        // Altera o brilho e contraste da imagem
        //
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
                matrix[i][j] = new Color((int) red, (int) green, (int) blue).getRGB();
            }
        }
        //
        // Aplica filtro de mediana para suavizar a imagem
        //
        int a = 0;
        int[] vet  = new int [9];
        for (int i = 1; i < image.getWidth() - 1; i++) {
            for (int j = 1; j < image.getHeight() - 1; j++) {
                a = 0;
                for (int coluna = i - 1; coluna <= i + 1; coluna++) {
                    for (int linha = j - 1; linha <= j + 1; linha++) {
                        vet[a] = matrix[coluna][linha];
                        a++;
                    }
                }
                Arrays.sort(vet);
                processed.setRGB(i, j, new Color(vet[4]).getRGB());
            }
        }
        ImageUtils.writeImage(processed, PROCESSED_IMAGE_PATH);
        ImageUtils.showProcessedImage();
    }
}