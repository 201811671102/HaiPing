import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pre.ysl.App;
import pre.ysl.base.mail.MailBase;
import pre.ysl.ftp.FtpOperation;
import pre.ysl.mail.SendMail;
import pre.ysl.mapper.VolunteerMapper;
import pre.ysl.pojo.Position;
import pre.ysl.redis.RedisUtil;
import pre.ysl.service.PositionService;
import pre.ysl.service.UserService;

import java.io.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={App.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class test {
    @Autowired
    VolunteerMapper volunteerMapper;
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    PositionService positionService;



    @Test
    public void redis(){
        StringBuilder builder = new StringBuilder();
        builder.append("1231");
        System.out.println("--------"+builder.length());
        System.out.println("----------"+builder.toString());
        //System.out.println("----------------->"+redisUtil.get("519706545@qq.com"));
    }

    @Autowired
    SolrClient solrClient;


    @Autowired
    private FtpOperation ftpOperation;
    @Test
    public void update() throws Exception {
        String filename = "ftp://39.96.68.53:6060/resume/bbb.txt";
        filename = new String(filename.getBytes("utf-8"),"iso-8859-1");
        filename = filename.substring(filename.lastIndexOf("/")+1);
        System.out.println("-------------"+filename);
        ftpOperation.connectToServer();
        System.out.println("-------------"+ftpOperation.delectFile(filename, "/resume"));
        ftpOperation.closeConnect();
    }
    @Autowired
    private SendMail sendMail;
    @Test
    public  void FTP() throws Exception {
        MailBase mailBase = new MailBase();
        mailBase.setSubject("海聘");
        mailBase.setContent("的简历");
        mailBase.setRecipient("519706545@qq.com");
        ftpOperation.connectToServer();
        String fileName = "ftp://39.96.68.53:6060/resume/bc2b24d5ea724ab2882445d17017c047信管1181陈干.docx";
        fileName = fileName.substring(fileName.lastIndexOf("/")+1);
        InputStream inputStream = ftpOperation.downloadFile(fileName,"/resume");
        System.out.println("------------------>"+fileName);
        System.out.println("------------------>"+inputStream.read());
        sendMail.sendAttachmentMail(mailBase,fileName,inputStream);
        ftpOperation.closeConnect();
    }
    @Test
    public void email(){
        MailBase mailBase = new MailBase();
        String message  = "已经被删除\n原因:";
        mailBase.setSubject("海聘:招聘信息删除");
        mailBase.setContent(message);
        mailBase.setRecipient("519706545@qq.com");
        sendMail.sendSimpleMail(mailBase);
    }

    @Test
    public void str(){
        System.out.println("--------------------"+userService.selectByUid(1).getUemail());
    }
}
