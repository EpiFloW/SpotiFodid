package fodid.SpotiFodid_back.catalogcontext.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fodid.SpotiFodid_back.catalogcontext.application.dto.ReadSongInfoDTO;
import fodid.SpotiFodid_back.catalogcontext.application.dto.SaveSongDTO;
import fodid.SpotiFodid_back.catalogcontext.application.vo.SongAuthorVO;
import fodid.SpotiFodid_back.catalogcontext.application.vo.SongTitleVO;
import fodid.SpotiFodid_back.catalogcontext.domain.Song;

@Mapper(componentModel = "spring")
public interface SongMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    Song saveSongDTOToSong(SaveSongDTO saveSongDTO);

    @Mapping(target = "favorite", ignore = true)
    ReadSongInfoDTO songToReadSongInfoDTO(Song song);

    default SongTitleVO stringToSongTitleVO(String title){
        return new SongTitleVO(title);
    }

    default SongAuthorVO stringToSongAuthorVO(String author){
        return new SongAuthorVO(author);
    }

    default String songTitleVOToString(SongTitleVO title) {
        return title.value();
    }

    default String songAuthorVOToString(SongAuthorVO author) {
        return author.value();
    }
}
