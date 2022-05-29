package com.drawint.domain.converter;

import com.drawint.domain.bo.UserBO;
import com.drawint.domain.bo.UserRegisterBO;
import com.drawint.domain.dto.UserDTO;
import com.drawint.domain.entity.User;
import com.drawint.domain.entity.UserIdentify;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-29T14:49:47+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public User userBO2User(UserBO userBO) {
        if ( userBO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userBO.getId() );
        user.setName( userBO.getName() );
        user.setGender( userBO.getGender() );

        return user;
    }

    @Override
    public User userDTO2User(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setGender( userDTO.getGender() );

        return user;
    }

    @Override
    public UserBO user2UserBO(User user) {
        if ( user == null ) {
            return null;
        }

        UserBO userBO = new UserBO();

        userBO.setId( user.getId() );
        userBO.setName( user.getName() );
        userBO.setGender( user.getGender() );

        return userBO;
    }

    @Override
    public List<UserBO> user2UserBO(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserBO> list = new ArrayList<UserBO>( user.size() );
        for ( User user1 : user ) {
            list.add( user2UserBO( user1 ) );
        }

        return list;
    }

    @Override
    public UserDTO user2UserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setGender( user.getGender() );

        return userDTO;
    }

    @Override
    public List<UserDTO> user2UserDTO(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( user2UserDTO( user ) );
        }

        return list;
    }

    @Override
    public UserBO userRegisterBO2UserBO(UserRegisterBO userRegisterBO) {
        if ( userRegisterBO == null ) {
            return null;
        }

        UserBO userBO = new UserBO();

        userBO.setName( userRegisterBO.getName() );
        userBO.setGender( userRegisterBO.getGender() );

        return userBO;
    }

    @Override
    public UserIdentify userRegisterBO2UserIdentify(UserRegisterBO userRegisterBO) {
        if ( userRegisterBO == null ) {
            return null;
        }

        UserIdentify userIdentify = new UserIdentify();

        userIdentify.setAccount( userRegisterBO.getAccount() );
        userIdentify.setPassword( userRegisterBO.getPassword() );
        userIdentify.setAccountType( userRegisterBO.getAccountType() );

        return userIdentify;
    }
}
