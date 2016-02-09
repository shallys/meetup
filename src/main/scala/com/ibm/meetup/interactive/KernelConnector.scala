package com.ibm.meetup.interactive

/**
 * Created by shallysangal on 01/02/16.
 */

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.toree.kernel.protocol.v5.MIMEType
import org.apache.toree.kernel.protocol.v5.client.SparkKernelClient
import org.apache.toree.kernel.protocol.v5.client.boot.ClientBootstrap
import org.apache.toree.kernel.protocol.v5.client.boot.layers.{StandardHandlerInitialization, StandardSystemInitialization}
import org.apache.toree.kernel.protocol.v5.content._

class KernelConnector(client : SparkKernelClient) {

  private var myContent: String = _

  def execute(cmd : String) : String = {

    this.client.execute(cmd)
      .onSuccess(printSuccess)
      .onResult(printResult)
      .onStream(printStreamContent)
      .onError(printError)

    Thread.sleep(5000)

    this.myContent

  }

  def stop() = {
    client.shutdown()
  }

  def printResult(result: ExecuteResult) = {
    println(s"Result was: ${result.data.get(MIMEType.ApplicationJson).get}")
  }

  def printStreamContent(content: StreamContent): Unit = {
    this.myContent = content.text
    println(s"Stream content was: " + this.myContent)
  }

  def printSuccess(executeReplyOk: ExecuteReplyOk) = {
    println(s"Successful code completion")
  }

  def printError(reply: ExecuteReplyError) = {
    println(s"Error was: ${reply.evalue.get}")
  }
}

object KernelConnector {

  def apply(client : SparkKernelClient) = {
    new KernelConnector(client)
  }
}
