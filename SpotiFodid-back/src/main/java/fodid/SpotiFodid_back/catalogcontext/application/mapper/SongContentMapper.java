package fodid.SpotiFodid_back.catalogcontext.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fodid.SpotiFodid_back.catalogcontext.application.dto.SaveSongDTO;
import fodid.SpotiFodid_back.catalogcontext.application.dto.SongContentDTO;
import fodid.SpotiFodid_back.catalogcontext.domain.SongContent;

@Mapper(componentModel = "spring")
public interface SongContentMapper {
    @Mapping(source = "song.publicId", target = "publicId")
    SongContentDTO songContentToSongContentDTO(SongContent songContent);

    @Mapping(target = "song", ignore = true)
    @Mapping(target = "songId", ignore = true)
    SongContent saveSongDTOToSong(SaveSongDTO songDTO);
}
