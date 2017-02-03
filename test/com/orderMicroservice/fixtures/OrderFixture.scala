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

package com.orderMicroservice.fixtures

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.orderMicroservice.models.Order
import com.orderMicroservice.services.MongoOrderService
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mock.MockitoSugar
import play.api.LoggerLike
import play.api.libs.json.{JsString, Json}

/**
  * Created by jason on 31/01/17.
  */
trait OrderFixture extends MockitoSugar with ScalaFutures {

  // implicits required by Play 2.5
  implicit val system = ActorSystem("order-Microservice")
  implicit val materializer = ActorMaterializer()

  val validOrderJson = Json.obj("customerID" -> "1", "productID" -> "99", "price" -> BigDecimal(4.99))
  val invalidOrderJson = JsString("")
  val testOrder1 = Order(customerID = "1", productID = "99", 4.99)
  val mockOrderService = mock[MongoOrderService]
  val mockLogger = mock[LoggerLike]
}
