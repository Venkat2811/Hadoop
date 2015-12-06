

package mapreduce;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/*
 * All org.apache.hadoop packages can be imported using the jar present in lib 
 * directory of this java project.
 */




public class MaxTemperature {

	
	public static class Map extends
			Mapper<LongWritable, Text, Text, IntWritable> {

	//Mapper
		
		


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
			
			while(tokenizer.hasMoreTokens())
			{
			
				context.write(new Text(tokenizer.nextToken()), new IntWritable(Integer.parseInt(tokenizer.nextToken())));
				
				
				
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
			
			
			int temp=Integer.MIN_VALUE;
			for(IntWritable x: values)
			{
				if(temp<Integer.parseInt(x.toString()))
					temp=Integer.parseInt(x.toString());
			}
			
			context.write(key, new IntWritable(temp));
				
					
		
				
			
		}
	}


  //Driver

	
	public static void main(String[] args) throws Exception {

		//Creating a JobConf object and assigning a job name for identification purposes
		Configuration conf= new Configuration();
		Job job = new Job(conf,"wc");
		job.setJarByClass(MaxTemperature.class);

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


