package com.atguigu.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimallThirdParthApplicationTests {

    @Resource
    OSSClient ossClient;

    @Test
    public void testUpload(){
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
//        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "LTAI5tQ4m5LRGiR5SpbLgsqL" ;
//        String accessKeySecret = "51NM9plEnZbnFXHiMW5i6rCAp7i3ae";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "gulimall221016";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "hahaha.PNG";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath= "C:/Users/admin/Desktop/shit/1.PNG";

        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    @Test
    void contextLoads() {
    }

}
