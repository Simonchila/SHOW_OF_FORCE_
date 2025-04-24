plugins {
    id("java")
}

group = "App"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Adding MariaDB JDBC driver as an implementation dependancy
    implementation(files("libs/mariadb-java-client-3.3.2.jar"))

    // Adding Flatlaf dependancy to the project
    implementation("com.formdev:flatlaf:3.4")

    // Adding Apache PDF box dependacy
    implementation(files("libs/org.apache.pdfbox:pdfbox:3.0.40"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}