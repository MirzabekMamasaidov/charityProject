/*package uz.unicon.charityproject.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.unicon.charityproject.service.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    JwtProvider jwtProvider;
    AuthService authService;

    @Autowired
    public JwtFilter(AuthService authService, JwtProvider jwtProvider) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
       token = token.substring(7);
        //validate expired kimga tegishli
        if (jwtProvider.validateToken(token)) {
            if (jwtProvider.expireToken(token)) {
                String username = jwtProvider.getUserNameFromToken(token);
                if (username!=null){
                UserDetails userDetails = authService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
            }}

        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

      doFilter(request, Objects.requireNonNull(response),filterChain);

    }
}*/
package uz.unicon.charityproject.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.unicon.charityproject.service.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(token!= null && token.startsWith("Bearer"))
            token = token.substring(7);

        if (jwtProvider.validateToken(token)) {
            if (jwtProvider.expireToken(token)) {
                //username oldi tokendan
                String userName = jwtProvider.getUserNameFromToken(token);
                System.out.println(token);
                System.out.println(userName);

                UserDetails userDetails = authService.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(), userDetails.getAuthorities());

                System.out.println(user);
                //tizimga kirgan odamni security o'zi un saqlab turibti
                SecurityContextHolder.getContext().setAuthentication(user);

                System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            }
        }
        //http zanjiri
        doFilter(request, response, filterChain);
    }
}
