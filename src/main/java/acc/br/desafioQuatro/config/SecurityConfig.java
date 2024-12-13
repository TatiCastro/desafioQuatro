package acc.br.desafioQuatro.config;



import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/login", "/public/**").permitAll().requestMatchers("/login", "/public/**").permitAll()  // Allow unauthenticated access to login and public pages
                                .requestMatchers("/admin/**").hasRole("ADMIN")       // Restrict access to /admin/** to ADMIN role only
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // Allow access to /user/** for USER and ADMIN roles
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                    form.loginPage("/login")
                            .defaultSuccessUrl("/studentRegistration", true)
                            .permitAll()
                )
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

