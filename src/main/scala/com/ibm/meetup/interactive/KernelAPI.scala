package com.ibm.meetup.interactive

import org.apache.toree.kernel.protocol.v5.client.SparkKernelClient
import org.http4s._
import org.http4s.server._
import org.http4s.dsl._

import _root_.argonaut._, Argonaut._
import org.http4s.argonaut._

object KernelAPI {

  val kernelclient : SparkKernelClient = KernelUtil.apply()

  val service = HttpService {
    case GET -> Root / "hello" / name =>
      Ok(jSingleObject("message", jString(s"Hello, ${name}")))

    case GET -> Root / "execute" =>
      /*val cmd : String = "val sqlContext = new org.apache.spark.sql.SQLContext(sc) ;val df= " +
        "sqlContext"+".range(1,11); val a = df.toJSON.toArray; val b = a"*/
      val cmd : String = "sqlContext.range(1,10).toJSON.toArray "
      val response : String = KernelConnector.apply(kernelclient).execute(cmd)
      //println("execute:"+response)
      //Ok(jSingleObject("message", jString(response)))
      Ok(jSingleObject("message",jString("")))

    case GET -> Root / "execute2" =>
      val cmd : String = "val sqlContext = new org.apache.spark.sql.SQLContext(sc) ; " +
        "import org.apache.spark.sql.functions._; " +
        "val df=sqlContext.range(1,100000); val a = df.agg(min(\"id\").as(\"minid\"),avg(\"id\")" +
        ".as(\"avgid\")); val b = a.toJSON.toArray().foreach(println); b"
      //val cmd = "Seq(\"strkl\",\"kjghg\",\"hngkw\")"
      val response2 : String = KernelConnector.apply(kernelclient).execute(cmd)
      println("execute2:"+response2)
      //println(Parse.parse(response2))
      Ok(jSingleObject("message", jString(response2)))
      //Ok(jString(response2)

    case GET -> Root / "aggregation" =>
      val cmd : String = "val sqlContext = new org.apache.spark.sql.SQLContext(sc) ; " +
        "import org.apache.spark.sql.functions._; " +
        "val df=sqlContext"+".range(1,100000); val a = df.agg(max(\"id\"),avg(\"id\")); a.show"
      val response2 : String = KernelConnector.apply(kernelclient).execute(cmd)
      println("aggregation:"+response2)
      Ok(jSingleObject("message", jString(response2)))

  }
}
