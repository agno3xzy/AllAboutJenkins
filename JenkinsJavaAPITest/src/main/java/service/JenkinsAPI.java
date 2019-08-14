package service;

import com.offbytwo.jenkins.JenkinsServer;
import org.apache.log4j.BasicConfigurator;
import utils.Constants;

import javax.xml.transform.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class JenkinsAPI {

    private JenkinsServer jenkinsServer;

    public JenkinsAPI(String url, String username, String passwd) {
        BasicConfigurator.configure();
        try {
            jenkinsServer = new JenkinsServer(new URI(url), username, passwd);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void action2Job(int type, String xmlFile, String jobName){
        switch (type){
            case Constants.JOB_CREATE:
                try {
                    jenkinsServer.createJob(jobName, xmlFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Constants.JOB_UPDATE:
                try {
                    jenkinsServer.updateJob(jobName, xmlFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

    }

    public String xml2String(String xmlAddress) throws TransformerException, IOException {
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
        String username = "agno3";
        String passwd = "123";
        String uri = "http://10.1.97.115:8081/";
        String xmlFilePath = "/Users/agno3/Documents/MAKEAGNO3GREATERAGAIN/AllAboutJenkins/JenkinsJavaAPITest/src/main/resources/ownCloud.xml";
        String jobName = "testAutoUpload";
        JenkinsAPI jenkinsAPI = new JenkinsAPI(uri,username,passwd);

        String xmlString = jenkinsAPI.xml2String(xmlFilePath);
        jenkinsAPI.action2Job(Constants.JOB_UPDATE,xmlString,jobName);

    }

}
