def call(Map config = [:]) {

    pipeline {
        agent any

        stages {

            stage('Build Docker Image') {
                steps {
                    script {
                        buildImage(config.image)
                    }
                }
            }

            stage('Push Docker Image') {
                steps {
                    script {
                        pushImage(config.image)
                    }
                }
            }

            stage('Delete Local Image') {
                steps {
                    script {
                        deleteLocalImage(config.image)
                    }
                }
            }

            stage('Update Manifests') {
                steps {
                    script {
                        updateManifests()
                    }
                }
            }

        }
    }
}

// ------------------ FUNCTIONS -------------------

def buildImage(imageName) {
    echo "Building Docker image: ${imageName}"
    sh "docker build -t ${imageName} ."
}

def pushImage(imageName) {
    echo "Pushing Docker image: ${imageName}"
    sh "docker push ${imageName}"
}

def deleteLocalImage(imageName) {
    echo "Deleting local Docker image: ${imageName}"
    sh "docker rmi ${imageName} || true"
}

def updateManifests() {
    echo "Updating Kubernetes manifests..."
    sh "kubectl apply -f k8s/"
}

def pushManifests() {
    echo "Pushing manifests to repository..."
}
