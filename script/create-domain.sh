#!/bin/bash

RED="\033[1;31""m"
GREEN="\033[1;32""m"
YELLOW="\033[1;33""m"
NC="\033[0m"

echo "${YELLOW} > input domain name${NC}"
read -r DOMAIN_NAME

cd src
cd main
cd kotlin
cd com
cd example
cd springbootkotlinexample
cd domain

mkdir "$DOMAIN_NAME"
echo "${GREEN} > created ${DOMAIN_NAME} ${NC}"

cd $DOMAIN_NAME

mkdir -p "controller/dto"
touch "controller/${DOMAIN_NAME}Controller.kt"
touch "controller/dto/Create${DOMAIN_NAME}Dto.kt"
touch "controller/dto/Update${DOMAIN_NAME}Dto.kt"
echo "${GREEN} > created controller ${NC}"

mkdir -p "entity/repository"
touch "entity/${DOMAIN_NAME}.kt"
touch "entity/repository/${DOMAIN_NAME}Repository.kt"
echo "${GREEN} > created entity ${NC}"

mkdir "service"
touch "service/${DOMAIN_NAME}Service.kt"
echo "${GREEN} > created service ${NC}"

echo "${GREEN} > done ${NC}"



