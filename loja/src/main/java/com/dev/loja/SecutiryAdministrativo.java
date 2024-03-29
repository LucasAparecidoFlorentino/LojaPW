package com.dev.loja;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecutiryAdministrativo extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	// @Bean
	// public BCryptPasswordEncoder passwordEncoder() {
	// return new BCryptPasswordEncoder();
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * Atenticação de usuários em memória
		 * 
		 * auth.inMemoryAuthentication().withUser("user") .password(new
		 * BCryptPasswordEncoder().encode("123")).roles("USER") .and().withUser("admin")
		 * .password(new BCryptPasswordEncoder() .encode("admin")).roles("USER",
		 * "ADMIN");
		 */

		/* Autenticação de usuários no banco de dados */

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select email as username, senha as password, 1 as enable from funcionario where email=?")
				.authoritiesByUsernameQuery(
						"select funcionario.email as username, papel.nome as authority from permissoes inner join funcionario on funcionario.id=permissoes.funcionario_id inner join papel on permissoes.papel_id=papel.id where funcionario.email=?")
				.passwordEncoder(new BCryptPasswordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/administrativo/cadastrar/**")
				.hasAnyAuthority("Contador").antMatchers("/").authenticated().and().formLogin()
				.loginPage("/login").failureUrl("/login").loginProcessingUrl("/admin")
				.defaultSuccessUrl("/administrativo").usernameParameter("username").passwordParameter("password").and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/administrativo/logout"))
				.logoutSuccessUrl("/login").deleteCookies("JSESSIONID").and().exceptionHandling()
				.accessDeniedPage("/negado").and().csrf().disable();

	}

}
