

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

@Integration
class MappingConfigFunctionalSpec extends GebSpec {

    def grailsApplication


    void 'Test that after interceptor can forward'() {
        when:
        String apiVersion = grailsApplication.config.getProperty('info.app.version')
        String entrypoint = "v${apiVersion}"

        go "${entrypoint}/post/show?interceptorForwards=true"

        then:
        $().text() == 'Hi There! Special Action: forward'
    }

    void 'test invoking a URL that results in an interceptor invoking a redirect'() {
        setup:
        String apiVersion = grailsApplication.config.getProperty('info.app.version')
        String entrypoint = "v${apiVersion}"


        when:
        go "/${entrypoint}/post/show/1"

        then:
        $().text() == 'rendered by TestappInterceptor.after'

        when:
        go "/${entrypoint}/message"

        then:
        $().text() == 'Message: [(beforeInterceptor) (afterInterceptor) (id is 1) (model : [fname:Bob]) (redirect) (beforeInterceptor) (afterInterceptor) (id is 0)]'
    }

    void 'test invoking a URL that results in several round trips of interceptor redirects'() {
        setup:
        String apiVersion = grailsApplication.config.getProperty('info.app.version')
        String entrypoint = "v${apiVersion}"


        when:
        go "/${entrypoint}/post/show/2"

        then:
        $().text() == 'rendered by TestappInterceptor.after'

        when:
        go "/${entrypoint}/message"
        println("###TEST###"+$().text()+"###TEST###")
        then:
        $().text() == 'Message: [(beforeInterceptor) (afterInterceptor) (id is 2) (model : [fname:Bob]) (redirect) (beforeInterceptor) (afterInterceptor) (id is 1) (model : [fname:Bob]) (redirect) (beforeInterceptor) (afterInterceptor) (id is 0)]'
    }

    void 'test that interceptors are not run when a request is sent to a URL that does not match a URL mapping'() {
        setup:
        String apiVersion = grailsApplication.config.getProperty('info.app.version')
        String entrypoint = "v${apiVersion}"


        when:
        go '/fred/post/show/1'

        then:
        $().text().contains 'Error: Page Not Found (404)'
        $().text().contains 'Path: /fred/post/show'

        when:
        go "/${entrypoint}/message"

        then:
        $().text() == 'Message: []'
    }

}