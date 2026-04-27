package fodid.SpotiFodid_back.catalogcontext.application;

import java.util.List;

import fodid.SpotiFodid_back.catalogcontext.application.dto.YouTubeSearchResultDTO;

public interface YouTubeService {
    List<YouTubeSearchResultDTO> search(String query);
}
