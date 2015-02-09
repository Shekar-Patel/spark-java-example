package com.bimarian.spark.spark_proj;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

/**
 * @author Rajashekar Yedla
 * 
 */
public class SimpleApp {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
	    String logFile = args[0]; // Should be some file on your system
	    
	    SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
	    JavaSparkContext sc = new JavaSparkContext(conf);
	    JavaRDD<String> logData = sc.textFile(logFile).cache();
	
	    long numAs = logData.filter(new Function<String, Boolean>() {
	      public Boolean call(String s) {
	    	  return s.contains("a");
	      }
	    }).count();
	
	    long numBs = logData.filter(new Function<String, Boolean>() {
	      public Boolean call(String s) {
	    	  return s.contains("b");
	      }
	    }).count();
	
	    System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
	    sc.close();
  }
}