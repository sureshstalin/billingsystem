package com.itgarden.mapper.impl;

import com.itgarden.dto.AuthenticationResponseInfo;
import com.itgarden.entity.JwtToken;
import com.itgarden.mapper.JwtResponseMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T09:21:20+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class JwtResponseMapperImpl implements JwtResponseMapper {

    @Override
    public JwtToken authResponseToJwt(AuthenticationResponseInfo authenticationResponseDTO) {
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
    public AuthenticationResponseInfo jwtToAuthResponse(JwtToken jwtToken) {
        if ( jwtToken == null ) {
            return null;
        }

        AuthenticationResponseInfo authenticationResponseInfo = new AuthenticationResponseInfo();

        authenticationResponseInfo.setId( jwtToken.getId() );
        authenticationResponseInfo.setAccessToken( jwtToken.getAccessToken() );
        authenticationResponseInfo.setRefreshToken( jwtToken.getRefreshToken() );
        authenticationResponseInfo.setAccessTokenExpiration( jwtToken.getAccessTokenExpiration() );
        authenticationResponseInfo.setRefreshTokenExpiration( jwtToken.getRefreshTokenExpiration() );
        authenticationResponseInfo.setUserName( jwtToken.getUserName() );

        return authenticationResponseInfo;
    }
}
