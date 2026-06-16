package com.adhub.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adhub.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	public JwtFilter(JwtService service) {
		this.jwtService = service;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("Jwt Filter Interceptor");
		String header = request.getHeader("Authorization");

		if (header != null && header.contains("Bearer")) {

			String token = header.substring(7);

			System.out.println(token);

			if (!this.jwtService.validateToken(token)) {

				System.out.println("Invalid token");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Invalid token");
				return;
			}

			Claims claims = jwtService.getClaims(token);
			List<String> userRoles = this.getUserRoles(claims);
			List<SimpleGrantedAuthority> userRolesPermissions = List.of();

			if (userRoles.size() > 0) {
				userRolesPermissions = userRoles.stream().map(r -> new SimpleGrantedAuthority(r)).toList();
			}

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					claims.getSubject(), null, userRolesPermissions);

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);

	}

	private List<String> getUserRoles(Claims claims) {
		Object rolesObject = claims.get("roles");
		List<String> roles = List.of();

		if (rolesObject instanceof List<?> rawRoles) {
			roles = rawRoles.stream().map(p -> String.valueOf(p)).toList();
		}

		return roles;

	}
}
