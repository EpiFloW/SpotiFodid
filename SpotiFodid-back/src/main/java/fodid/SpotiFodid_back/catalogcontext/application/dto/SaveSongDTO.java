package fodid.SpotiFodid_back.catalogcontext.application.dto;

import fodid.SpotiFodid_back.catalogcontext.application.vo.SongAuthorVO;
import fodid.SpotiFodid_back.catalogcontext.application.vo.SongTitleVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SaveSongDTO(@Valid SongTitleVO title,
                            @Valid SongAuthorVO author,
                            @NotNull byte[] cover,
                            @NotNull String coverContentType,
                            @NotNull byte[] file,
                            @NotNull String fileContentType) {

}
