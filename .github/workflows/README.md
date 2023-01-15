# M1 docker build 이슈
m1 맥북에서 docker build 시 arm64 이미지로 생성된다.
이를 해결하기 위해 amd64 option을 추가하여 이미지를 생성해야한다.

하지만 docker build는 github actions에서 실행되기 때문에 job build image 인 ubuntu 기준으로 생성되어 문제가 발생하지 않는다.