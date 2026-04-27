package fodid.SpotiFodid_back.catalogcontext.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fodid.SpotiFodid_back.catalogcontext.application.dto.ReadSongInfoDTO;
import fodid.SpotiFodid_back.catalogcontext.application.dto.SaveSongDTO;
import fodid.SpotiFodid_back.catalogcontext.application.vo.SongAuthorVO;
import fodid.SpotiFodid_back.catalogcontext.application.vo.SongTitleVO;
import fodid.SpotiFodid_back.catalogcontext.domain.Song;

/**
 * Mapper pour transformer les DTO en Entités et inversement.
 * L'annotation componentModel = "spring" permet d'injecter ce mapper dans SongService.
 */
@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "title", source = "title") // Utilise la méthode default pour le VO
    @Mapping(target = "author", source = "author") // Utilise la méthode default pour le VO
    Song saveSongDTOToSong(SaveSongDTO saveSongDTO);

    @Mapping(target = "favorite", ignore = true)
    @Mapping(target = "title", source = "title") // Extrait la valeur du VO vers String
    @Mapping(target = "author", source = "author") // Extrait la valeur du VO vers String
    ReadSongInfoDTO songToReadSongInfoDTO(Song song);

    // --- Méthodes de conversion pour les Value Objects ---

    default SongTitleVO stringToSongTitleVO(String title) {
        return (title != null) ? new SongTitleVO(title) : null;
    }

    default SongAuthorVO stringToSongAuthorVO(String author) {
        return (author != null) ? new SongAuthorVO(author) : null;
    }

    default String songTitleVOToString(SongTitleVO title) {
        return (title != null) ? title.value() : null;
    }

    default String songAuthorVOToString(SongAuthorVO author) {
        return (author != null) ? author.value() : null;
    }
}