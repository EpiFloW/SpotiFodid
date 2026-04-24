package fodid.SpotiFodid_back.catalogcontext.application.dto;

import java.util.UUID;

import fodid.SpotiFodid_back.catalogcontext.application.vo.SongAuthorVO;
import fodid.SpotiFodid_back.catalogcontext.application.vo.SongTitleVO;
import jakarta.validation.constraints.NotNull;

public class ReadSongInfoDTO {

    private SongTitleVO title;

    private SongAuthorVO author;

    @NotNull
    private byte[] cover;

    @NotNull
    private String coverContentString;

    @NotNull
    private boolean isFavorite;

    @NotNull
    private UUID publicId;

    public UUID getPublicId() {
        return publicId;
    }

    public SongTitleVO getTitle() {
        return title;
    }

    public SongAuthorVO getAuthor() {
        return author;
    }

    public byte[] getCover() {
        return cover;
    }

    public String getCoverContentString() {
        return coverContentString;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public void setTitle(SongTitleVO title) {
        this.title = title;
    }

    public void setAuthor(SongAuthorVO author) {
        this.author = author;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public void setCoverContentString(String coverContentString) {
        this.coverContentString = coverContentString;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

}
