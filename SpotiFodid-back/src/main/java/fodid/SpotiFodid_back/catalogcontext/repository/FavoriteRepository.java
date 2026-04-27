package fodid.SpotiFodid_back.catalogcontext.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fodid.SpotiFodid_back.catalogcontext.domain.Favorite;
import fodid.SpotiFodid_back.catalogcontext.domain.FavoriteId;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId>{
    List<Favorite> findAllByUserEmailAndSongPublicIdIn(String email, List<UUID> songPublicIds);
}
