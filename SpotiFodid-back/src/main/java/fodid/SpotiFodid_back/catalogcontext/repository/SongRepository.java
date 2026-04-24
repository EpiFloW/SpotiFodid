package fodid.SpotiFodid_back.catalogcontext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fodid.SpotiFodid_back.catalogcontext.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
