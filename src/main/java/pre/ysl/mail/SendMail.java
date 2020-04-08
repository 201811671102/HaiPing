package pre.ysl.mail;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pre.ysl.base.ResultUtil;
import pre.ysl.base.dto.ResultDTO;
import pre.ysl.base.mail.MailBase;


import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;

@Component
public class SendMail {
    @Autowired
    private JavaMailSender javaMailSender;
    /*发送简单邮件*/
    public ResultDTO sendSimpleMail(MailBase mailbase){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送人
        simpleMailMessage.setFrom(MailBase.SENDER);
        //邮件介接收人
        simpleMailMessage.setTo(mailbase.getRecipient());
        //邮件主题
        simpleMailMessage.setSubject(mailbase.getSubject());
        //邮件内容
        simpleMailMessage.setText(mailbase.getContent());
        javaMailSender.send(simpleMailMessage);
        return new ResultUtil().Success("成功发送邮件到"+mailbase.getRecipient());
    }
    /*发送html格式的邮件*/
    public ResultDTO sendHTMLMail(MailBase mailbase) throws MessagingException {
        MimeMessage mimeMailMessage = null;
        mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(MailBase.SENDER);
        mimeMessageHelper.setTo(mailbase.getRecipient());
        mimeMessageHelper.setSubject(mailbase.getSubject());
        mimeMessageHelper.setText(mailbase.getContent(), true);
        javaMailSender.send(mimeMailMessage);
        return new ResultUtil().Success("成功发送邮件到"+mailbase.getRecipient());
    }
    /* 发送带附件格式的邮件*/
    public ResultDTO sendAttachmentMail(MailBase mailbase, String fileName, InputStream inputStream) throws MessagingException, IOException {
        MimeMessage mimeMailMessage = null;
        mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(MailBase.SENDER);
        mimeMessageHelper.setTo(mailbase.getRecipient());
        mimeMessageHelper.setSubject(mailbase.getSubject());
        mimeMessageHelper.setText(mailbase.getContent());
        mimeMessageHelper.addAttachment(fileName,new ByteArrayResource(IOUtils.toByteArray(inputStream)));
        javaMailSender.send(mimeMailMessage);
        return new ResultUtil().Success("成功发送邮件到"+mailbase.getRecipient());
    }
    /*发邮件发送之内嵌图片*/
    public ResultDTO sendInlineMail(MailBase mailbase,String filePath) throws MessagingException {
        MimeMessage mimeMailMessage = null;
        mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(MailBase.SENDER);
        mimeMessageHelper.setTo(mailbase.getRecipient());
        mimeMessageHelper.setSubject(mailbase.getSubject());
        mimeMessageHelper.setText("<html><body><img src='cid:picture' /></body></html>", true);
        //文件路径
        FileSystemResource file = new FileSystemResource(new File(filePath));
        mimeMessageHelper.addInline("picture", file);
        javaMailSender.send(mimeMailMessage);
        return new ResultUtil().Success("成功发送邮件到"+mailbase.getRecipient());
    }
}
