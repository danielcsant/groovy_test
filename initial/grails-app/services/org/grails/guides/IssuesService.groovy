package org.grails.guides

import grails.gorm.transactions.Transactional
import net.rcarz.jiraclient.*

@Transactional
class IssuesService {

    def getBusinessGoals() {
        BasicCredentials creds = new BasicCredentials("", "")
        JiraClient jira = new JiraClient("https://stratio.atlassian.net", creds)

        Issue issue = jira.getIssue("AG-21")

        issue.getSummary()
    }
}
