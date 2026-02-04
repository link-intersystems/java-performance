plugins {
    idea
}

idea {
    module {
        isDownloadSources = true
    }
}


tasks.register<DefaultTask>("afterPublish") {
}