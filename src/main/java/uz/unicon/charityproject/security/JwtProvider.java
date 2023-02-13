package uz.unicon.charityproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.unicon.charityproject.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
   // @Value("${jwt.secretKey}")
    private String secretKey = "mirzabek";
    //@Value("${jwt.expireTime}")
    private final long expire = 86400000;

    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

   public String getUserNameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
   }

    public boolean expireToken(String token) {
        try {

            Date expiration = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
