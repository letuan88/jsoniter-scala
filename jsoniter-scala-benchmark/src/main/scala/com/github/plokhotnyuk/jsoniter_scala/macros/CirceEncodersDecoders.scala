package com.github.plokhotnyuk.jsoniter_scala.macros

import java.time.ZoneOffset

import com.github.plokhotnyuk.jsoniter_scala.macros.SuitEnum.SuitEnum
import io.circe._
import io.circe.generic.extras._
import io.circe.generic.extras.semiauto._

import scala.util.control.NonFatal

object CirceEncodersDecoders {
  val printer: Printer = Printer.noSpaces.copy(dropNullValues = true, reuseWriters = true)
  val escapingPrinter: Printer = printer.copy(escapeNonAscii = true)
  implicit val config: Configuration = Configuration.default.withDiscriminator("type")
  implicit val aEncoder: Encoder[A] = deriveEncoder
  implicit val aDecoder: Decoder[A] = deriveDecoder
  implicit val bEncoder: Encoder[B] = deriveEncoder
  implicit val bDecoder: Decoder[B] = deriveDecoder
  implicit val cEncoder: Encoder[C] = deriveEncoder
  implicit val cDecoder: Decoder[C] = deriveDecoder
  implicit val adtEncoder: Encoder[AdtBase] = deriveEncoder
  implicit val adtDecoder: Decoder[AdtBase] = deriveDecoder
  implicit val enumEncoder: Encoder[SuitEnum] = Encoder.enumEncoder(SuitEnum)
  implicit val enumDecoder: Decoder[SuitEnum] = Decoder.enumDecoder(SuitEnum)
  implicit val suitEncoder: Encoder[Suit] = Encoder.encodeString.contramap(_.name)
  implicit val suitDecoder: Decoder[Suit] = Decoder.decodeString.emap { str =>
    try Right(Suit.valueOf(str)) catch {
      case NonFatal(_) => Left("Suit")
    }
  }
  implicit val zoneOffsetEncoder: Encoder[ZoneOffset] = Encoder.encodeString.contramap(_.toString)
  implicit val zoneOffsetDecoder: Decoder[ZoneOffset] = Decoder.decodeString.emap { str =>
    try Right(ZoneOffset.of(str)) catch {
      case NonFatal(_) => Left("ZoneOffset")
    }
  }
  // GeoJSON
  implicit val featureEncoder: Encoder[Feature] = deriveEncoder
  implicit val featureDecoder: Decoder[Feature] = deriveDecoder
  implicit val featureCollectionEncoder: Encoder[FeatureCollection] = deriveEncoder
  implicit val featureCollectionDecoder: Decoder[FeatureCollection] = deriveDecoder
  implicit val geoJSONEncoder: Encoder[GeoJSON] = deriveEncoder
  implicit val geoJSONDecoder: Decoder[GeoJSON] = deriveDecoder
  implicit val pointEncoder: Encoder[Point] = deriveEncoder
  implicit val pointDecoder: Decoder[Point] = deriveDecoder
  implicit val multiPointEncoder: Encoder[MultiPoint] = deriveEncoder
  implicit val multiPointDecoder: Decoder[MultiPoint] = deriveDecoder
  implicit val lineStringEncoder: Encoder[LineString] = deriveEncoder
  implicit val lineStringDecoder: Decoder[LineString] = deriveDecoder
  implicit val multiLineStringEncoder: Encoder[MultiLineString] = deriveEncoder
  implicit val multiLineStringDecoder: Decoder[MultiLineString] = deriveDecoder
  implicit val polygonEncoder: Encoder[Polygon] = deriveEncoder
  implicit val polygonDecoder: Decoder[Polygon] = deriveDecoder
  implicit val multiPolygonEncoder: Encoder[MultiPolygon] = deriveEncoder
  implicit val multiPolygonDecoder: Decoder[MultiPolygon] = deriveDecoder
  implicit val geometryCollectionEncoder: Encoder[GeometryCollection] = deriveEncoder
  implicit val geometryCollectionDecoder: Decoder[GeometryCollection] = deriveDecoder
  implicit val geometryEncoder: Encoder[Geometry] = deriveEncoder
  implicit val geometryDecoder: Decoder[Geometry] = deriveDecoder
}