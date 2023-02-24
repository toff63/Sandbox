# K8 Basics

Simple node app with a pod and a service definition.
You can run k8 locally by using minikube.

To create a pod
```
kubectl apply -f pod.yml
```

To access it using command line:
```
kubectl port-forward first-pod 8080:8080
```

Then you can see the deployed pod locally at [http://localhost:8080](http://localhost:8080)

To have a load balancer lcoally, we should deploy a service. Locally, you need to use `NodePort` as type. In cloud provider, you should use `LoadBalancer` as it will provision an Internet facing load balancer for you.
```
kubectl apply -f svc-local.yml
```

To deploy the service in the cloud, update the default kubectl config by running
```
export KUBECONFIG=$PWD/config
```

Provision the service running
```
kubectl apply -f svc-cloud.yml
```

You can retrieve the loadbalancer IP by running
```
kubectl get svc 
```
Alternatively you can port forward your localhost 8080 port to the remote load balancer:
```
kubectl port-forward --address 0.0.0.0 service/cloud-lb 8080:8080
```
The page can now be visited locally at [http://localhost:8080](http://localhost:8080).

## Clean up

```
kubectl delete svc cloud-lb
kubectl delete pod first-pod
```
