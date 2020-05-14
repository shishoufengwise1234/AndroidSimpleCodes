package com.asm_lifecycle.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project


public class LifeCyclePlugin implements Plugin<Project> {

    void apply(Project project){
        System.out.println("====LifeCyclePlugin === : apply")

        def android = project.extensions.getByType(AppExtension)

        System.out.println("----------Registering LifeCycleTransform----------")
        LifeCycleTransform lifeCycleTransform = new LifeCycleTransform()

        android.registerTransform(lifeCycleTransform)

    }
}