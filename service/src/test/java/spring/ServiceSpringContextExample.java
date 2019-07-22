package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.yi.cms.service.ICssItemService;
import com.itacademy.jd2.yi.cms.service.IPageService;
//import com.itacademy.jd2.yi.cms.service.ICssItemService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;

public class ServiceSpringContextExample {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		System.out.println(context.getBean(IUserAccountService.class));
		System.out.println(context.getBean(ITemplateService.class));
		System.out.println(context.getBean(ISiteService.class));
		System.out.println(context.getBean(ICssItemService.class));
		System.out.println(context.getBean(IPageService.class));

//TODO show multiple candidates with Qualifier

	}
}