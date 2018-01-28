import jdk.nashorn.internal.objects.NativeFunction.apply
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import sun.security.pkcs.PKCS8Key.version

group = "com.su"
version = "1.0-SNAPSHOT"

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.10"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
    }
    
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("idea")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    kotlinModule("stdlib-jdk8", kotlin_version)
    testCompile ("junit", "junit", "4.12")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
/*tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}*/

