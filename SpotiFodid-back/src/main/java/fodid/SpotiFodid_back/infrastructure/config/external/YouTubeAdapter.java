package fodid.SpotiFodid_back.infrastructure.config.external;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import fodid.SpotiFodid_back.catalogcontext.application.YouTubeService;
import fodid.SpotiFodid_back.catalogcontext.application.dto.YouTubeSearchResultDTO;

@Service
public class YouTubeAdapter implements YouTubeService {

    private final String apiKey;
    private final RestTemplate restTemplate;

    public YouTubeAdapter(@Value("${application.youtube.api-key:UNSET}") String apiKey) {
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
        System.out.println("DEBUG YOUTUBE API KEY: [" + this.apiKey + "]");
    }

    @Override
    public List<YouTubeSearchResultDTO> search(String query) {
        if ("UNSET".equals(apiKey) || query == null || query.isBlank()) {
            return List.of();
        }

        // Construction sécurisée de l'URL
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/youtube/v3/search")
                .queryParam("part", "snippet")
                .queryParam("maxResults", "10")
                .queryParam("q", query)
                .queryParam("type", "video")
                .queryParam("key", apiKey)
                .toUriString();

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("items")) {
                return List.of();
            }

            List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

            return items.stream().map(item -> {
                Map<String, Object> idMap = (Map<String, Object>) item.get("id");
                String videoId = (String) idMap.get("videoId");

                Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
                String title = (String) snippet.get("title");

                Map<String, Object> thumbnails = (Map<String, Object>) snippet.get("thumbnails");
                Map<String, Object> defaultThumb = (Map<String, Object>) thumbnails.get("default");
                String thumbnailUrl = (String) defaultThumb.get("url");

                return new YouTubeSearchResultDTO(videoId, title, thumbnailUrl);
            }).collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel YouTube : " + e.getMessage());
            return List.of();
        }
    }
}
