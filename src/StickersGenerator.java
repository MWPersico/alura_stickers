import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickersGenerator {
    public void cria(InputStream inputStream, String fileName) throws Exception {
        // Leitura da imagem:
        // InputStream inputStream = new File("imagem/imagem_local.png")
        // InputStream inputStream = new
        // URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_9.jpg").openStream();
        InputStream gatoStream = new URL("https://i.pinimg.com/474x/cc/f1/86/ccf186597b24a5e1cf2645b7a2043f11.jpg")
                .openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);
        BufferedImage gatoImage = ImageIO.read(gatoStream);

        // Criação de nova imagem em memória com transparência e tamanho novos:
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 256;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Cópia da imagem original para nova (em memória):

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
        graphics.drawImage(gatoImage, 0, newHeight - 256, null);

        // COnfigurar fonte:
        URL fontUrl = new URL("http://db.onlinewebfonts.com/t/7cc6719bd5f0310be3150ba33418e72e.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
        font = font.deriveFont(Font.PLAIN, width / 5);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);

        // Escrever frase na imagem:
        graphics.drawString("TOPZERA", 10, newHeight - 100);

        // Gravar em um arquivo:
        ImageIO.write(newImage, "png", new File("saida/" + fileName + ".png"));
    }
}
