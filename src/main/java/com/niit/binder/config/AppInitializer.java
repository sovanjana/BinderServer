/*package com.niit.binder.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml - Java based configuration...

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	Logger log = Logger.getLogger(AppInitializer.class);
	
	@Override
	protected Class[] getRootConfigClasses() {
		log.debug("Entered in getRootConfigClasses method............");
		return new Class[] {AppConfig.class};
	}
	
	@Override
	protected Class[] getServletConfigClasses() {
		log.debug("Entered in getServletConfigClasses method............");
		return null;
	}
	
	@Override
	protected String[] getServletMappings() {
		log.debug("Entered in getServletMappings method............");
		return new String[] {"/"};
	}
}
*/