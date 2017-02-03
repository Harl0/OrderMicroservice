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

package com.orderMicroservice.controllers

import com.orderMicroservice.data.ResponseData
import com.orderMicroservice.fixtures.OrderFixture
import org.mockito.Mockito._
import org.scalatest.concurrent.PatienceConfiguration
import play.api.libs.json.JsString
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Helpers}
import uk.gov.hmrc.play.test.UnitSpec

import scala.concurrent.Future

/**
  * Created by jason on 31/01/17.
  */
class OrderControllerSpec extends UnitSpec
  with OrderFixture
  with ResponseData
  with PatienceConfiguration {

  val orderController = new OrderController(mockOrderService, mockLogger)

  "createOrder" should {
    "return a 201 (created) when an order could be created" in {
      val result = Helpers.call(orderController.createOrder, FakeRequest().withJsonBody(validOrderJson))

      when(mockOrderService.createOrder(org.mockito.Matchers.eq(testOrder1))).thenReturn(Future.successful())

      whenReady(result) { res =>
        status(res) shouldEqual CREATED
      }
    }
  }

  "return a 500 (internal server error) when an order could not be created" in {
    val result = Helpers.call(orderController.createOrder, FakeRequest().withJsonBody(validOrderJson))

    when(mockOrderService.createOrder(org.mockito.Matchers.eq(testOrder1))).thenReturn(Future.failed(new Exception("mongo error")))

    whenReady(result) { res =>
      status(res) shouldEqual INTERNAL_SERVER_ERROR
      jsonBodyOf(res) shouldEqual JsString(CREATE_ORDER_500)
    }
  }

  "return a 400 (bad request) when an order is invalid" in {
    val result = Helpers.call(orderController.createOrder, FakeRequest().withJsonBody(invalidOrderJson))

    whenReady(result) { res =>
      status(res) shouldEqual BAD_REQUEST
    }
  }
}