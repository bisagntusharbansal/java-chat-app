pipeline {
agent any
environment {
APP_NAME = 'java-chat-app'
IMAGE_NAME = 'java-chat-app'
}
stages {
stage('Checkout') {
steps { checkout scm }
}
stage('Build & Test (Maven in Docker)') {
steps {
sh 'docker run --rm -v $PWD:/app -w /app maven:3.9-eclipse-temurin-17 mvn -B clean test package'
}
}
stage('Build Docker Image') {
steps {
sh 'docker build -t $IMAGE_NAME:$BUILD_NUMBER .'
sh 'docker tag $IMAGE_NAME:$BUILD_NUMBER $IMAGE_NAME:latest'
}
}
stage('Deploy (run container locally)') {
steps {
sh 'docker rm -f $APP_NAME || true'
sh 'docker run -d --name $APP_NAME -p 8085:8085 $IMAGE_NAME:latest'
}
}
}
post {
always {
archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
}
}
}
