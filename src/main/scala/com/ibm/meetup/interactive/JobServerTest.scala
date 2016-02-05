package com.ibm.meetup.interactive

import com.typesafe.config.Config
import org.apache.spark.SparkContext
import spark.jobserver.{SparkJobValid, SparkJobValidation, SparkJob}

/**
 * Created by shallysangal on 04/02/16.
 */
class JobServerTest extends SparkJob{

  def runJob(sc: SparkContext, jobConfig: Config): Any = {
    val dd = sc.parallelize(Seq(2, 4, 9, 16, 25, 36, 55, 66))
      .map(_ * 2)

    dd.sum().toInt
  }

  def validate(sc: SparkContext, config: Config): SparkJobValidation = {
    SparkJobValid
  }

}
