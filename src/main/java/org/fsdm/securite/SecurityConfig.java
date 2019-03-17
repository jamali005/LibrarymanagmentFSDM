package org.fsdm.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private DataSource dataSource;
@Override
@Autowired
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal, password as credentails,true from users where username=?")
	.authoritiesByUsernameQuery("select username as principal,type_compte as role from users where username=?").rolePrefix("ROLE_");
	
	
//	auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","USER");
//	auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
	
	}
@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
								.formLogin()
										.loginPage("/login");
		//http.formLogin();
		http.authorizeRequests().antMatchers("/index","/pfelicences","/memoiresmaster","/thesesdoctorats","/consulterLivre").hasAnyRole("ETDS","PROF","ADMIN");
		http.authorizeRequests().antMatchers("/Admin/*","/pfelicences","/memoiresmaster","/thesesdoctorats","/consulterLivre").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	}
}
