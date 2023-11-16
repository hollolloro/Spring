package kr.co.ezen.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {

	// ������ ����� Bean���� �����ϱ� ���� Ŭ������ �����ؾ���
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootAppContext.class };
	}

	// MVC�������� Ŭ���� ����
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletAppContext.class };
	}

	// DispatcherServlet�� ������ ��û�ּҸ� ����
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };

	}
	// �Ķ���� ���ڵ� ���� ����

	protected Filter[] getServletFilters() {

		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}

}
