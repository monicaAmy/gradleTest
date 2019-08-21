
plugins {
    java
}

group = "com.su"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        flatDir { dirs("libs") }
        mavenCentral()
    }
}

apply {
    plugin("java")
    plugin("idea")
}





val kotlin_version: String by extra

repositories {
    flatDir { dirs("libs") }
    mavenCentral()
}

dependencies {
    compile ("junit", "junit", "4.12")
    compile( "com.lmax", "disruptor", "3.4.2")
    compile ("com.rabbitmq", "amqp-client", "5.2.0")
    compile("cglib", "cglib", "3.2.7")
    annotationProcessor("org.projectlombok", "lombok", "1.18.2")
    compileOnly("org.projectlombok", "lombok", "1.18.2")
    compile("org.apache.httpcomponents", "httpasyncclient", "4.1.4")
    compile("io.netty", "netty", "3.10.5.Final")
    compile("io.netty", "netty-all", "5.0.0.Alpha2")
    compile("com.google.protobuf", "protobuf-java", "2.4.1")
    compile("log4j", "log4j", "1.2.17")
    compile("org.slf4j", "slf4j-log4j12", "2.0.0-alpha0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
