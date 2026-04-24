package fodid.SpotiFodid_back.catalogcontext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fodid.SpotiFodid_back.catalogcontext.domain.Favorite;
import fodid.SpotiFodid_back.catalogcontext.domain.FavoriteId;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId>{

}
