plugins {
    id("com.possible-triangle.common")
}

val rei_version: String by extra
val rei_annotations_version: String by extra

dependencies {
    modCompileOnly("curse.maven:jei-238222:5466551")
    modCompileOnly("curse.maven:emi-580555:5436759")

    modCompileOnly("me.shedaniel:RoughlyEnoughItems-neoforge:${rei_version}")
    modCompileOnly("me.shedaniel:REIPluginCompatibilities-forge-annotations:${rei_annotations_version}")
}
