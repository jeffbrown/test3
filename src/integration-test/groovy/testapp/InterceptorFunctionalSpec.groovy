package testapp

import grails.test.mixin.integration.Integration

import geb.spock.GebSpec
import spock.lang.Issue

@Integration
class InterceptorFunctionalSpec extends GebSpec {

    //@Issue('grails/grails-core#9183')
    void "Test that an after interceptor can render text and return false to disable view rendering"() {
        when:
        go '/post/show'

        then:
        $().text() == 'the after interceptor rendered this'
    }

    void 'Test that after interceptor can render a model and view'() {
        when:
        go '/demo/show?interceptorRendersView=true'

        then:
        $().text() == 'Name: JSB'
    }

    void 'Test that after interceptor can render text'() {
        when:
        go '/post/show?interceptorRendersText=true'

        then:
        $().text() == 'text rendered by interceptor'
    }

    //@Issue('grails/grails-core#9194')
    void 'Test that after interceptor can redirect'() {
        when:
        go '/post/show?interceptorRedirects=true'

        then:
        $().text() == 'Hi There! Special Action: redirect'
    }

    //@Issue('grails/grails-core#9194')
    void 'Test that after interceptor can forward'() {
        when:
        go '/post/show?interceptorForwards=true'

        then:
        $().text() == 'Hi There! Special Action: forward'
    }

    //@Issue('grails/grails-core#9194')
    void 'Test that after interceptor can chain'() {
        when:
        go '/demo/post?interceptorChains=true'

        then:
        $().text() == 'Hi There! Special Action: chain'
    }
}