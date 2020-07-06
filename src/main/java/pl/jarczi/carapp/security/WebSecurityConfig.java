package pl.jarczi.carapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jarczi.carapp.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/cars/*").authenticated()
                .antMatchers(HttpMethod.POST, "/cars/carService").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cars/carService").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/cars/carService").hasAnyRole("ADMIN", "MODERATOR")
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/cars/getAll")
                .and()
                .logout().permitAll();

    }
}
