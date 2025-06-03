@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails librarian = User.withUsername("librarian")
            .password(encoder.encode("lib123"))
            .roles("LIBRARIAN")
            .build();
        return new InMemoryUserDetailsManager(librarian);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
                .anyRequest().hasRole("LIBRARIAN")
            .and().httpBasic();
        return http.build();
    }
}
