/*
 * Copyright 2011-2013 the original author or authors.
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

 package org.vertx.scala.tests

import org.vertx.testtools.VertxAssert.testComplete
import org.vertx.testtools.VertxAssert.assertEquals
import org.vertx.testtools.TestVerticle
import org.junit.Test

/**
 * Date: 6/1/13
 * @author Edgar Chan
 */
class ScalaHttpTest extends TestVerticle{

  import org.vertx.scala.core._

  @Test
  def testClientDefaults() {

    val html = "<html><body><h1>Hello from vert.x!</h1></body></html>"
    val port = 8080

    vertx.newHttpServer{
      r => r.response.end(html)
    }.listen(port, server =>{
      val client = vertx.newHttpClient.setPort(port)
      client.getNow("/"){
        h => h.bodyHandler{
                data => {
                    assertEquals( html, data.toString )
                    testComplete()
                }
        }
      }
    })

  }

}
