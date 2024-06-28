package com.wjx.config;

// 导入必要的依赖
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity // 启用Web安全支持
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService; // 用户信息服务，用于加载用户详情
    // 不需要这样显示的注入customAuthenticationProvider，因为配置了AuthenticationManager，调用authenticationManager.authenticate
    // 的时候会自动取调CustomUserDetailsService.loadUserByUsername的
    /* @FIXME
    private CustomAuthenticationProvider customAuthenticationProvider;*/
    @Autowired
    private JwtRequestFilter jwtRequestFilter; // JWT请求过滤器，用于处理Token验证

    /**
     * 配置认证管理器，定义密码编码器和用户详情服务
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) // 使用自定义的UserDetailsService
                .passwordEncoder(passwordEncoder()); // 使用BCryptPasswordEncoder进行密码加密
//        auth.authenticationProvider(customAuthenticationProvider);
    }
    /**
     * 配置密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 返回BCrypt密码编码器实例
    }

    /**
     * 配置认证管理器Bean
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); // 返回AuthenticationManager的实例
    }

    /**
     * 定义HTTP安全策略，包括允许无认证访问的路径、认证要求、会话管理策略等
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // 禁用CSRF保护，根据实际情况可能需要调整
                .authorizeRequests() // 开始配置请求授权规则
                .antMatchers("/api/authenticate").permitAll() // 允许所有人访问/authenticate端点
                .anyRequest().authenticated() // 其他所有请求都需要认证
                .and()
                .sessionManagement() // 配置会话管理
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 设置为无状态，适用于JWT

//         添加JWT请求过滤器到过滤链中
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}