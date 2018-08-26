#!groovy
node {


    currentBuild.result = "SUCCESS"

    try {

       stage('Checkout'){
          checkout scm
       }

        stage('Artifactory configuration') {
            // Tool name from Jenkins configuration
            rtGradle.tool = "Gradle-2.4"
            // Set Artifactory repositories for dependencies resolution and artifacts deployment.
            rtGradle.deployer repo:'ext-release-local', server: server
            rtGradle.resolver repo:'remote-repos', server: server
        }

        // Get Artifactory server instance, defined in the Artifactory Plugin administration page.
        def server = Artifactory.server "SERVER_ID"
        // Create an Artifactory Gradle instance.
        def rtGradle = Artifactory.newGradleBuild()
        def buildInfo

        stage('Gradle build') {
            buildInfo = rtGradle.run rootDir: "gradle-examples/4/gradle-example-ci-server/", buildFile: 'build.gradle', tasks: 'clean artifactoryPublish'
        }

        stage('Publish build info') {
            server.publishBuildInfo buildInfo
        }

    }
    catch (err) {

        currentBuild.result = "FAILURE"

            mail body: "project build error is here" ,
            from: 'silva.willian@outlook.com',
            replyTo: 'silva.willian@outlook.com',
            subject: 'project build failed',
            to: 'silva.willian@outlook.com'

        throw err
    }
}