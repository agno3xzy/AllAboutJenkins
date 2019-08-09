import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class test {


    public static void main(String[] args) throws IOException, URISyntaxException {
        BasicConfigurator.configure();

        JenkinsServer jenkins;

        jenkins = new JenkinsServer(new URI("http://****:8081/"), "***", "***");

        Map<String, Job> jobs = jenkins.getJobs();

        JobWithDetails job = jobs.get("testott").details();

        QueueReference qr = jobs.get("testott").build();

        System.out.println(qr.getQueueItemUrlPart());

    }

}
