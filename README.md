# component-WanAndroid-Module

###WanAndroid 项目 -- 组件化


本项目基于 组件化 + Arouter + Jetpack + Rxjava + Retrofit + AOP 等框架实现的一款开源项目

"集成开发模式" 和 "组件开发模式"的切换开关 true表示组件独立运行，false表示一个library

gradle.properties中

```java

isRunModule            =   false

```


```java
|- WanAndroid
||-- app // app 入口
    ||librarys //library库
      ||--library-aop// aop 封装（登录校验、点击）
      ||--library-db// room数据库封装
      ||--library-network// 网络请求封装（livedata+rxjava+retrofit）
      ||--library-base// 基础封装（BaseAc、BaseFg、BaseUtil等）
      ||--library-common//通用库（全局Context获取等）
      ||--library-widget// 控件封装
  ||--modules// 功能模块
    ||--module-home// 首页模块
    ||--module-login// 登录模块
    ||--module-project// 项目模块
    ||--module-square // 广场模块
    ||--module-public //公众号模块
    ||--module-mine//我的模块
    ||--module-web//网页模块
||-- README.md
 ```




Kotlin项目中的配置方式

 ```java
android {
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
    }
}

dependencies {
    // 替换成最新版本, 需要注意的是api
    // 要与compiler匹配使用，均使用最新版可以保证兼容
    compile 'com.alibaba:arouter-api:x.x.x'
    annotationProcessor 'com.alibaba:arouter-compiler:x.x.x'
}


 ```


Kotlin项目中的配置方式

 ```java
// 可以参考 module-kotlin 模块中的写法
apply plugin: 'kotlin-kapt'

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.getName())
    }
}

dependencies {
    compile 'com.alibaba:arouter-api:x.x.x'
    kapt 'com.alibaba:arouter-compiler:x.x.x'
    ...
}

 ```




参考地址

https://github.com/alibaba/ARouter/blob/master/README_CN.md

https://github.com/1170762202/WanAndroid