package kr.co.ezen.config;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.ezen.beans.User;
import kr.co.ezen.interceptor.LoginInterceptor;
import kr.co.ezen.interceptor.TopInterceptor;
import kr.co.ezen.mapper.BoardMapper;
import kr.co.ezen.mapper.TopMenuMapper;
import kr.co.ezen.mapper.UserMapper;
import kr.co.ezen.service.TopMenuService;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.ezen.controller")
@ComponentScan("kr.co.ezen.dao")
@ComponentScan("kr.co.ezen.service")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {
	
	@Value("${db.classname}")
	private String db_classname;
	
	@Value("${db.url}")
	private String db_url;
	
	@Value("${db.username}")
	private String db_username;
	
	@Value("${db.password}")
	private String db_password;
	
	@Autowired
	private TopMenuService topMenuService;
	
	@Resource(name="loginBean")
	private User loginBean;
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source=new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
	}
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception{
		
		SqlSessionFactoryBean f=new SqlSessionFactoryBean();
		
		f.setDataSource(source);
		SqlSessionFactory fac=f.getObject();
		return fac;
	}
	
	@Bean
	public MapperFactoryBean<BoardMapper> mm(SqlSessionFactory fac) throws
	Exception{
		
		MapperFactoryBean<BoardMapper> m=
				new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		
		m.setSqlSessionFactory(fac);
		return m;
	}
	
	@Bean
	public MapperFactoryBean<TopMenuMapper> mm2(SqlSessionFactory fac) throws
	Exception{
		
		MapperFactoryBean<TopMenuMapper> m2=
				new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
		
		m2.setSqlSessionFactory(fac);
		return m2;
	}
	
	@Bean
	public MapperFactoryBean<UserMapper> mm3(SqlSessionFactory fac) throws
	Exception{
		
		MapperFactoryBean<UserMapper> m2=
				new MapperFactoryBean<UserMapper>(UserMapper.class);
		
		m2.setSqlSessionFactory(fac);
		return m2;
	}
	
	
	public void addInterceptors(InterceptorRegistry re) {
		
		WebMvcConfigurer.super.addInterceptors(re);

		TopInterceptor top=new TopInterceptor(topMenuService,loginBean);
		
		InterceptorRegistration re1=re.addInterceptor(top);
		re1.addPathPatterns("/**");
		//어떠한 주소요청이 오던간에 상단메뉴에 해당하는 1,2,3,4,1팀,2팀,3팀,4팀 뿌려야함
		
		LoginInterceptor lo=new LoginInterceptor(loginBean);
		InterceptorRegistration re2=re.addInterceptor(lo);
		//로그인이 되어있어야 사용자 정보 수정, 로그아웃, 게시글 crud 가능
		re2.addPathPatterns("/user/modify","/user/logout","/board/*");
		re2.excludePathPatterns("/board/main");
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer 
	PropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res=new
				ReloadableResourceBundleMessageSource();
		
		res.setBasename("/WEB-INF/properties/error");
		return res;
	}
	
	
}




