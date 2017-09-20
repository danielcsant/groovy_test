package org.grails.guides

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class IssuesServiceSpec extends Specification implements ServiceUnitTest<IssuesService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
