def buildImage(String imageName, String dockerfilePath=".") {
    echo "Building Docker image: ${imageName}"
    sh "docker build -t ${imageName} ${dockerfilePath}"
}

def scanImage(String imageName) {
    echo "Scanning Docker image with Trivy: ${imageName}"
    sh "trivy image ${imageName}"
}

def pushImage(String imageName) {
    echo "Pushing image to DockerHub: ${imageName}"
    sh "docker push ${imageName}"
}

def deleteLocalImage(String imageName) {
    echo "Deleting local Docker image: ${imageName}"
    sh "docker rmi ${imageName}"
}

def updateManifests() {
    echo "Applying manifests to Kubernetes..."
    sh "kubectl apply -f k8s/"
}

def pushManifests() {
    echo "Pushing manifests to Git..."
    sh "git add k8s/ && git commit -m 'update manifests' && git push"
}
