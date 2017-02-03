/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orderMicroservice.data

import com.orderMicroservice.fixtures.OrderFixture
import org.scalatest.Matchers
import uk.gov.hmrc.play.test.UnitSpec

/**
  * Created by jason on 03/02/17.
  */
class LoggerErrorDataSpec extends UnitSpec with OrderFixture with Matchers {

  val loggerErrorData = new LoggerErrorData {}

  "createOrderError" should {
    "construct the correct log message with customer id and exception message" in {
      val expectedExceptionMessage = "create customer order error"
      val actualMessage = loggerErrorData.creatingOrderError(testOrder1, new Exception(expectedExceptionMessage))

      actualMessage shouldEqual s"Error creating order: ${testOrder1.toString}, exception: $expectedExceptionMessage"
    }
  }
}
