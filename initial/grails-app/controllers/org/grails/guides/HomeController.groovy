package org.grails.guides

class HomeController {

    def issuesService

    def index() {
//        respond model:[businessGoals: issuesService.getBusinessGoals()]
        respond([businessGoals: issuesService.getBusinessGoals()])
    }
}
