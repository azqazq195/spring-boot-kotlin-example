#!/bin/bash

RED="\033[1;31""m"
GREEN="\033[1;32""m"
YELLOW="\033[1;33""m"
NC="\033[0m"

echo "${YELLOW} > input domain name${NC}"
read -r DOMAIN_NAME
FILE_NAME="$(tr '[:lower:]' '[:upper:]' <<< ${DOMAIN_NAME:0:1})${DOMAIN_NAME:1}"

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
touch "controller/${FILE_NAME}Controller.kt"
touch "controller/dto/Create${FILE_NAME}Dto.kt"
touch "controller/dto/Update${FILE_NAME}Dto.kt"
echo "${GREEN} > created controller ${NC}"

mkdir -p "entity/repository"
touch "entity/${FILE_NAME}.kt"
touch "entity/repository/${FILE_NAME}Repository.kt"
echo "${GREEN} > created entity ${NC}"

mkdir "service"
touch "service/${FILE_NAME}Service.kt"
echo "${GREEN} > created service ${NC}"

echo "${GREEN} > done ${NC}"
