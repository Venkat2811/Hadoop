import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class Driver extends Configured implements Tool{

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		if (arg0.length != 2) {
            System.err.println("Usage: recipe <in> <out>");
            System.exit(2);
        }
		 Job job = new Job();
		 job.setJarByClass(Driver.class);
	        job.setMapperClass(TokenizerMapper.class);
	       // job.setCombinerClass(IntSumReducer.class);
	        job.setReducerClass(IntSumReducer.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(IntWritable.class);
	        FileInputFormat.addInputPath(job, new Path(arg0[0]));
	        FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
	       // FileInputFormat.addInputPath(job, new Path("hdfs://127.0.0.1:9000/in"));
	       // FileOutputFormat.setOutputPath(job, new Path("hdfs://127.0.0.1:9000/out"));
	        System.exit(job.waitForCompletion(true) ? 0 : 1);
	        boolean success = job.waitForCompletion(true);
	        return success ? 0 : 1;
	}


    
    public static void main(String[] args) throws Exception {
    	Driver driver = new Driver();
    	int exitCode = ToolRunner.run(driver, args);
    	System.exit(exitCode);

    }      
    }