plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":common"))
}

val rei_version: String by extra

dependencies {
    modCompileOnly("curse.maven:jei-238222:5846878")
    modImplementation("curse.maven:emi-580555:5436758")

    modCompileOnly("me.shedaniel:RoughlyEnoughItems-fabric:${rei_version}")
}
