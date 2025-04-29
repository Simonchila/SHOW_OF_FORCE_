plugins {
    id("java")
}

group = "App"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Adding MariaDB JDBC driver as an implementation dependency
    implementation(files("libs/mariadb-java-client-3.3.2.jar"))

    // Adding Flatlaf dependency to the project
    implementation("com.formdev:flatlaf:3.4")

    // Adding Apache PDF box dependency from the local libs folder
    implementation(files("libs/pdfbox-app-3.0.4.jar"))

    // Adding Vonage SMS client (Alternative to Twilio)
    implementation("com.vonage:client:[6.1.0,7.0.0)")

    // Adding the miglayout dependancy
    implementation("com.miglayout:miglayout-swing:5.3")

    // Adding SLF4J logger for log management
    implementation("org.slf4j:slf4j-simple:1.7.32")

    // Adding ZXing library dependacies
    implementation("com.google.zxing:core:3.5.2")
    implementation("com.google.zxing:javase:3.5.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}