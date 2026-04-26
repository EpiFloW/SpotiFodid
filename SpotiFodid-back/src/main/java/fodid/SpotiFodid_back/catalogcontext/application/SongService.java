package fodid.SpotiFodid_back.catalogcontext.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fodid.SpotiFodid_back.catalogcontext.application.dto.ReadSongInfoDTO;
import fodid.SpotiFodid_back.catalogcontext.application.dto.SaveSongDTO;
import fodid.SpotiFodid_back.catalogcontext.application.dto.SongContentDTO;
import fodid.SpotiFodid_back.catalogcontext.application.mapper.SongContentMapper;
import fodid.SpotiFodid_back.catalogcontext.application.mapper.SongMapper;
import fodid.SpotiFodid_back.catalogcontext.domain.Song;
import fodid.SpotiFodid_back.catalogcontext.domain.SongContent;
import fodid.SpotiFodid_back.catalogcontext.repository.SongContentRepository;
import fodid.SpotiFodid_back.catalogcontext.repository.SongRepository;

@Service
@Transactional
public class SongService {

    private final SongMapper songMapper;

    private final SongRepository songRepository;

    private final SongContentRepository songContentRepository;

    private final SongContentMapper songContentMapper;

//    private final UserService userService;

    public SongService(SongMapper songMapper, SongRepository songRepository,
                       SongContentRepository songContentRepository, SongContentMapper songContentMapper) {
        this.songMapper = songMapper;
        this.songRepository = songRepository;
        this.songContentRepository = songContentRepository;
        this.songContentMapper = songContentMapper;
        //this.userService = userService;
        //this.favoriteRepository = favoriteRepository;
    }

    public ReadSongInfoDTO create(SaveSongDTO saveSongDTO) {
        Song song = songMapper.saveSongDTOToSong(saveSongDTO);
        Song songSaved = songRepository.save(song);

        SongContent songContent = songContentMapper.saveSongDTOToSong(saveSongDTO);
        songContent.setSong(songSaved);

        songContentRepository.save(songContent);
        return songMapper.songToReadSongInfoDTO(songSaved);
    }

    public Optional<SongContentDTO> getOneByPublicId(UUID publicId) {
        Optional<SongContent> songByPublicId = songContentRepository.findOneBySongPublicId(publicId);
        return songByPublicId.map(songContentMapper::songContentToSongContentDTO);
    }
}