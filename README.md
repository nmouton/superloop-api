# superloop-api
A simple containerized spring boot 2.0 project using gradle. Project template via Spring Initializr.

# test
gradlew clean build test

# run
gradlew bootRun

# :ship: build and deploy :ship:
  - Requires docker, gradle, gcloud sdk, kubectl and a google cloud account with a kubernetes cluster configured
  - gradlew docker build
  - docker build . -t gcr.io/{{project-id}}/superloop-api:{{version}}
  - gcloud docker -- push gcr.io/{{project-id}}/superloop-api:{{version}}

# configure a gcloud deployment and expose
  - kubectl run {{deplloyment-name}} --image gcr.io/{{project-id}}/superloop-api:{{version}} --port={{port to expose}}
  - kubectl expose deployment {{deplloyment-name}} --type=LoadBalancer
