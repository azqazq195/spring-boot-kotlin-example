#bin/bash

# Docker App 실행
open -a Docker

# -f docker-compose 파일 위치 설정
# -p Project Name 설정
# -d 백그라운드 실행
docker-compose -f ./docker/docker-compose.yml -p jwt up -d
