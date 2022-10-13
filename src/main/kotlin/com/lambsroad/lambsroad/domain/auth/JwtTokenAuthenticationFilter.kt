package com.lambsroad.lambsroad.domain.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException

class JwtTokenAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider): GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    ovveride fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token: String? = jwtTokenProvider.getTokenFromRequestHeader((request as HttpServletRequest))

        if (token != null && jwtTokenProvider.validateToken(token)) {
            val authentication = jwtTokenProvider.getAuthenticationFromToken(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }
}
