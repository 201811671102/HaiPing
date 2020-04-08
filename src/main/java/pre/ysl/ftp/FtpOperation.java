package pre.ysl.ftp;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.ftp.*;
import org.springframework.stereotype.Component;
import pre.ysl.base.ResultUtil;
import pre.ysl.base.dto.ResultDTO;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 类说明：文件上传下载工具类
 *
 */
@Component
@Log4j2
public class FtpOperation {

    private final String userName = "ftpuser";

    private final String passWord = "123";

    private final String ip = "39.96.68.53";

    private final int port = 6060;

    private static StringBuilder FILEPATH ;

    // ftp客户端
    private FTPClient ftpClient = new FTPClient();

    /**
     *
     * 功能：上传文件附件到文件服务器
     * @param buffIn:上传文件流
     * @param fileName：保存文件名称
     * @return
     * @throws IOException
     */
    public boolean uploadToFtp(InputStream buffIn, String fileName, String filepath){
        // 上传文件
        try {
            // 设置传输二进制文件
            setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply))
            {
                ftpClient.disconnect();
                log.info("failed to connect to the FTP Server:"+ip);
                return false;
            }
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(filepath);
            // 上传文件到ftp
            // 输出操作结果信息
            fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
            if (ftpClient.storeFile(fileName, buffIn)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            log.error("ERR : upload file  to ftp : failed! ", e);
        } finally {
            try {
                if (buffIn != null) {
                    buffIn.close();
                }
            } catch (Exception e) {
                log.error("ftp关闭输入流时失败！", e);
            }
        }
        return true;
    }


    /**
     *
     * 功能：根据文件名称，下载文件流
     * @param filename
     * @return
     * @throws IOException
     */
    public InputStream  downloadFile(String filename,String filepath){
        InputStream in=null;
        try {
            ftpClient.enterLocalPassiveMode();
            // 设置传输二进制文件
            setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply))
            {
                log.info("failed to connect to the FTP Server:"+ip);
                return null;
            }
            ftpClient.changeWorkingDirectory(filepath);
            // ftp文件获取文件
            filename = new String(filename.getBytes("utf-8"),"iso-8859-1");
            in=ftpClient.retrieveFileStream(filename);
            return in;
        }catch (Exception e) {
            log.error("ERR : upload file "+ filename+ " from ftp : failed!", e);
            return null;
        }
    }


    public boolean delectFile(String filename,String filepath){
        try {
            filename = new String(filename.getBytes("utf-8"),"iso-8859-1");
           ftpClient.changeWorkingDirectory(filepath);
            if (!ftpClient.deleteFile(filename)){
                return false;
            }
            return true;
        }catch (Exception e) {
            log.error("ERR : delete file "+ filename+ " from ftp : failed!", e);
            return false;
        }
    }
    /**
     * 设置传输文件的类型[文本文件或者二进制文件]
     *
     * @param fileType
     *            --BINARY_FILE_TYPE、ASCII_FILE_TYPE
     */
    private void setFileType(int fileType) {
        try {
            ftpClient.setFileType(fileType);
        } catch (Exception e) {
            log.error("ftp设置传输文件的类型时失败！", e);
        }
    }

    /**
     *
     * 功能：关闭连接
     */
    public void closeConnect() {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (Exception e) {
            log.error("ftp连接关闭失败！", e);
        }
    }

    /**
     * 连接到ftp服务器
     */
    public void connectToServer() throws FTPConnectionClosedException,Exception {
        if (!ftpClient.isConnected()) {
            int reply;
            try {
                ftpClient=new FTPClient();
                ftpClient.connect(ip,port);
                ftpClient.login(userName,passWord);
                reply = ftpClient.getReplyCode();

                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftpClient.disconnect();
                    log.info("connectToServer FTP server refused connection.");
                }
            }catch(FTPConnectionClosedException ex){
                log.error("服务器:IP："+ip+"没有连接数！there are too many connected users,please try later", ex);
                throw ex;
            }catch (Exception e) {
                log.error("登录ftp服务器【"+ip+"】失败", e);
                throw e;
            }
        }
    }
    // Check the path is exist; exist return true, else false.
    public boolean existDirectory(String path) throws Exception {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        for (FTPFile ftpFile : ftpFileArr) {
            System.out.println("-------------"+ftpFile);
            if (ftpFile.isDirectory()
                    && ftpFile.getName().equalsIgnoreCase(path)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    /**
     * 创建FTP文件夹目录
     */
    public String createDirectory(String filePath) throws Exception {
        FILEPATH = new StringBuilder();
        FILEPATH.append(filePath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String strdate = simpleDateFormat.format(date);
        String[] strdates = strdate.split("-");
        boolean isSuccess=false;
        for (int i = 0 ; i < strdates.length ; i++){
            try{
                isSuccess=ftpClient.makeDirectory(FILEPATH.toString()+"/"+strdates[i]);
                FILEPATH.append("/"+strdates[i]);
            }catch(Exception e){
                e.printStackTrace();
                for(int j = 0 ; j < i ; j++){
                    ftpClient.removeDirectory(strdates[j]);
                }
                log.info("创建文件夹失败");
                isSuccess = false;
                FILEPATH.delete(0,FILEPATH.length());
                break;
            }
        }
        if (isSuccess == true){
            return FILEPATH.toString();
        }else{
            return null;
        }
    }
}