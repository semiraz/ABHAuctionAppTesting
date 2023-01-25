pipeline {
  agent any
  stages {
    stage('Test smoke') {
      parallel {
        stage('Test smoke') {
          steps {
            echo 'Executing smoke test'
          }
        }

        stage('Regression test') {
          steps {
            echo 'Regression tests..'
          }
        }

      }
    }

  }
}