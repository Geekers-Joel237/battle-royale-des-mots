plugins {
    id("java")
}

allprojects {
    group = "com.brm"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    val junitVersion = "5.10.2"
    val assertjVersion = "3.25.3"

    dependencies {
        testImplementation(platform("org.junit:junit-bom:$junitVersion"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.assertj:assertj-core:$assertjVersion")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}



