import com.link_intersystems.gradle.project.plugin.MultiModuleExtension
import com.link_intersystems.gradle.project.plugin.ProjectNamingStrategies
import com.link_intersystems.gradle.project.plugin.ProjectNamingStrategy
import java.nio.file.Path
import java.util.function.Predicate

rootProject.name = "java-jmh"

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.link-intersystems.gradle.multi-module") version "0.6.2"
}


configure<MultiModuleExtension> {


}
