package net.nosegrind.apiframework

import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.groovy.grails.commons.*
import javax.servlet.forward.*
import javax.servlet.http.HttpServletRequest


class BatchRequestService{

	void setApiParams(HttpServletRequest request, GrailsParameterMap params){
			request?.JSON.each{ k,v ->
				params[k]=v
			}
	}

}
