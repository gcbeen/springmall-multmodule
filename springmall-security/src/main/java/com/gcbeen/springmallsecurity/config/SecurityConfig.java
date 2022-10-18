package com.gcbeen.springmallsecurity.config;


import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gcbeen.springmallsecurity.component.AccessDecisionProcessor;
import com.gcbeen.springmallsecurity.component.JwtAuthenticationTokenFilter;
import com.gcbeen.springmallsecurity.component.RestAuthenticationEntryPoint;
import com.gcbeen.springmallsecurity.component.RestfulAccessDeniedHandler;
import com.gcbeen.springmallsecurity.util.JwtTokenUtil;

/**
 * 对SpringSecurity的配置的扩展
 * 支持自定义白名单资源路径和查询用户逻辑
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // private IDynamicSecurityService dynamicSecurityService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = httpSecurity.authorizeRequests();
        
        // 静态资源 不需要授权
        // 登录注册 匿名访问
        // 不需要保护的资源 允许访问
        for (String url : ignoreUrlsConfig().getUrls()) {
            urlRegistry.antMatchers(url).permitAll();
        }
        // 跨域请求 先进行一次 options 请求
        // 允许 OPTIONS 请求
        urlRegistry.antMatchers(HttpMethod.OPTIONS).permitAll();
        // 其他 任何请求需要身份认证
        urlRegistry.and().authorizeRequests().anyRequest().authenticated()
        // 使用自定义的 accessDecisionManager
        .accessDecisionManager(accessDecisionManager());
        // 打开Spring Security的跨域
        urlRegistry.and().cors();
        // JWT 不需要 csrf
        // JWT 基于 token 不需要session
        // 关闭跨站请求防护 csrf
        urlRegistry.and().csrf().disable()
        // 不使用 session 关闭Session机制
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 自定义权限拒绝处理类
        // 添加未登录与权限不足异常处理器
        urlRegistry.and().exceptionHandling()
        .accessDeniedHandler(restfulAccessDeniedHandler())
        .authenticationEntryPoint(restAuthenticationEntryPoint());
        // 自定义权限拦截器JWT过滤器
        // 替代UsernamePasswordAuthenticationFilter
        urlRegistry.and().addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService())
    //             .passwordEncoder(passwordEncoder());
    // }

    // Spring Security在认证操作时会使用我们定义的这个加密器
    // 定义加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    // 替代UsernamePasswordAuthenticationFilter
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    // 这个 Bean 的 authenticate 方法会由 Spring Security 自动帮我们做认证
    // 定义 AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public AccessDecisionVoter<FilterInvocation> accessDecisionProcessor() {
        return new AccessDecisionProcessor();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        // 构造一个新的 AccessDecisionManager 放入两个投票器
        // WebExpressionVoter 发现当前请求认证了，就直接投了赞成票
        // 自定义的投票器 拿到当前用户的权限，使其和当前请求所需要的权限做个对比
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(new WebExpressionVoter(), accessDecisionProcessor());
        return new UnanimousBased(decisionVoters);
    }

}
