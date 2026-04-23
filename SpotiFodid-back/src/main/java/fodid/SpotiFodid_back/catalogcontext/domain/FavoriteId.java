package fodid.SpotiFodid_back.catalogcontext.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FavoriteId implements Serializable {

    private UUID songPublicId;
    private String userEmail;

    public FavoriteId() {
    }

    public FavoriteId(UUID songPublicId, String userEmail) {
        this.songPublicId = songPublicId;
        this.userEmail = userEmail;
    }

    public UUID getSongPublicId() {
        return songPublicId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteId)) return false;
        FavoriteId that = (FavoriteId) o;
        return Objects.equals(getSongPublicId(), that.getSongPublicId()) && Objects.equals(getUserEmail(), that.getUserEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongPublicId(), getUserEmail());
    }
}
