
plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    platform("org.komapper:komapper-platform:1.12.1").let {
        implementation(it)
        ksp(it)
    }
    implementation("org.komapper:komapper-starter-r2dbc:1.12.1")
    implementation("org.komapper:komapper-dialect-postgresql-r2dbc:1.12.1")
    ksp("org.komapper:komapper-processor")

    implementation("io.r2dbc:r2dbc-pool:1.0.1.RELEASE")
}

application {
    mainClass.set("MainKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

