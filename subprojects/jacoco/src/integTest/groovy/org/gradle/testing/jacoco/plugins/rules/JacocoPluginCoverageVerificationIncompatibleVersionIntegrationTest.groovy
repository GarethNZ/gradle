/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.testing.jacoco.plugins.rules

import org.gradle.integtests.fixtures.TargetCoverage
import org.gradle.testing.jacoco.plugins.fixtures.JacocoCoverage

@TargetCoverage({ JacocoCoverage.COVERAGE_CHECK_UNSUPPORTED })
class JacocoPluginCoverageVerificationIncompatibleVersionIntegrationTest extends AbstractJacocoPluginCoverageVerificationVersionIntegrationTest {

    def "fails to verify code coverage metrics"() {
        when:
        fails TEST_AND_JACOCO_COVERAGE_VERIFICATION_TASK_PATHS

        then:
        executedAndNotSkipped(TEST_AND_JACOCO_COVERAGE_VERIFICATION_TASK_PATHS)
        failure.assertHasCause("jacocoReport doesn't support the nested \"check\" element.")
    }
}