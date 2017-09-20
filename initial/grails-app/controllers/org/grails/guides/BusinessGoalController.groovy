package org.grails.guides

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BusinessGoalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BusinessGoal.list(params), model:[businessGoalCount: BusinessGoal.count()]
    }

    def show(BusinessGoal businessGoal) {
        respond businessGoal
    }

    def create() {
        respond new BusinessGoal(params)
    }

    @Transactional
    def save(BusinessGoal businessGoal) {
        if (businessGoal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (businessGoal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond businessGoal.errors, view:'create'
            return
        }

        businessGoal.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'businessGoal.label', default: 'BusinessGoal'), businessGoal.id])
                redirect businessGoal
            }
            '*' { respond businessGoal, [status: CREATED] }
        }
    }

    def edit(BusinessGoal businessGoal) {
        respond businessGoal
    }

    @Transactional
    def update(BusinessGoal businessGoal) {
        if (businessGoal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (businessGoal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond businessGoal.errors, view:'edit'
            return
        }

        businessGoal.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'businessGoal.label', default: 'BusinessGoal'), businessGoal.id])
                redirect businessGoal
            }
            '*'{ respond businessGoal, [status: OK] }
        }
    }

    @Transactional
    def delete(BusinessGoal businessGoal) {

        if (businessGoal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        businessGoal.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'businessGoal.label', default: 'BusinessGoal'), businessGoal.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'businessGoal.label', default: 'BusinessGoal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
