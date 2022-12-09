pipeline{
    agent any
    tools {
        maven 'Maven 3.8.6'
    }
    options {
        // Clean before build options
        skipDefaultCheckout(true)
    }
    stages{
        stage('SCM Checkout'){
            steps {
               // Clean before build
               cleanWs()
               git branch: 'main',
                    url: 'https://github.com/manuelrodriguezGL/apiTesting_challenge.git'
            }
        }
        stage('Test')
        {
            environment{
                API_CREDENTIALS = credentials('secret_api')
            }

            steps{
                sh('echo Reading Username...: $API_CREDENTIALS_USR')
                sh('echo Reading Password...')
                sh 'mvn clean install -Dtestng.dtd.http=true -Dapi_user=$API_CREDENTIALS_USR ' +
                    '-Dapi_psw=$API_CREDENTIALS_PSW ' +
                    '-DbaseUrl=http://0.0.0.0:8085/wp-json/wc/v3'
            }
        }
    }
}
