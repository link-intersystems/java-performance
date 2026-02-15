plugins {
    id("com.link-intersystems.java.module")
    id("me.champeau.jmh")
}

dependencies {
    implementation(project(":test-models"))
    implementation("ch.qos.logback:logback-classic:1.5.16")

    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:6.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:6.0.2")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.2")

    jmh("org.openjdk.jmh:jmh-core:1.37")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:1.37")

    // JMH annotation processor (equivalent to the Maven compiler plugin's annotationProcessorPaths)
    jmhAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")
}

jmh {
    jvmArgs = listOf()
    fork = 1
    warmupIterations = 2
    iterations = 2
    timeOnIteration = "5s"


}