# lfp-jec
Jec是一个基于微服务架构的，分布式Java EE 企业级快速开发平台，致力于打造大型企业分布式、互联网、云开发平台，基于代码重用、组件重用、业务逻辑重用、组装重用，结合在线流程设计器，将开发人员从传统的流程管理业务开发中解放出来，把更多的精力集中解决客户的业务数据处理，为企业提供安全、稳定、统一、服务化的框架。

## 总体架构
### 前后台分离
#### 前台
##### web
##### app
#### 后台
##### 微服务架构
##### RESTful
## 软件架构
### 基础类库
#### jec-frame-util
#### jec-frame-base
### 监控应用
#### jec-center
#### jec-turbine
#### jec-sleuth
### 独立应用
#### jec-gateway-aa
#### jec-config-bb
#### jec-service-xxx
#### jec-balance-xxx
### 前端类库
#### jec-frame-face
#### jec-frame-desk
#### jec-webapp-zzz
## 部署架构
### 微服务部署
#### 服务注册中心集群
#### 配置服务集群
#### 网关集群
#### xxx服务集群
### 应用部署
#### web
##### 一站式
##### 多站式
#### app
##### 客户端
### 技术架构
#### 框架级核心技术
##### spring-boot
##### spring-cloud
##### spring-mvc
#### 构件级关键技术
##### 数据
###### 数据库
SQL
NoSQL
###### 连接池
Druid
C3P0
Dynamic
###### ORM
Hibernate
MyBatis
##### 缓存
###### EhCache 
###### Redis
##### 队列
###### Kafka
###### Rabbit
##### 检索
###### ES
