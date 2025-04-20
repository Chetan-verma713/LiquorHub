package com.example.liquorhub.mapper;

import com.example.liquorhub.dto.UserDto;
import com.example.liquorhub.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encryptPassword")
    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> entities);

    default void updateUserFromDto(UserDto dto, User entity, Long id) {
        if (dto == null || entity == null) return;
        if (dto.getId() != null) {
            entity.setId(id);
        }
        if (dto.getAddress() != null) {
            entity.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            entity.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getDateOfBirth() != null) {
            entity.setDateOfBirth(dto.getDateOfBirth());
        }
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getPassword() != null) {
            entity.setPassword(getEncryptedPassword(dto.getPassword()));
        }
    }

    @Named("encryptPassword")
    default String getEncryptedPassword(String password) {
        // Implement your password encryption logic here
        return password; // Placeholder, replace with actual encryption
    }

}
