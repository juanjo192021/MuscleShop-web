package com.muscleshop.web.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class Security {

	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEn() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/index/**", "usuario/carrito/finalizarCompra", "/ejemplo/**", "usuario/pago","usuario/validation",
						"usuario/boleta", "/registrar", "usuario/listaRegion", "/usuario/listaProvincia",
						"usuario/listaDistrito", "/cambiar-contrasena").permitAll()
				.requestMatchers("/index/porProducto").hasAnyAuthority("ROLE_CLIENTE", "ROLE_ADMIN").anyRequest().authenticated())
				.formLogin((login) -> login.loginPage("/login").permitAll().defaultSuccessUrl("/index/inicio", true)
						.failureUrl("/login?error=true"))
				.logout((logout) -> logout.logoutUrl("/logout").permitAll().logoutSuccessUrl("/index/inicio"));

		return http.build();
	}

/*	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return  http
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/index/**", "/login").permitAll();
					auth.requestMatchers("/index/porProducto").hasAnyAuthority("ROLE_CLIENTE", "ROLE_ADMIN").anyRequest().authenticated();
				})
				.oauth2Login(oauth2login ->{
					oauth2login
							.loginPage("/login")
							.successHandler((request, response, authentication) -> response.sendRedirect("/index/inicio"));
				})
				.formLogin((login) -> login.loginPage("/login").permitAll().defaultSuccessUrl("/index/inicio", true)
						.failureUrl("/login?error=true"))
				.logout((logout) -> logout.logoutUrl("/logout").permitAll().logoutSuccessUrl("/index/inicio"))
				.build();
	}*/

	@Bean
	WebSecurityCustomizer webCus() {
		return (web) -> web.ignoring().requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://104.218.48.244:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
