- Create cluster
```bash
kind create cluster --name country-grabber-cluster
```
- (OPTIONAL) Inspect cluster
```bash
kubectl cluster-info
```
```bash
kubectl get nodes
```
- Load Docker Image to cluster
```bash
kind load docker-image country-grabber --name country-grabber-cluster
```
- Apply both the Deployment AND Service settings:
```bash
kubectl apply -f kubernetes/deployment.yaml
```
```bash
kubectl apply -f kubernetes/service.yaml
```
- (OPTIONAL) Check deployment and Pods
```bash
kubectl get deployments
```
```bash
kubectl get pods
```
- Forwards our Computer's port 8080 to the Kubernetes Service
```bash
kubectl port-forward service/spring-app-service 8080:8080
```

# Stopping/Deleting
- Stopingp the cluster (Stops container assossiated with )
