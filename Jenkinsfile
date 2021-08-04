pipeline{
    agent any
    tools {
        maven 'Maven 3.6.1'
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
               git branch: 'testing',
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
                    '-DbaseUrl=http://52.14.147.231/wp-json/wc/v2'
            }
        }
    }
}
