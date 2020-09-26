pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh './gradlew clean build -x check -x test'
      }
    }
    stage('static-analysis') {
      parallel {
        stage('check') {
          steps {
            sh './gradlew check'
          }
        }
        stage('sonarqube') {
          steps {
            sh 'echo TBD'
          }
        }
      }
    }
    stage('test') {
      steps {
        sh './gradlew test'
      }
    }
    stage('deploy') {
      when {
        branch 'master'
      }
      post {
        success {
          slackSend(channel: '#deploy', color: 'good', message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was successful")

        }

        failure {
          slackSend(channel: '#deploy', color: 'danger', message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was failed")

        }

      }
      steps {
        sh '''bash <<EOF
          #!/bin/bash
          echo TBD
EOF'''
      }
    }
  }
  post {
    always {
      junit 'build/test-results/**/TEST-*.xml'
      recordIssues enabledForFailure: true, tool: checkStyle(pattern: '**/build/reports/checkstyle/*.xml')
      recordIssues enabledForFailure: true, tool: findBugs(pattern: '**/build/reports/findbugs/*.xml')
      recordIssues enabledForFailure: true, tool: pmd(pattern: '**/build/reports/pmd/*.xml')
      recordIssues enabledForFailure: true, tool: cpd(pattern: '**/build/reports/cpd/*.xml')
    }
  }
  environment {
    SONAR_HOST_URL = credentials('SONAR_HOST_URL')
    SONAR_LOGIN_TOKEN = credentials('SONAR_LOGIN_TOKEN')
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '7'))
  }
}