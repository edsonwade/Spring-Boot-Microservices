pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout code
                    git branch: 'main', url: 'https://github.com/edsonwade/Springfy-Microservice'
                }
            }
        }

        stage('Set up AdoptOpenJDK 17') {
            steps {
                script {
                    // Set up AdoptOpenJDK 17
                    // You might need to adjust this to match your Jenkins configuration
                    tool 'JDK17'
                }
            }
        }

        stage('Build and Test with Maven') {
            steps {
                script {
                    // Build and Test with Maven
                    sh 'mvn -B clean install'
                }
            }
        }
    }
}
