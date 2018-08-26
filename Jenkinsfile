#!groovy
node {


    currentBuild.result = "SUCCESS"

    try {

       stage('Checkout'){
          checkout scm
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