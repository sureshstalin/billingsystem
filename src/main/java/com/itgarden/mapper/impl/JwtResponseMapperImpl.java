package com.itgarden.mapper.impl;

import com.itgarden.dto.AuthenticationResponseDTO;
import com.itgarden.entity.JwtToken;
import com.itgarden.mapper.JwtResponseMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-11T09:47:20+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class JwtResponseMapperImpl implements JwtResponseMapper {

    @Override
    public JwtToken authResponseToJwt(AuthenticationResponseDTO authenticationResponseDTO) {
        if ( authenticationResponseDTO == null ) {
            return null;
        }

        JwtToken jwtToken = new JwtToken();

        jwtToken.setId( authenticationResponseDTO.getId() );
        jwtToken.setAccessToken( authenticationResponseDTO.getAccessToken() );
        jwtToken.setRefreshToken( authenticationResponseDTO.getRefreshToken() );
        jwtToken.setUserName( authenticationResponseDTO.getUserName() );
        jwtToken.setAccessTokenExpiration( authenticationResponseDTO.getAccessTokenExpiration() );
        jwtToken.setRefreshTokenExpiration( authenticationResponseDTO.getRefreshTokenExpiration() );

        return jwtToken;
    }

    @Override
    public AuthenticationResponseDTO jwtToAuthResponse(JwtToken jwtToken) {
        if ( jwtToken == null ) {
            return null;
        }

        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();

        authenticationResponseDTO.setId( jwtToken.getId() );
        authenticationResponseDTO.setAccessToken( jwtToken.getAccessToken() );
        authenticationResponseDTO.setRefreshToken( jwtToken.getRefreshToken() );
        authenticationResponseDTO.setAccessTokenExpiration( jwtToken.getAccessTokenExpiration() );
        authenticationResponseDTO.setRefreshTokenExpiration( jwtToken.getRefreshTokenExpiration() );
        authenticationResponseDTO.setUserName( jwtToken.getUserName() );

        return authenticationResponseDTO;
    }
}
