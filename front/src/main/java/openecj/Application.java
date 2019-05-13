package openecj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Component
	public class CustomContainer implements
			WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

		@Override
		public void customize(TomcatServletWebServerFactory factory) {
			factory.setContextPath("");
			factory.setPort(8080);
			factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
			factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
			factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
		}
	}

	/**
	 * 403エラー画面表示
	 *
	 * @return
	 */
	@RequestMapping("/403")
	public String forbiddenError() {
		return "error/403";
	}

	/**
	 * 404エラー画面表示
	 *
	 * @return
	 */
	@RequestMapping("/404")
	public String notFoundError() {
		return "error/404";
	}

	/**
	 * 500エラー画面表示
	 *
	 * @return
	 */
	@RequestMapping("/500")
	public String internalServerError() {
		return "error/500";
	}
}