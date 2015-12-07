

package mapreduce;

import org.apache.hadoop.examples.terasort.TeraSort;

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


public class HotAndColdDays {


	
	public static class Map extends
			Mapper<LongWritable, Text, Text, FloatWritable> {

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
			TeraSort sort;
			
			while(tokenizer.hasMoreTokens())
			{
			tokenizer.nextToken();
			String DATE=tokenizer.nextToken();
			/**String date="",month="",year="";
			char temp='\0';
			int i=0;
			for(i=0;i<4;i++)
				year+=String.valueOf(DATE.charAt(i));
			for(;i<6;i++)
				month+=String.valueOf(DATE.charAt(i));
			for(;i<8;i++)
				date+=String.valueOf(DATE.charAt(i));
			
			Date DaTe=new Date(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(date));**/
			
			tokenizer.nextToken();
			tokenizer.nextToken();
			tokenizer.nextToken();
		//Sending to output collector which inturn passes the same to reducer
				context.write(new Text(DATE), new FloatWritable(Float.parseFloat(tokenizer.nextToken())));//MaxTemp
				context.write(new Text(DATE), new FloatWritable(Float.parseFloat(tokenizer.nextToken())));//MinTemp
				
				tokenizer.nextToken("/n");
			}
			
	}
	}

  //Reducer
	

	
	public static class Reduce extends
			Reducer<Text, FloatWritable, Text, Text> {
	

		
		/* (non-Javadoc)
		 * @see org.apache.hadoop.mapred.Reducer#reduce(java.lang.Object, java.util.Iterator, org.apache.hadoop.mapred.OutputCollector, org.apache.hadoop.mapred.Reporter)
		 */
		public void reduce(Text key, Iterable<FloatWritable> values,
				Context context)
				throws IOException,InterruptedException {
			
			Float temperature[]=new Float[2];
			int i=0;
			for(FloatWritable x: values)
			{
				temperature[i]=Float.parseFloat(x.toString());
				i++;
			}
			
			
				if(temperature[0]>40)
					context.write(key, new Text("Hot Day"));
				else if(temperature[1]<10)
					context.write(key, new Text("Cold Day"));
				else
					context.write(key, new Text("Moderate Day"));
					
		
				
			
		}
	}


  //Driver


	
	public static void main(String[] args) throws Exception {

		//Creating a JobConf object and assigning a job name for identification purposes
		Configuration conf= new Configuration();
		Job job = new Job(conf,"wc");
		job.setJarByClass(HotAndColdDays.class);

		//Setting configuration object with the Data Type of output Key and Value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//Providing the mapper and reducer class names
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		//Setting format of input and output
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);

		//The hdfs input and output directory to be fetched from the command line
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		//Running the job
		System.exit(job.waitForCompletion(true)?0:1);

	}
}


