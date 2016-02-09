This repo contains code shown in meetup :

http://www.meetup.com/Bangalore-Spark-Enthusiasts/events/228030045/

 * HelloWorld - spark-submit example
 * JobServer* - spark job server examples
 * KernelExample - spark kernel example shown in demo
 * KernelAPI, KernelConnector, KernelUtil, Server - provide a very basic REST wrapper over Kernel

You need Spark installed.

To build and run Kernel:

https://github.com/ibm-et/spark-kernel/wiki/Getting-Started-with-the-Spark-Kernel

To build and publish client libraries to local repo:

https://github.com/ibm-et/spark-kernel/wiki/Guide-for-the-Spark-Kernel-Client

To build and start Job Server:

https://github.com/spark-jobserver/spark-jobserver#development-mode

Find sample job server calls:

https://github.com/spark-jobserver/spark-jobserver

Build of code:

sbt package

Run KernelExample:

sbt "run-main com.ibm.meetup.interactive.KernelExample"



