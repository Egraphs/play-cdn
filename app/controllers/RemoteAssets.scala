package controllers

import play.api.mvc._
import play.api.Play
import play.api.Play.current
import org.joda.time.format.{ DateTimeFormat, DateTimeFormatter }
import org.joda.time.DateTimeZone
import scala.Some

// Inspired by http://www.jamesward.com/2012/08/08/edge-caching-with-play2-heroku-cloudfront

object RemoteAssets extends Controller {
 
  private val timeZoneCode = "GMT"
 
  private val df: DateTimeFormatter =
    DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss '"+timeZoneCode+"'").withLocale(java.util.Locale.ENGLISH).withZone(DateTimeZone.forID(timeZoneCode))
 
  type ResultWithHeaders = Result { def withHeaders(headers: (String, String)*): Result }
 
  def at(path: String, file: String): Action[AnyContent] = Action { request =>
    val action = Assets.at(path, file)
    val result = action.apply(request)
    val resultWithHeaders = result.asInstanceOf[ResultWithHeaders]
    resultWithHeaders.withHeaders(DATE -> df.print({new java.util.Date}.getTime))
  }
 
  def at(file: String) = {
    val secure = Play.configuration.getBoolean("cdn.secure").getOrElse(true) // default is secure
    val httpOrHttps = if(secure) "https://" else "http://"

    val maybeContentUrl = Play.configuration.getString("cdn.contenturl")
    maybeContentUrl match {
      case Some(contentUrl) => {
        httpOrHttps + contentUrl + controllers.routes.RemoteAssets.at(file).url
      }
      case None => controllers.routes.RemoteAssets.at(file)
    }
  }
 
}