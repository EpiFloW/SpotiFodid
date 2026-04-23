package usercontext.mapper;

import org.mapstruct.Mapper;

import usercontext.ReadUserDTO;
import usercontext.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDTO readUserDTO(User user);
}
