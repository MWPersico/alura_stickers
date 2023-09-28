import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements contentExtractor {
    public List<Content> extractContent(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);
        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> attributes : attributeList) {
            String title = "NASA-"+attributes.get("title")
            .replaceAll("[\\/|?<>*:\"“]", "")
            .replace(" ", "_");
            String imageUrl = attributes.get("url");
            Content content = new Content(title, imageUrl);

            contentList.add(content);
        }

        return contentList;
    }
}
