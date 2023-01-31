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
                        // Get some code from a GitHub repository
                        git 'https://github.com/semiraz/ABHAuctionAppTesting.git/'

                     // Run Maven on a Unix agent.
                       sh "mvn test -PSmoke"
                    }
                }

                stage('Run UI Regression Tests') {
                    steps {

                        sh "mvn test -PRegression"
                    }
                }
            }
        }
    }
}
