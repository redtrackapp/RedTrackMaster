package com.dbs.redtrack.business.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface UserCheck {
	 String getService();
	 boolean isActiveUser();
	 boolean isManageRole();
}
