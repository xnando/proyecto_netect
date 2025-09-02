# 🏥 Proyecto Medical App – Docker & Kubernetes

Este proyecto despliega una aplicación **Medical App** compuesta por:
- **Backend (BE)**: API de servicios médicos.
- **Frontend (FE)**: interfaz web para usuarios.
- **Base de datos MySQL**: persistencia de la información.
- **Infraestructura Kubernetes**: para orquestación y gestión.

---

## 📌 1. Requisitos Previos

Antes de iniciar, asegúrate de tener instalado:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Hub](https://hub.docker.com/)
- [Kubernetes](https://kubernetes.io/) (Minikube o un cluster real)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [nginx-ingress controller](https://kubernetes.github.io/ingress-nginx/)

---

## 📌 2. Estructura del Repositorio

```
proyecto/
│── BE/                     # Código fuente del backend + Dockerfile
│── FE/                     # Código fuente del frontend + Dockerfile
│── k8s/                    # Manifiestos de Kubernetes
│   ├── backend-deployment.yaml
│   ├── backend-service.yaml
│   ├── frontend-deployment.yaml
│   ├── frontend-service.yaml
│   ├── ingress-*.yaml
│   ├── mysql-deployment.yaml
│   ├── mysql-service.yaml
│   ├── mysql-volume.yaml
│   ├── namespace.yaml
│   ├── configmap.yaml
│   └── secret.yaml
│── img/                    # Imágenes o diagramas
│── README.md                # Guía principal
```

---

## 📌 3. Contenerización

### Backend
```bash
cd BE
docker build -t usuario/medical-backend:1.0 .
docker push usuario/medical-backend:1.0
```

### Frontend
```bash
cd FE
docker build -t usuario/medical-frontend:1.0 .
docker push usuario/medical-frontend:1.0
```

---

## 📌 4. Despliegue en Kubernetes

### 4.1 Crear namespace
```bash
kubectl apply -f k8s/namespace.yaml
```

### 4.2 Desplegar base de datos
```bash
kubectl apply -f k8s/mysql-volume.yaml
kubectl apply -f k8s/mysql-deployment.yaml
kubectl apply -f k8s/mysql-service.yaml
```

### 4.3 Desplegar backend y frontend
```bash
kubectl apply -f k8s/backend-deployment.yaml
kubectl apply -f k8s/backend-service.yaml
kubectl apply -f k8s/frontend-deployment.yaml
kubectl apply -f k8s/frontend-service.yaml
```

### 4.4 Configuración de ingress
```bash
kubectl apply -f k8s/ingress-ui.yaml
kubectl apply -f k8s/ingress-api.yaml
kubectl apply -f k8s/ingress-main.yaml
```

*(dependiendo del diseño, puedes unificar ingress en un solo archivo `ingress.yaml`)*

---

## 📌 5. Monitoreo y Observabilidad

Ver pods:
```bash
kubectl get pods -n medical-app
```

Ver servicios:
```bash
kubectl get svc -n medical-app
```

Logs del backend:
```bash
kubectl logs -f deploy/medical-backend -n medical-app
```

Uso de recursos:
```bash
kubectl top pods -n medical-app
```

---

## 📌 6. Persistencia

Se utiliza un **PersistentVolumeClaim (PVC)** definido en `mysql-volume.yaml` para asegurar que los datos de la base de datos se mantengan incluso si los pods se reinician.

---

## 📌 7. Acceso a la Aplicación

Si usas Minikube:

1. Habilitar el túnel:
```bash
minikube tunnel
```

2. Agregar entrada en `/etc/hosts`:
```
127.0.0.1   medical.local
```

3. Acceder en navegador:
- Frontend: http://medical.local
- Backend API: http://medical.local/api

---

## 📌 8. Decisiones de Arquitectura

- **Ingress Controller**: centraliza el acceso HTTP/HTTPS.
- **ConfigMap y Secret**: separan configuraciones y credenciales sensibles.
- **PVC para MySQL**: mantiene persistencia de los datos.
- **Replicas**: backend y frontend tienen múltiples réplicas para alta disponibilidad.
- **Namespace dedicado**: separa los recursos del proyecto.

---

## 📌 9. Evidencia de Ejecución

En la carpeta [`/img`](./img) se pueden incluir:
- Diagramas de arquitectura.
- Historial de comandos (`historial.txt`).
- Capturas de `kubectl get pods`, `kubectl logs`, etc. *(si se tienen disponibles)*.

---

## 📌 10. Próximos pasos

- Añadir monitoreo con **Prometheus + Grafana**.
- Integrar pipeline **CI/CD** con GitHub Actions.
- Incluir **NetworkPolicies** para reforzar seguridad interna.

---

## 📌 11. Créditos

Proyecto desarrollado en entorno **Ubuntu + Docker + Kubernetes**, siguiendo prácticas recomendadas de contenerización y despliegue en la nube.
