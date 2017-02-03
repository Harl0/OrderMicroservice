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

import com.google.inject.Inject
import com.orderMicroservice.data.{LoggerErrorData, ResponseData}
import com.orderMicroservice.models.Order
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import com.orderMicroservice.services.MongoOrderService
import play.api.LoggerLike
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, Results}
import uk.gov.hmrc.play.microservice.controller.BaseController

/**
  * Created by jason on 31/01/17.
  */
class OrderController @Inject()(orderService: MongoOrderService, logger: LoggerLike)
  extends BaseController
    with LoggerErrorData
    with ResponseData {

  def createOrder: Action[JsValue] = Action.async(parse.json) { implicit request =>
    withJsonBody[Order] { order =>
      orderService.createOrder(order).map { result => Results.Created}
        .recover {
          case ex => logger.error(creatingOrderError(order, ex))
            InternalServerError(Json.toJson(CREATE_ORDER_500))
        }
    }
  }
}

