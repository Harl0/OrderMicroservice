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

package com.orderMicroservice.services

import com.google.inject.Inject
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import com.orderMicroservice.models.Order
import com.orderMicroservice.repository.MongoOrderConnection

import scala.concurrent.Future

/**
  * Created by jason on 31/01/17.
  */
class MongoOrderService @Inject()(mongoOrderConnection: MongoOrderConnection) {
  def createOrder(order: Order): Future[Unit] = {
    mongoOrderConnection.collection.insert(order).map(result => Unit)
  }
}
