plugins {
    java
}

group = "com.su"
version = "1.0-SNAPSHOT"

buildscript {
    //    var kotlin_version: String by extra
//    kotlin_version = "1.2.10"

    repositories {
        mavenCentral()
    }

    dependencies {
        // classpath(kotlinModule("gradle-plugin", kotlin_version))
    }

}

apply {
    plugin("java")
    // plugin("kotlin")
    plugin("idea")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    //  kotlinModule("stdlib-jdk8", kotlin_version)
    compile ("junit", "junit", "4.12")
    compile( "com.lmax", "disruptor", "3.4.2")
    compile ("com.rabbitmq", "amqp-client", "5.2.0")
    compile("cglib", "cglib", "3.2.7")
    annotationProcessor("org.projectlombok", "lombok", "1.18.2")
    compileOnly("org.projectlombok", "lombok", "1.18.2")
    compile("org.apache.httpcomponents", "httpasyncclient", "4.1.4")
    compile("cglib", "cglib", "3.2.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
