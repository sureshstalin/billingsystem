package com.itgarden;

import com.itgarden.common.staticdata.TokenType;
import com.itgarden.exception.InvalidInputException;
import com.itgarden.exception.InvalidTokenException;
import com.itgarden.repository.JwtTokenRepository;
import com.itgarden.security.JwtUtils;
import com.itgarden.service.bo.AuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Created by Suresh Stalin on 07 / Nov / 2020.
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        final String authHeader = httpServletRequest.getHeader("Authorization");
        String userName = null;
        String accessToken = null;

        try {
            if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
                accessToken = authHeader.substring(7);
                userName = jwtUtils.extractUsername(accessToken,TokenType.ACCESS_TOKEN);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (jwtUtils.isValidToken(accessToken, TokenType.ACCESS_TOKEN)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    System.out.println("Authentication is failed: Can't set authentication in Security Context");
                }
            }
        } catch (ExpiredJwtException e) {
            throw new InvalidInputException(e.getMessage());
        } catch (BadCredentialsException e) {
            throw new InvalidInputException(e.getMessage());
        } catch (Exception ex) {
            throw ex;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        request.setAttribute("claims", ex.getClaims());

    }

    private UserDetails getContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails;
    }


}
