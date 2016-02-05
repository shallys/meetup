package com.ibm.meetup.interactive

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by shallysangal on 05/02/16.
 */
object HelloWorld {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("HelloWorld")
    val sc = new SparkContext(sparkConf)

    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val df : DataFrame = sqlContext.range(1,11)

    println(df.count())

    Thread.sleep(50000)

    sc.stop()

  }

}
