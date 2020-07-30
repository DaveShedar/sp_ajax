package ddd.Spring_Boot_CRUD.config;



import ddd.Spring_Boot_CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    SuccessUserHandler successUserHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/").permitAll() // доступность всем
                    .antMatchers("/user").access("hasAnyRole('ROLE_USER')") // разрешаем входить на /user пользователям с ролью User
                    .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .successHandler(successUserHandler) // подключаем наш SuccessHandler для перенеправления по ролям
                    .and()
                .logout()
                    .permitAll()  // разрешаем делать логаут всем
                    .logoutUrl("/logout") // указываем URL логаута
                    .logoutSuccessUrl("/login?logout") // указываем URL при удачном логауте
                    .invalidateHttpSession(true); // делаем не валидной текущую сессию
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
