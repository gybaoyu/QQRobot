# QQRobot

基于 [gocq-spring-boot-starter](https://github.com/NKDark/gocq-spring-boot-starter)SDK对[AstralGocq](https://github.com/ProtocolScience/AstralGocq)的一个超简易插件实现

### 受SDK限制，本项目基于 [SpringBoot 2.7.7](https://github.com/spring-projects/spring-boot/tree/v2.7.7) 版本开发

本项目仅仅是一个简易的插件实现框架，深入开发详见 [gocq-spring-boot-starter](https://github.com/NKDark/gocq-spring-boot-starter)的SDK以及gocq的官方文档[gocq](https://docs.go-cqhttp.org/)

---

# 附 如何创建一个自己的QQBot（简易流程）

## 申明：本教程仅供学习，禁止用于非法用途

### 环境要求

- 一台电脑（当然也可以是服务器）
- 一个QQ号

#### 简单地说明一下qqbot的原理（基于AstralGocq（基于原版gocq的协议二次开发版））

1. 通过AstralGocq，你的主机，和你的QQ号，以某种手段让AstralGocq可以检测到你的QQ号所接收的信息
2. 通过gocq提供的[API | go-cqhttp 帮助中心](https://docs.go-cqhttp.org/api/#基础传输)自行开发插件从而实现对检测到的信息的个性化响应

#### 实现流程

1. 根据[ProtocolScience/AstralGocq: 基于原版gocq的协议二次开发版，让gocq再次伟大](https://github.com/ProtocolScience/AstralGocq)和[Mrs4s/go-cqhttp: cqhttp的golang实现，轻量、原生跨平台.](https://github.com/Mrs4s/go-cqhttp)提供的**文档**，在你的主机上运行**AstralGocq**
2. 根据**文档**和**AstralGocq**的指引在**AstralGocq**上登录你的QQ号，这里不做具体教学，有兴趣可以自己探索
3. 通过SDK开发你的插件，并根据SDK对AstralGocq的config做出对应的修改。需要注意的是，Gocq的SDK不仅仅有本项目使用的基于kotlin+springboot实现的SDK，还有很多别的语言的SDK（可以自行在github上搜索），只要根据SDK和官方的API进行开发，都能达到一样的效果
4. 插件（至少我的插件）的本质是通过WebSocket和Gocq的通信做出个性化响应

