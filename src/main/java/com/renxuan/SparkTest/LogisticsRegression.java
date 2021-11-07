package com.renxuan.SparkTest;

import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class LogisticsRegression {
	public static void main(String []args) {
		// Load training data
		
		SparkSession spark = SparkSession
                .builder()
                .appName("first")
                .master("local[*]")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();

		Dataset<Row> training = spark.read().format("libsvm")
		  .load("data/mllib/sample_libsvm_data.txt");
		 
		LogisticRegression lr = new LogisticRegression()
		  .setMaxIter(10)
		  .setRegParam(0.3)
		  .setElasticNetParam(0.8);
		 
		// Fit the model
		LogisticRegressionModel lrModel = lr.fit(training);
		 
		// Print the coefficients and intercept for logistic regression
		System.out.println("Coefficients: "
		  + lrModel.coefficients() + " Intercept: " + lrModel.intercept());
	}
}
