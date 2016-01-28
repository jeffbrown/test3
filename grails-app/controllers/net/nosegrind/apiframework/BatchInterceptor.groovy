package net.nosegrind.apiframework

import grails.converters.JSON
import org.apache.catalina.connector.*
import grails.util.Metadata
import grails.web.servlet.mvc.GrailsParameterMap
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired


class BatchInterceptor{


    BatchRequestService batchRequestService

    String apiVersion
    String entryPoint

    BatchInterceptor() {
        apiVersion = Metadata.current.getApplicationVersion()
        entryPoint = "v${apiVersion}"

        match(uri:"/${entryPoint}/**")
    }

    boolean before() {
        println("#### BATCHINTERCEPTOR BEFORE")

        if (!request.getAttribute('batchInc')) {
            request.setAttribute('batchInc',0)
        }else{
            request.setAttribute('batchInc',request.getAttribute('batchInc').toInteger() + 1)
        }
        if(!request.getAttribute('batchLength')){ request.setAttribute('batchLength',3) }
        return true
    }

    boolean after() {
        println("#### BATCHINTERCEPTOR AFTER")
        println("BatchLength("+request.getAttribute('batchLength')+") > BatchInc("+(request.getAttribute('batchInc'))+")")
        if(request.getAttribute('batchLength')>=(request.getAttribute('batchInc'))){
            request.setAttribute('batchInc',request.getAttribute('batchInc')+1)
            forward(params)
            return false
        }
        false
    }

}
