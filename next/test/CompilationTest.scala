package org.virtuslab.iskra.test

import org.scalatest.funsuite.AnyFunSuite

class CompilationTest extends AnyFunSuite {
  test("Wrong collect type") {
    assertCompiles("""
      |import org.virtuslab.iskra._
      |implicit lazy val spark: SparkSession = ???
      |val elements = Seq(1, 2, 3).toTypedDF.collectAs[Int]
      |""".stripMargin)

    assertDoesNotCompile("""
      |import org.virtuslab.iskra._
      |implicit lazy val spark: SparkSession = ???
      |val elements = Seq(1, 2, 3).toTypedDF.collectAs[String]
      |""".stripMargin)
  }

  test("Select nonexistent column") {
    assertCompiles("""
      |import org.virtuslab.iskra._
      |case class Foo(string: String)
      |implicit lazy val spark: SparkSession = ???
      |val elements = Seq(Foo("abc")).toTypedDF.select($.string)
      |""".stripMargin)

    assertDoesNotCompile("""
      |import org.virtuslab.iskra._
      |case class Foo(string: String)
      |implicit lazy val spark: SparkSession = ???
      |val elements = Seq(Foo("abc")).toTypedDF.select($.strin)
      |""".stripMargin)
  }

  test("Collect nullable") {
    assertCompiles("""
      |import org.virtuslab.iskra._
      |implicit lazy val spark: SparkSession = ???
      |val result = Seq(1, 2).toTypedDF.collectAs[Int]
      |""".stripMargin)

    assertCompiles("""
      |import org.virtuslab.iskra._
      |implicit lazy val spark: SparkSession = ???
      |val result = Seq(1, 2).toTypedDF.collectAs[Option[Int]]
      |""".stripMargin)

    assertDoesNotCompile("""
      |import org.virtuslab.iskra._
      |implicit lazy val spark: SparkSession = ???
      |val result = Seq(Some(1), None).toTypedDF.collectAs[Int]
      |""".stripMargin)

    assertCompiles("""
      |import org.virtuslab.iskra._
      |implicit lazy val spark: SparkSession = ???
      |val result = Seq(Some(1), None).toTypedDF.collectAs[Option[Int]]
      |""".stripMargin)
  }
}
