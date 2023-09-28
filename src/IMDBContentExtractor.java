import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDBContentExtractor implements contentExtractor {
    public List<Content> extractContent(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);
        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> attributes : attributeList) {
            String title = "IMDB-"+attributes.get("title")
                    .replaceAll("[\\/|?<>*:\"“]", "")
                    .replace(" ", "_");
            String image = attributes.get("image")
                    .replaceAll("(@+)(.*).jpg$", "$l.jpg");
            ;
            Content content = new Content(title, image);

            contentList.add(content);
        }

        return contentList;
    }
}
