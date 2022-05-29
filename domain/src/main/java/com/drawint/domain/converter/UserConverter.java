package com.drawint.domain.converter;

import com.drawint.domain.bo.UserBO;
import com.drawint.domain.bo.UserRegisterBO;
import com.drawint.domain.dto.UserDTO;
import com.drawint.domain.entity.User;
import com.drawint.domain.entity.UserIdentify;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConverter {
    UserConverter userConverter = Mappers.getMapper(UserConverter.class);

    User userBO2User(UserBO userBO);

    User userDTO2User(UserDTO userDTO);

    UserBO user2UserBO(User user);

    List<UserBO> user2UserBO(List<User> user);

    UserDTO user2UserDTO(User user);

    List<UserDTO> user2UserDTO(List<User> users);

    UserBO userRegisterBO2UserBO(UserRegisterBO userRegisterBO);

    UserIdentify userRegisterBO2UserIdentify(UserRegisterBO userRegisterBO);
}
