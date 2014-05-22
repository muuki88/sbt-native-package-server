package core

import java.util.concurrent.{ Executors, TimeUnit }
import java.nio.file.Files
import java.nio.charset.Charset
import org.apache.logging.log4j.LogManager
import com.typesafe.config._

object Server extends App {

  val log = LogManager getLogger "App"
  val conf = ConfigFactory load () getConfig "heartbeat"

  // Getting the heartbeat log rate
  val logRate = conf getMilliseconds "rate"
  val msg = conf getString "msg"
  log info s"LogRate set to $logRate ms and message $msg"

  // Creating an executor service to schedule the config parsing and heartbeat logging
  val service = Executors newScheduledThreadPool 1
  service.scheduleAtFixedRate(new Runnable() {
    override def run() {
      log info s"$msg"
    }
  }, 0, logRate, TimeUnit.MILLISECONDS)

}
