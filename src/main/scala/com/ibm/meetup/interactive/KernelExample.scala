package com.ibm.meetup.interactive

import com.typesafe.config.{ConfigFactory, Config}
import org.apache.toree.kernel.protocol.v5.MIMEType
import org.apache.toree.kernel.protocol.v5.client.boot.ClientBootstrap
import org.apache.toree.kernel.protocol.v5.client.boot.layers.{StandardHandlerInitialization, StandardSystemInitialization}
import org.apache.toree.kernel.protocol.v5.content._

/**
 * Created by shallysangal on 05/02/16.
 */

class KernelExample {

  def apply() = {
    val profileJSON: String = """
  {
      "stdin_port" : 48691,
      "control_port" : 44808,
      "hb_port" : 49691,
      "shell_port" : 40544,
      "iopub_port" : 43462,
      "ip" : "127.0.0.1",
      "transport" : "tcp",
      "signature_scheme" : "hmac-sha256",
      "key" : ""
  } """.stripMargin

    val config: Config = ConfigFactory.parseString(profileJSON)

    val client = (new ClientBootstrap(config)
      with StandardSystemInitialization
      with StandardHandlerInitialization).createClient()
    
    client.execute("import sqlContext.implicits._; import org.apache.spark.sql.functions._;" +
      "case class Cust(id: Integer, name: String, sales: Double, discounts: Double, country: String); " +
      "val custs = Seq(\n      Cust(1, \"Customer1\", 120000.00, 100.00, \"USA\"),\n      Cust(2," +
      " \"Customer2\", 410500.00, 500.00, \"IND\"),\n      Cust(3, \"Customer3\", 410500.00, 200.00, \"IND\")" +
      ",\n      Cust(4, \"Customer4\", 410500.00, 0.0, \"IND\"),\n      " +
      "Cust(5, \"Customer5\", 500.00, 0.0, \"UK\")\n    );" +
      "val testdf = sc.parallelize(custs, 4).toDF(); " +
      "val a = testdf.groupBy(\"country\").agg(avg(\"sales\")); testdf.show ; a.show")
      .onSuccess(printSuccess)
      .onResult(printResult)
      .onStream(printStreamContent)
      .onError(printError)

    Thread.sleep(10000)

    client.shutdown()


  }

  def printResult(result: ExecuteResult) = {
    println(s"Result was: ${result.data.get(MIMEType.PlainText).get}")
  }

  def printStreamContent(content: StreamContent): Unit = {
    println(s"Stream content was: " + content.text)
  }

  def printSuccess(executeReplyOk: ExecuteReplyOk) = {
    println(s"Successful code completion")
  }

  def printError(reply: ExecuteReplyError) = {
    println(s"Error was: ${reply.evalue.get}")
  }

}

object KernelExample extends App {
  val ke = new KernelExample
  ke()
}