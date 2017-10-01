docker入门教程
#### 安装
sudo apt-get install -y docker.io

安装最新版本docker
curl -s https://get.docker.com|sh

查看docker版本信息
docker version
启动docker服务
service docker start

#### 拉取镜像
docker pull [options] name[tag]
option: 拉取的参数,用到了在过来写
name:镜像名称 tags:默认拉取最新版本

#### 查看镜像,以及验证pull是否成功
docker images [options] [repository[:tag]]

##### 运行镜像
docker run [options] image[:tag][command][arg...]
command表示镜像要执行的命令
args命令执行的参数

#### docker运行过程
直接复制运行helloworld的运行流程
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

#### 运行dokcer nginx
类型:持久运行容器

方式:前台运行&后台运行

最终:进入容器内部

拉取:docker pull hub.c.163.com/library/nginx:latest

运行:docker run前台运行

查看正在运行的容器: docker ps

帮助:docker --help

后台运行:docker run -d

进入容器:dokcer exec 加--help查看帮助
        docker exec -it [name或id] bash,进入容器的终端
        查看进程:ps -ef



docker 网络使用network namespace隔离
怎样访问到docker中的nginx
网络类型:
    bridge:docker分配独立的namespace,涉及端口映射,可以将容器端口和主机端口之间进行映射
    host:不会获得独立的namespace,和主机公用一个,不会虚拟出网卡,配置自己的ip等,而会用宿主机的ip和端口
    none:没有网络

停止镜像
docker stop
docker p 映射到指定端口 demon:docker run -d -p 8080:80 hub.c.163.com/library/nginx 8080宿主机端口,80容器端口
查看端口状态:netstat -na | grep 8080

docker P 映射到随机端口 demon:docker run -d -P hub.c.163.com/library/nginx
docker  ps 查看端口状态

#### 制作自己的镜像 java web
Dockerfiel告诉docker怎样制作镜像

写好dockerfile使用docker build 即可

Dockerfile
from 基础镜像
linux命令:改名称 mv jpress-web-newest.war jpress.war

写好dockerfile
docker build dockerfile

docker build -t jpress:latest . 设定名字和tag

docker run -d -p 8888:8080 jpress

通过jpress访问mysql

docker pull

docker run -d -p 3306:3306  -e MYSQL_ROOT_PASSWORD=2014080102 -e MYSQL_DATABASE=jpress hub.c.163.com/library/mysql

docker reatart id 重启镜像






















