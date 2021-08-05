# Automation Playground API Testing Project

This RestAssured automation project was designed to test Gorilla Logic Automation Playground API http://52.14.147.231/wp-json

## How to run it

### Local run
In a terminal, run the following maven command:

`mvn clean install -ea -Dtestng.dtd.http=true 
    -DbaseUrl=http://52.14.147.231/wp-json/wc/v2 
    -Dapi_user=auto -Dapi_psw=auto`
    
Where:
- baseUrl = API Base URL
- api_user = User name for the API
- api_psw = Password for the API

### Jenkins run

#### Prerequisites
1. Install Maven plugin
2. Install Workspace cleanup plugin
3. Configure API credentials inside Jenkins manager

NOTE: There are more secure ways of handling credentials, but for the sake of this example, I used the most straightforward way.

#### Configuring a pipeline file

1. Add maven to your tools section
 
 `tools {
         maven 'Maven 3.6.1' 
     }`   

2. Add skipDefaultCheckout option to cleanup the workspace

 `options {
        // Clean before build options
        skipDefaultCheckout(true)
    }`

3. In your stage steps, first call the cleanup plugin `cleanWs()` Then, configure git to checkout from the desired branch

`git branch: 'main',
    url: 'https://github.com/manuelrodriguezGL/webtesting_challenge.git'`

4. Setup an environment, where you read the credentials stored in Jenkins credentials manager. Make sure you are using the same credentials ID.
 
` environment{
    SAUCE_CREDENTIALS = credentials('secret_api')
} `

5. Finally, in your steps, call a shell script with maven command, similarly to a local run

`mvn clean install -ea -Dtestng.dtd.http=true 
    -DbaseUrl=http://52.14.147.231/wp-json/wc/v2 
    -Dapi_user=auto -Dapi_psw=auto`
    
Where:
- baseUrl = API Base URL
- api_user = User name for the API
- api_psw = Password for the API

NOTE: Don't remove `testng.dtd.http=true`, since it is needed to run it from terminal. That's a workaround for a known TestNG bug.