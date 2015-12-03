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

import com.google.gson.Gson;


class Id
{

    public String oid;
}


 class Ts
{

    public long date ;
}

class Roo
{

    public Id _id ;

    public String name ;

    public String ingredients ;

    public String url ;

    public String image ;

    public Ts ts ;

    public String cookTime;

    public String source ;

    public String recipeYield ;
    public String datePublished;

    public String prepTime ;

    public String description;
}


    public class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable>{

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        Gson gson = new Gson();
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
           


            Roo roo=gson.fromJson(value.toString(),Roo.class);
            if(roo.cookTime!=null)
            {
            word.set(roo.cookTime);
            }
            else
            {
                word.set("none");
            }
            context.write(word, one);
        }
    }
    
