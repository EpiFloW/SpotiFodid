package fodid.SpotiFodid_back.catalogcontext.application.dto;

import java.util.UUID;

import jakarta.persistence.Lob;

public record SongContentDTO(UUID publicId, @Lob byte[] file, String fileContentType) {
}
