package org.virtuslab.iskra
package api

export DataFrameBuilders.toTypedDF
export types.{
  DataType,
  BooleanType,
  BooleanOptType,
  StringType,
  StringOptType,
  ByteType,
  ByteOptType,
  ShortType,
  ShortOptType,
  IntegerType,
  IntegerOptType,
  LongType,
  LongOptType,
  FloatType,
  FloatOptType,
  DoubleType,
  DoubleOptType,
  StructType,
  StructOptType
}
export UntypedOps.typed
export org.virtuslab.iskra.$
export org.virtuslab.iskra.{Column, DataFrame, UntypedColumn, UntypedDataFrame, :=, /}

object functions:
  export org.virtuslab.iskra.functions.{lit, when}
  export org.virtuslab.iskra.functions.Aggregates.*

export org.apache.spark.sql.SparkSession
