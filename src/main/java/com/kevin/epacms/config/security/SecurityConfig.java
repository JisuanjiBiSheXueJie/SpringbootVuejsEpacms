package com.kevin.epacms.config.security;

import com.kevin.epacms.entity.User;
import com.kevin.epacms.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.annotation.Resource;
import java.util.Objects;

/**
 *
 *
 * @author kevin
 * @since 2022/11/17 21:59
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserService userService;
    @Resource
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/js/**",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/doc.html",
                        "/v2/api-docs-ext/**"
                )
                .permitAll()
                // 对登录注册要允许匿名访问
                .antMatchers("/api/login", "/api/register", "/api/email", "/api/user/export", "/api/user/exist", "/api/upload/**")
                .permitAll()
                // 跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
//                .antMatchers("/**")//测试时全部运行访问
//                .permitAll()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> {
            User user = userService.getUserByUsername(username);
            if (Objects.nonNull(user)) {
//                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
//                return new AdminUserDetails(user,permissionList);
                return user;
            }
            throw new UsernameNotFoundException("用户名不存在");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
