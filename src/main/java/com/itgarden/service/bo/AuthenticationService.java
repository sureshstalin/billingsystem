package com.itgarden.service.bo;

import com.itgarden.common.Utils;
import com.itgarden.common.staticdata.TokenType;
import com.itgarden.dto.AuthenticationResponseDTO;
import com.itgarden.dto.GrandAuthorityRole;
import com.itgarden.entity.JwtToken;
import com.itgarden.entity.Role;
import com.itgarden.mapper.JwtResponseMapper;
import com.itgarden.repository.JwtTokenRepository;
import com.itgarden.repository.UserRepository;
import com.itgarden.security.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtTokenRepository jwtTokenRepository;

        @Autowired
        private JwtUtils jwtUtils;
        @Autowired
        private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        com.itgarden.entity.User user = userRepository.findByEmailId(emailId);
        GrandAuthorityRole grandAuthorityRoleSuperAdmin = new GrandAuthorityRole("SUPER_ADMIN");
        GrandAuthorityRole grandAuthorityRoleEmployee = new GrandAuthorityRole("ROLE_EMPLOYEE");
        List<GrandAuthorityRole> authorities = new ArrayList<>();
        authorities.add(grandAuthorityRoleSuperAdmin);
        authorities.add(grandAuthorityRoleEmployee);
        UserDetails userDetails = new User(user.getEmailId(), user.getPassword(), authorities);
        return userDetails;
    }

    public JwtToken saveJwt(AuthenticationResponseDTO authenticationResponseDto) {

        JwtToken jwtToken = JwtResponseMapper.INSTANCE.authResponseToJwt(authenticationResponseDto);
        JwtToken jwtTokenResponse = jwtTokenRepository.save(jwtToken);
        return jwtTokenResponse;
    }

    public AuthenticationResponseDTO generateAuthResponse(UserDetails userDetails, TokenType tokenType) {

        JwtToken jwtToken = jwtTokenRepository.findByUserName(userDetails.getUsername());
        AuthenticationResponseDTO authenticationResponseDTO = null;
        if(tokenType.equals(TokenType.ACCESS_TOKEN)) {
            String accessToken = jwtUtils.generateAccessToken(userDetails);
            final Claims accessclaims = jwtUtils.extractAllClaims(accessToken);
            String refreshToken = jwtUtils.generateRefreshToken(userDetails);
            final Claims refreshClaims = jwtUtils.extractAllClaims(refreshToken);
            authenticationResponseDTO = new AuthenticationResponseDTO();
            authenticationResponseDTO.setAccessToken(accessToken);
            authenticationResponseDTO.setRefreshToken(refreshToken);
            authenticationResponseDTO.setUserName(userDetails.getUsername());
            authenticationResponseDTO.setAccessTokenExpiration(Utils.convertToLocalDateTime(accessclaims.getExpiration().getTime()));
            authenticationResponseDTO.setRefreshTokenExpiration(Utils.convertToLocalDateTime(refreshClaims.getExpiration().getTime()));
            if (jwtToken != null) {
                authenticationResponseDTO.setId(jwtToken.getId());
            }
        }
        if(tokenType.equals(TokenType.REFRESH_TOKEN)) {
            String accessToken = jwtUtils.generateAccessToken(userDetails);
            final Claims accessclaims = jwtUtils.extractAllClaims(accessToken);
            authenticationResponseDTO = new AuthenticationResponseDTO();
            authenticationResponseDTO.setAccessToken(accessToken);
            authenticationResponseDTO.setRefreshToken(jwtToken.getRefreshToken());
            authenticationResponseDTO.setUserName(userDetails.getUsername());
            authenticationResponseDTO.setAccessTokenExpiration(Utils.convertToLocalDateTime(accessclaims.getExpiration().getTime()));
            authenticationResponseDTO.setRefreshTokenExpiration(jwtToken.getRefreshTokenExpiration());
            authenticationResponseDTO.setId(jwtToken.getId());
        }

        return authenticationResponseDTO;
    }
}
