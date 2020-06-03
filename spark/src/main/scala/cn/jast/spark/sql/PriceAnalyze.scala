package cn.jast.spark.sql

import org.apache.spark.sql.SparkSession

object PriceAnalyze {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("priceAnalyze").getOrCreate()
    val df = spark.read.json("/Users/zhangzhiwen/prices/jsonl")
    df.printSchema()
    df.createOrReplaceTempView("price")
    val sqlDF = spark.sql("select farmProduceName,averagePrice,maxPrice,minPrice from price where farmProduceCode='AA01002' order by maxPrice desc limit 10")
    sqlDF.show()
    spark.stop()
  }
}
