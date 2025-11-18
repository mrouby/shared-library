def buildImage(imageName) {
    echo "Building Docker image: ${imageName}"
    sh "docker build -t ${imageName} ."
}

def scanImage(imageName) {
    echo "Scanning Docker image: ${imageName}"
    sh "trivy image ${imageName} || true"
}

def pushImage(imageName) {
    echo "Pushing Docker image: ${imageName}"
    sh "docker push ${imageName}"
}

def deleteLocalImage(imageName) {
    echo "Deleting local Docker image: ${imageName}"
    sh "docker rmi ${imageName} || true"
}

def updateManifests(kubeServer) {
    echo "Applying manifests to Kubernetes at ${kubeServer}"
    sh "kubectl --server=${kubeServer} apply -f k8s/"
}

def pushManifests() {
    echo "Pushing manifests (git push)"
    sh "git add k8s/ && git commit -m 'Update manifests' && git push || echo 'No changes to push'"
}
