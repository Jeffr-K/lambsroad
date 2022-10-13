package com.lambsroad.lambsroad.domain.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(private val userDetailsService: UserDetailsService) {
    private var secretKey = "heyMan"
    private val expiredTime = 30 * 30 * 1000L
    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString((secretKey.toByteArray()))
    }

    fun createAccessToken(userId: String): String? {
        val claims: Claims = Jwts.claims().setSubject(userId)
        claims["userId"] = userId
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("type", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + expiredTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getAuthenticationFromToken(token: String): UsernamePasswordAuthenticationToken {
        val userDetails = userDetailsService.loadUserByUsername(getUserInfoFromToken(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUserInfoFromToken(token: String): String {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun getTokenFromRequestHeader(request: HttpServletRequest): String? {
        return request.getHader("Authorization")
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: java.lang.Exception) {
            false
        }
    }
}
