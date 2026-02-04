plugins {
    id("com.link-intersystems.jmh.module")
}

val mapstructVersion = "1.5.5.Final"

dependencies {
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    implementation("org.springframework:spring-beans:6.1.8")
    implementation("com.github.dozermapper:dozer-core:7.0.0")
}