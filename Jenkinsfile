pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven"
    }

    stages {
        stage('UI Tests') {
            parallel {
                stage('Run UI Smoke Test') {
                    steps {
                     // Run Maven on a Unix agent.
                        sh 'mvn test -PSmoke -Dbrowser="$browser"'
                    }
                                        post {
                                            always {
                                                publishHTML(
                                                [allowMissing: false,
                                                alwaysLinkToLastBuild: false,
                                                keepAll: false,
                                                reportDir: 'reports/',
                                                reportFiles: 'index.html',
                                                reportName: 'HTML Smoke Report',
                                                reportTitles: 'UI Smoke Report',
                                                useWrapperFileDirectly: true
                                                ])
                                            }
                                        }
                }

                stage('Run UI Regression Tests') {
                    steps {
                        sh "mvn test -PRegression -Dbrowser='$browser'"
                    }
                    post {
                        always {
                            publishHTML(
                            [allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: false,
                            reportDir: 'reports/',
                            reportFiles: 'index.html',
                            reportName: 'HTML Regression Report',
                            reportTitles: 'UI Regression Report',
                            useWrapperFileDirectly: true])
                        }
                    }
                }
            }
        }


    }
}
