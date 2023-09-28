import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";

        // Requisição HTTP para buscar os filmes:
        ClientHttp clientHttp = new ClientHttp();
        String json = clientHttp.searchData(url);

        // Exibir e manipular os dados:
        var extractor = new NasaContentExtractor();
        List<Content> contentList = extractor.extractContent(json);

        // Gerar figurinhas e exibir dados:
        var generator = new StickersGenerator();

        System.out.printf("\nTamanho da lista: %d\n\n", contentList.size());
        for (Content content : contentList) {
            InputStream inputStream = new URL(content.getImageUrl()).openStream();
            generator.cria(inputStream, content.getTitle());

            System.out.printf("Título: %s\n", content.getTitle());
            System.out.printf("Poster: %s", content.getImageUrl());
            System.out.println("Figurinha .png gerada ;)\n\n");
        }
    }
}
