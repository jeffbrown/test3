package net.nosegrind.apiframework

import grails.converters.JSON
import org.apache.catalina.connector.*
import grails.util.Metadata
import grails.web.servlet.mvc.GrailsParameterMap
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired


class BatchInterceptor{

    BatchInterceptor() {
        String apiVersion = Metadata.current.getApplicationVersion()
        String entryPoint = "v${apiVersion}"

        match(uri:"/${entryPoint}/**")
    }

    boolean before() {
        println("#### BATCHINTERCEPTOR BEFORE")

        try {
            if(!request.getAttribute('batchLength')){ request.setAttribute('batchLength',3) }
            if (!request.getAttribute('batchInc')) {
                println('init batchinc')
                request.setAttribute('batchInc', 1)
            } else {
                println('increment batchinc')
                request.setAttribute('batchInc', request.getAttribute('batchInc').toInteger() + 1)
            }
            return true
        }catch(Exception e) {
            println("[BatchInterceptor :: preHandler] : Exception - full stack trace follows:"+ e)
        }
    }

    boolean after() {
        println("#### BATCHINTERCEPTOR AFTER")
        try{
            println("BatchLength("+request.getAttribute('batchLength')+") > BatchInc("+(request.getAttribute('batchInc'))+")")
            if(request.getAttribute('batchLength')>=(request.getAttribute('batchInc'))){
                forward(params)
                return false
            }
            false
        }catch(Exception e) {
            println("[BatchInterceptor :: postHandler] : Exception - full stack trace follows:"+ e)
        }
    }

}
