pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t java-chat-app:latest .'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                docker rm -f java-chat-app || true
                docker run -d --name java-chat-app -p 8085:8085 java-chat-app:latest
                '''
            }
        }
    }
}
