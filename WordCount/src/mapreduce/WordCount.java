


package mapreduce;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;






public class WordCount {

	
	
	public static class Map extends
			Mapper<LongWritable, Text, Text, IntWritable> {


		/*
		 * (non-Javadoc)
		 * @see org.apache.hadoop.mapred.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
		 */
		
		
		@Override
		public void map(LongWritable key, Text value,
				Context context)
				throws IOException,InterruptedException {
			String[] line= value.toString().split("\n");
			StringTokenizer tokenizer = new StringTokenizer(line[0]);

			//Iterating through all the words available in that line and forming the key value pair	
			while (tokenizer.hasMoreTokens()) {
				value.set(tokenizer.nextToken());
				
				//Sending to output collector which inturn passes the same to reducer
				context.write(value, new IntWritable(1));
			}
	}
	}

  //Reducer
	

	
	public static class Reduce extends
			Reducer<Text, IntWritable, Text, IntWritable> {
	


		
		/* (non-Javadoc)
		 * @see org.apache.hadoop.mapred.Reducer#reduce(java.lang.Object, java.util.Iterator, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
		 */
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context)
				throws IOException,InterruptedException {
			int sum=0;
			// TODO Auto-generated method stub
			for(IntWritable x: values)
			{
				sum+=x.get();
			}
			context.write(key, new IntWritable(sum));
			
		}
	}


  //Driver

/**
 * @method main
 * <p>This method is used for setting all the configuration properties.
 * It acts as a driver for map reduce code.
 * @return void
 * @method_arguments args
 * @throws Exception
 */
	
	public static void main(String[] args) throws Exception {

		//Creating a JobConf object and assigning a job name for identification purposes
		Configuration conf= new Configuration();
		Job job = new Job(conf,"wc");
		job.setJarByClass(WordCount.class);

		//Setting configuration object with the Data Type of output Key and Value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//Providing the mapper and reducer class names
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		//Setting format of input and output
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//The hdfs input and output directory to be fetched from the command line
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		//Running the job
		System.exit(job.waitForCompletion(true)?0:1);

	}
}


