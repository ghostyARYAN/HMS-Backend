package com.hms.GatewayMS.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AddResponseHeaderGatewayFilterFactory.Config;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenFilter extends AbstractGatewayFilterFactory<TokenFilter.Config> {
    private static final String SECRET="deac50fc5b0d49204fa5ed757df86f7322548994248ad9cc93174ae59f92e86f4140b1136fd215b0afbc16a3db8b5ed181be8a0ddfe0878b39b2b7343ef51a62";
   

    public TokenFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
       return(exchange, chain) ->{
        String path=exchange.getRequest().getPath().toString();
        if(path.equals("/user/login")|| path.equals("/user/register")) {
            return chain.filter(exchange.mutate().request(r-> r.header("X-Secret-Key", "SECRET")).build());
        }
        HttpHeaders header=exchange.getRequest().getHeaders();
        if(!header.containsKey(HttpHeaders.AUTHORIZATION)){
            throw new RuntimeException("Authorization header is missing");
        }
        String authHeader=header.getFirst(HttpHeaders.AUTHORIZATION);
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Invalid Authorization header");
        }
        String token=authHeader.substring(7);
        try{
            Claims claims = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();
            exchange=exchange.mutate().request(r-> r.header("X-Secret-Key", "SECRET")).build();
        }
        catch(Exception e){
            throw new RuntimeException("Invalid Token");
        }
        return chain.filter(exchange);
            
        
       };
    }
       
       
    public static class Config {
      
    }

    

    
    
}   
