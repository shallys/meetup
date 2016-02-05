package com.ibm.meetup.interactive

import com.typesafe.config.Config
import org.apache.spark.SparkContext
import spark.jobserver._

import scala.util.Try

/**
 * Created by shallysangal on 05/02/16.
 */
class JobServerNamedRdd extends SparkJob with NamedRddSupport {
  override def runJob(sc: SparkContext, jobConfig: Config): Any = {
    val myRDD = sc.parallelize(jobConfig.getString("input.string").split(" "))

    this.namedRdds.update("myRDD",myRDD)

  }

  override def validate(sc: SparkContext, config: Config): SparkJobValidation = {
    /*Try(config.getString("input.string"))
    .map(x=>SparkJobValid)
    .getOrElse(SparkJobInvalid("No input.string config param"))*/
    SparkJobValid

  }
}
