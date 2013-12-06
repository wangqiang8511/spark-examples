/*** SimpleApp.scala ***/

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.mllib.regression.LabeledPoint


object SimpleMlibApp {
  def main(args: Array[String]) {
    val sc = new SparkContext("local", "Simple App", "/home/woflking/Projects/spark/spark_source/incubator-spark/",
      List("target/scala-2.9.3/simple-mlib-project_2.9.3-1.0.jar"))
    val data = sc.textFile("/home/woflking/Projects/spark/spark_source/incubator-spark/mllib/data/sample_svm_data.txt")
    val parsedData = data.map { line =>
        val parts = line.split(' ')
        LabeledPoint(parts(0).toDouble, parts.tail.map(x => x.toDouble).toArray)
    }

    // Run training algorithm
    val numIterations = 100
    val model = SVMWithSGD.train(parsedData, numIterations)
     
    // Evaluate model on training examples and compute training error
    val labelAndPreds = parsedData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    val trainErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / parsedData.count
    println("trainError = " + trainErr)
  }
}
