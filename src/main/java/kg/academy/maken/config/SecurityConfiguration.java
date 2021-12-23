package kg.academy.maken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login, password, is_active from users where login=?")
                .authoritiesByUsernameQuery("select u.login, r.name as role from user_roles ur join users u " +
                        "on ur.user_id = u.id join roles r on r.id = ur.role_id where u.login=? " +
                        "and u.is_active=1");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/ign-in").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/user/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/user/search-user").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/user").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/user/update-name").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/user/update-password").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/user/{id}").hasRole("USER")

                .antMatchers(HttpMethod.GET, "api/dashboard").hasRole("USER")
                .antMatchers(HttpMethod.GET, "api/dashboard/pageable").hasRole("USER")
                .antMatchers(HttpMethod.GET, "api/dashboard/{id}").hasRole("USER")
                .antMatchers(HttpMethod.POST, "api/dashboard").hasRole("USER")
                .antMatchers(HttpMethod.POST, "api/dashboard/add-member").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "api/dashboard").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "api/dashboard/{id}").hasRole("USER")

                .antMatchers(HttpMethod.POST, "api/list").hasRole("USER")
                .antMatchers(HttpMethod.GET, "api/list/get-all/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "api/list/get-all").hasRole("USER")
                .antMatchers(HttpMethod.GET, "api/list/get-all/pageable").hasRole("USER")
                .antMatchers(HttpMethod.GET, "api/list/{id}").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "api/list").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "api/list/update-status").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "api/list/update-name").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "api/list/{id}").hasRole("USER")

                .antMatchers(HttpMethod.GET, "api/status").hasRole("USER")


                .antMatchers(HttpMethod.POST, "/api/card").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/card/add-comment").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/card/add-member").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/card/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/get-by-list/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/get-full-card/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/card/get-all").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/card/get-all/pageable").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/card/get-all/move-card").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/card/get-all/reject-task").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/card/get-all/accept-task").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/card/get-all/update-card").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/card/get-all/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/card/get-all/remove-member-card").hasRole("USER")



                .antMatchers(HttpMethod.GET,"/api/member/dashboard/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/api/member/remove-member").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/member/remove-admin").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/member/add-admin").hasRole("USER")

                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
