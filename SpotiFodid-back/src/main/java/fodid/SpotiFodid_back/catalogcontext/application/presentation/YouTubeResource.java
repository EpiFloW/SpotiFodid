package fodid.SpotiFodid_back.catalogcontext.application.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fodid.SpotiFodid_back.catalogcontext.application.YouTubeService;
import fodid.SpotiFodid_back.catalogcontext.application.dto.YouTubeSearchResultDTO;

@RestController
@RequestMapping("/api/youtube")
public class YouTubeResource {

    private final YouTubeService youtubeService;

    public YouTubeResource(YouTubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<YouTubeSearchResultDTO>> search(@RequestParam("q") String query) {
        List<YouTubeSearchResultDTO> results = youtubeService.search(query);
        return ResponseEntity.ok(results);
    }
}