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
    // إذا عندك خطوات push، هنا تتحط
}
