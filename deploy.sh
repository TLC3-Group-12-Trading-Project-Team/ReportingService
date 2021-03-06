#!/bin/bash
set -e
scp  -o StrictHostKeyChecking=no -r ./.env ./docker-compose.staging.yml ubuntu@$EC2_PUBLIC_IP_ADDRESS:/home/ubuntu/TradeProject/Reporting
ssh -o StrictHostKeyChecking=no ubuntu@$EC2_PUBLIC_IP_ADDRESS << 'ENDSSH'
  cd /home/ubuntu/TradeProject/Reporting
  export $(cat ./.env | xargs)
  echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
  docker pull elijahahianyo/trade-project:reporting-service-prod
  docker-compose -f docker-compose.staging.yml  up -d
ENDSSH

