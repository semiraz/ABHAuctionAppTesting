pipeline {
    agent any
    parameters {
        choice(name : 'browser', choices: ['chrome', 'chromeheadless', 'firefox', 'firefoxheadless'])
    }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven"
    }

    stages {
        stage('Run UI Smoke Test') {
            steps {
             // Run Maven on a Unix agent.
                sh "mvn test -PSmoke -Dbrowser=$params.browser"
            }
        }

        stage('Run UI Regression Tests') {
            steps {
                sh "mvn test -PRegression -Dbrowser=$params.browser"
            }
        }
    }
    post {
        always {
            publishHTML(
            [allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'reports/',
            reportFiles: 'index.html',
            reportName: 'HTML Report',
            reportTitles: 'UI Report',
            useWrapperFileDirectly: true
            ])
        }
    }
}
