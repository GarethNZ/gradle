/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.vcs.internal

import org.gradle.integtests.fixtures.AbstractIntegrationSpec
import org.gradle.integtests.fixtures.ToBeFixedForInstantExecution
import org.gradle.integtests.fixtures.build.BuildTestFile
import org.gradle.test.fixtures.server.http.BlockingHttpServer
import org.gradle.vcs.fixtures.GitFileRepository
import org.junit.Rule
import spock.lang.Issue

class GitVcsIntegrationTest extends AbstractIntegrationSpec implements SourceDependencies {
    @Rule
    GitFileRepository repo = new GitFileRepository('dep', temporaryFolder.getTestDirectory())

    @Rule
    GitFileRepository deeperRepo = new GitFileRepository('deeperDep', temporaryFolder.getTestDirectory())

    @Rule
    GitFileRepository evenDeeperRepo = new GitFileRepository('evenDeeperDep', temporaryFolder.getTestDirectory())
    BuildTestFile depProject

    def setup() {
        buildFile << """
            apply plugin: 'java'
            group = 'org.gradle'
            version = '2.0'
            
            dependencies {
                implementation "org.test:dep:latest.integration"
            }
        """
        file("src/main/java/Main.java") << """
            public class Main {
                Dep dep = null;
            }
        """
        buildTestFixture.withBuildInSubDir()
        depProject = singleProjectBuild("dep") {
            buildFile << """
                allprojects {
                    apply plugin: 'java-library'
                    group = 'org.test'
                }
            """
            file("src/main/java/Dep.java") << "public class Dep {}"
        }
    }
/*
    @ToBeFixedForInstantExecution
    def 'authenticated ssh repo'() {
        given:
        def commit = repo.commit('initial commit')

        settingsFile << """
            sourceControl {
                gitRepository("ssh://git@github.com:GarethNZ/GameDev.git:master").producesModule("org.test:dep")
            }
        """
        expect:
        succeeds('assemble')
        result.assertTaskExecuted(":dep:compileJava")
        result.assertTaskExecuted(":compileJava")

        // Git repo is cloned
        def gitCheckout = checkoutDir(repo.name, commit.id.name, repo.id)
        gitCheckout.file('.git').assertExists()
    }*/

    @ToBeFixedForInstantExecution
    def 'authenticated http repo'() {
        given:
        def commit = repo.commit('initial commit')

        settingsFile << """
            sourceControl {
                gitRepository("http://garethnz:grthlklu@192.168.1.200:3000/garethnz/CRUD-DSL.git").producesModule("org.test:dep")
            }
        """
        expect:
        succeeds('assemble')
        result.assertTaskExecuted(":dep:compileJava")
        result.assertTaskExecuted(":compileJava")

        // Git repo is cloned
        def gitCheckout = checkoutDir(repo.name, commit.id.name, repo.id)
        gitCheckout.file('.git').assertExists()
    }

    // TODO: Use HTTP hosting for git repo
}
