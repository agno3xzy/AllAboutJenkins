import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import org.apache.log4j.BasicConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class test {

    public  static String xml2String(String xmlAddress) throws TransformerException, IOException {
       File xmlFile = new File(xmlAddress);
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            sb.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        String output = sb.toString();
        bufferedReader.close();
        return output;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, TransformerException {
        BasicConfigurator.configure();
        JenkinsServer jenkins;
        jenkins = new JenkinsServer(new URI("http://"), "agno3", "");

        jenkins.createJob("testAutoUpload", xml2String("/AllAboutJenkins/JenkinsJavaAPITest/src/main/resources/ownCloud.xml"));


        Map<String, Job> jobs = jenkins.getJobs();

        JobWithDetails job = jobs.get("testAutoUpload").details();

        QueueReference qr = jobs.get("testAutoUpload").build();

        System.out.println(qr.getQueueItemUrlPart());

    }

}
