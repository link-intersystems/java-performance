plugins {
    id("com.link-intersystems.module")
    `java-library`
    `maven-publish`
    signing
}

java {
    toolchain {
        // There is a problem when updating to 22 with the dependency-submission action in github.
        // Don't know why it fails with
        // Cannot find a Java installation on your machine (Linux 6.11.0-1014-azure amd64) matching:
        // {languageVersion=22, vendor=any vendor, implementation=vendor-specific, nativeImageCapable=false}.
        // Toolchain download repositories have not been configured.
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    maxHeapSize = "1G"
}

val artifactName = rootProject.name + buildTreePath.replace(':', '-')

publishing.publications.withType<MavenPublication> {
    artifactId = artifactName
}

tasks.withType<Jar> {
    archiveBaseName.set(artifactName)
}


// Add byte-buddy to the jvmArgs to prevent mockito-inline warning:
//   Mockito is currently self-attaching to enable the inline-mock-maker.
//   This will no longer work in future releases of the JDK
val byteBuddyAgent by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    byteBuddyAgent("net.bytebuddy:byte-buddy-agent:1.15.4")
}

tasks.withType(Test::class.java) {
    jvmArgs("-javaagent:${byteBuddyAgent.singleFile}")
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
}

signing {
    val signingKey: String? by project
    val signingKeyExists = signingKey != null

    val signingPassword: String? by project
    val signingPasswordExists = signingPassword != null

    logger.debug("signingKey exists = {}, signingPassword {}", signingKeyExists, signingPasswordExists)

    val signingEnabled = signingKey != null && signingPassword != null
    logger.debug("signingEnabled = {}", signingEnabled)

    isRequired = signingEnabled

    sign(publishing.publications)

    if (signingEnabled) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
}

configurations {
    create("tests") {
        extendsFrom(configurations[JavaPlugin.TEST_RUNTIME_CLASSPATH_CONFIGURATION_NAME])
    }
}

val testJar = tasks.register<Jar>("testJar") {
    dependsOn(tasks["testClasses"])
    archiveClassifier.set("tests")
    archiveExtension.set("jar")
    from(sourceSets["test"].output)
    group = LifecycleBasePlugin.BUILD_GROUP
}


artifacts {
    add("tests", testJar)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}


