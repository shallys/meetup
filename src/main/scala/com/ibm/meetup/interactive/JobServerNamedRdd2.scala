package com.ibm.meetup.interactive

import com.typesafe.config.Config
import org.apache.spark.SparkContext
import spark.jobserver.{SparkJobValid, SparkJobValidation, NamedRddSupport, SparkJob}

/**
 * Created by shallysangal on 05/02/16.
 */
class JobServerNamedRdd2 extends SparkJob with NamedRddSupport  {

  override def runJob(sc: SparkContext, jobConfig: Config): Any = {
    //val myRDD = sc.parallelize(jobConfig.getString("input.string").split(" "))

    val myRDD = this.namedRdds.get[String]("myRDD").get

    println("got this rdd :"+myRDD)

    //this.namedRdds.update("myrdd",myRDD)
    myRDD.filter(_.length>1).collect()

  }

  override def validate(sc: SparkContext, config: Config): SparkJobValidation = {
    /*Try(config.getString("input.string"))
    .map(x=>SparkJobValid)
    .getOrElse(SparkJobInvalid("No input.string config param"))*/
    SparkJobValid

  }


}
