package net.nosegrind.apiframework

import grails.util.Metadata

class BatchInterceptor{

    BatchInterceptor() {
        String apiVersion = Metadata.current.getApplicationVersion()
        String entryPoint = "v${apiVersion}"

        match(uri:"/${entryPoint}/**")
    }

    boolean before() {
        def batchInc = request.batchInc
        request.batchInc = batchInc ? batchInc + 1 : 1
        log.debug "request.batchInc is ${request.batchInc}"
        true
    }

    boolean after() {
        if(request.batchInc < 5) {
            forward params
            return false
        }
        render "requestBatchInc reached 5"
        false
    }
}
