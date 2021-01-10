package com.college.register.application;

import javax.ws.rs.core.Application;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;

/**
 * @author Perceive Chuchu
 *
 */
@ApplicationScoped
@ApplicationPath("v1")
public class StudentApplication extends Application {
	
}
