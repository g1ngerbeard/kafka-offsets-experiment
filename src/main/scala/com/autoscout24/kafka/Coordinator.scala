package com.autoscout24.kafka

import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

object Coordinator extends App {

  val TopicName = "search-classifieds"

  val BootStrapServers = "broker:2181"

  val GroupID = "test-group"

  private def createConsumer(): KafkaConsumer[String, String] = {
    val props: Properties = createKafkaProps()
    new KafkaConsumer[String, String](props)
  }

//  todo: check if created
//  private def createAdmin(groupId: String): AdminClient = {
//    val props: Properties = createKafkaProps(groupId)
//    AdminClient.create(props)
//  }

  private def createKafkaProps() = {
    val props = new Properties()
    props.put("bootstrap.servers",BootStrapServers)
//    props.put("zookeeper",BootStrapServers)
    props.put("group.id", GroupID)
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("session.timeout.ms", "30000")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props
  }

  println("Starting consumer and polling messages from topic...")

  //  1. Create consumers and consume  data
  val consumer = createConsumer()

  println("Subscribing to topic...")

  consumer.subscribe(Seq(TopicName).asJava)

  println("Polling topic...")

  val polledRecords = consumer.poll(30000)

  println("Reading records")

  for {
    record <- polledRecords.records(TopicName).asScala
  } {
    println(record.value())
  }

  println("Committing offsets...")

  //  2. Stop consumers

  //  3. Create temp consumers and reset offsets to 0

  //  4. Verify offsets

}
