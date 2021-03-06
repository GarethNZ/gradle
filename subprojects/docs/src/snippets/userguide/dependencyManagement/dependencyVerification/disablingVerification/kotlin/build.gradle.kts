/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

repositories {
    mavenCentral()
}

val myPlugin by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = false
}
val myPluginClasspath by configurations.creating {
    extendsFrom(myPlugin)
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    myPlugin("org.apache.commons:commons-lang3:3.3.1")
}

// tag::disabling-one-configuration[]
configurations {
    "myPluginClasspath" {
        resolutionStrategy {
            disableDependencyVerification()
        }
    }
}
// end::disabling-one-configuration[]

tasks.register("checkDependencies") {
    inputs.files(myPluginClasspath)
    doLast {
        println(myPluginClasspath.files)
    }
}

// tag::disabling-detached-configuration[]
tasks.register("checkDetachedDependencies") {
    doLast {
        val detachedConf = configurations.detachedConfiguration(dependencies.create("org.apache.commons:commons-lang3:3.3.1"))
        detachedConf.resolutionStrategy.disableDependencyVerification()
        println(detachedConf.files)
    }
}
// end::disabling-detached-configuration[]
