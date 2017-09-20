package org.grails.guides

class BusinessGoal {

    String name

    static constraints = {
        name maxSize: 255
    }
}
