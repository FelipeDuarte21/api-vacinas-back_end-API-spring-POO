package br.com.uezo.luizfelipeduarteelias.apivacinas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguracao extends WebSecurityConfigurerAdapter {
	
	private static String[] PATH_PUBLICO_POST = {"/usuarios","/login"};
	private static String[] PATH_PUBLICO_GET = {"/campanhas/disponiveis","/postos/localidade","/postos",
		"/vacinas/comentarios","/enderecos/estados","/enderecos/cidades","/enderecos/bairros"};
	
	@Autowired
	private UsuarioDetalheService usuarioDetalheService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,PATH_PUBLICO_POST).permitAll()
		.antMatchers(HttpMethod.GET,PATH_PUBLICO_GET).permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAutenticacaoFiltro(authenticationManager(), this.jwtUtil))
		.addFilter(new JWTAutorizacaoFiltro(authenticationManager(), this.jwtUtil,this.usuarioDetalheService))
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetalheService).passwordEncoder(bCryptPasswordEnconder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addExposedHeader("Authorization");
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**",corsConfiguration.applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
}
