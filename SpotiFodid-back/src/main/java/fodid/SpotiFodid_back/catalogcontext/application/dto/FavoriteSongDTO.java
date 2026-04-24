package fodid.SpotiFodid_back.catalogcontext.application.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record FavoriteSongDTO(@NotNull boolean isFavorite, @NotNull UUID publicId) {

}
