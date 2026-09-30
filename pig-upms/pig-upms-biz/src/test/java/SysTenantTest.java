import java.net.URI;
import java.net.http.*;
import java.time.LocalDateTime;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class SysTenantTest {
    static String TOKEN;
    static String BASE = "http://127.0.0.1:9999";
    static HttpClient CLIENT = HttpClient.newHttpClient();
    static List<String[]> results = new ArrayList<>();
    static String createdUserId;
    static String createdTenantId;
    static String createdDatasourceId;
    static String createdRoleId;
    static String createdPermId;
    static String createdClientId;
    static String createdScopeId;
    static String P = "t" + (System.currentTimeMillis() % 10000000);
    static String password = "lengLeng001";
    // static String phone = "+8613800000001";
    static String KEY_ALGORITHM = "AES";
    static String ENCODE_KEY = "thanks,pig4cloud";
    static Long CLIENT_ID=1L;
    static String CLIENT_NAME="pig-upms";

    public static void main(String[] args) throws Exception {
        String tokenResp = postForm(BASE + "/auth/oauth2/token",
            "username=admin&password=3cbf0057aefcc4fc&grant_type=password&scope=server",
            "Basic cGlnLXVwbXM6cGln", CLIENT_NAME);
        TOKEN = extractField(tokenResp, "access_token");
        if (TOKEN == null) { System.out.println("FATAL: Cannot get token: " + tokenResp); return; }
        System.out.println("Token OK");

        // ===== 4.9 TM 租户基本信息管理 =====
        // test("TM-01-01", "创建租户", () -> {
        //     String tenantCode = P + "TM0101";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        // });
        // test("TM-01-02", "创建租户-租户编码唯一", () -> {
        //     String tenantCode = P + "TM0102";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     tenantCode = P + "TM0102";
        //     r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 1);
        // });
        // test("TM-01-03", "创建租户-缺少必要属性", () -> {
        //     String tenantCode = P + "TM0103";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\"");
        //     checkCode(r, 1);
        // });
        // test("TM-02-01", "修改租户", () -> {
        //     String tenantCode = P + "TM0201";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1&tenantCode="+tenantCode);
        //     String tenantId = extractNestedField(page, "records", "id");
        //     //租户代码不能被修改
        //     String tenantCodeUpdate = P + "TM020101";
        //     r = putCall(BASE+"/admin/tenant", "{\"id\":"+tenantId+",\"tenantCode\":\""+tenantCodeUpdate+"\",\"name\":\"测试租户改_update\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant/page?current=1&size=1&tenantCode="+tenantCode);
        //     checkContains(r, tenantCode);
        // });
        // test("TM-02-02", "修改租户-缺少必要参数", () -> {
        //     String tenantCode = P + "TM0202";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1&tenantCode="+tenantCode);
        //     String tenantId = extractNestedField(page, "records", "id");
        //     //租户代码不能被修改
        //     String tenantCodeUpdate = P + "TM020101";
        //     r = putCall(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCodeUpdate+"\",\"name\":\"测试租户改_update\"}");
        //     checkCode(r, 1);
        // });
        // test("TM-03-01", "删除租户", () -> {
        //     String tenantCode = P + "TM0301";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1&tenantCode="+tenantCode);
        //     String tenantId = extractNestedField(page, "records", "id");

        //     r = deleteCall(BASE+"/admin/tenant/"+tenantId);
        //     checkCode(r, 0);
        // });
        // test("TM-03-02", "删除租户后用户不可用", () -> {
        //     checkTrue(true, "需人工验证：删除租户后其下用户无法登录");
        // });
        // test("TM-04-01", "启用租户", () -> {
        //     String tenantCode = P + "TM0401";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1&tenantCode="+tenantCode);
        //     String tenantId = extractNestedField(page, "records", "id");

        //     r = putCall(BASE+"/admin/tenant/enable/"+tenantId, "");
        //     checkCode(r, 0);
        // });
        // test("TM-04-02", "禁用租户", () -> {
        //     String tenantCode = P + "TM0402";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1&tenantCode="+tenantCode);
        //     String tenantId = extractNestedField(page, "records", "id");

        //     r = putCall(BASE+"/admin/tenant/disable/"+tenantId, "");
        //     checkCode(r, 0);
        // });
        // test("TM-04-03", "禁用后用户无法登录", () -> {
        //     checkTrue(true, "需人工验证：禁用租户后其下所有用户无法登录");
        // });

        // ===== 4.10 DC 租户数据源配置管理 =====
        // test("DC-01-01", "创建数据源配置", () -> {
        //     String tenantCode = P + "DC0101";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1&tenantCode="+tenantCode);
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0101";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");

        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);
        //     r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=100");
        //     createdDatasourceId = extractNestedField(r, "records", "id");
            
        // });
        // test("DC-01-02", "创建数据源配置-唯一性验证", () -> {
        //     String tenantCode = P + "DC0102";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0102";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");

        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);            

        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 1);  
            
        // });
        // test("DC-01-03", "创建数据源配置-缺少必要属性", () -> {
        //     String tenantCode = P + "DC0103";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0103";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");

        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 1);
            
        // });
        // test("DC-02-01", "修改数据源配置", () -> {
        //     String tenantCode = P + "DC0201";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0201";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");
        //     //创建数据源
        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=1&&tenantId=" +tenantId+ "&clientId="+clientId);
        //     String id = extractField(r, "id");

        //     //修改数据源
        //     r = putCall(BASE+"/admin/tenant-datasource",
        //         "{\"id\":"+id+",\"dbType\":\"MYSQL\",\"host\":\"192.168.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root-update\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);
            
        // });
        // test("DC-02-02", "修改数据源-缺少ID", () -> {
        //     String tenantCode = P + "DC0201";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0201";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");
        //     //创建数据源
        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=1&&tenantId=" +tenantId+ "&clientId="+clientId);
        //     String id = extractField(r, "id");

        //     //修改数据源
        //     r = putCall(BASE+"/admin/tenant-datasource",
        //         "{\"dbType\":\"MYSQL\",\"host\":\"192.168.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root-update\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 1);
        // });
        // test("DC-02-03", "修改数据源-验证不能修改的属性", () -> {
        //     String tenantCode = P + "DC0201";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0201";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");
        //     //创建数据源
        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=1&&tenantId=" +tenantId+ "&clientId="+clientId);
        //     String id = extractField(r, "id");

        //     //修改数据源
        //     r = putCall(BASE+"/admin/tenant-datasource",
        //         "{\"id\":"+id+",\"tenantId\":"+9999+",\"clientId\":"+99999+",\"dbType\":\"MYSQL\",\"host\":\"192.168.0.1\",\"port\":3306,\"dbName\":\""+dbName+"-update\",\"username\":\"root-update\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     tenantId.equals(extractField(r, "tenantId"));
        //     clientId.equals(extractField(r, "clientId"));
        //     ""+dbName+"".equals(extractField(r, "dbName"));
        // });
        // test("DC-03-01", "查询数据源-密码脱敏", () -> {
        //     String tenantCode = P + "DC0301";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0301";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");
        //     //创建数据源
        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=1&&tenantId=" +tenantId+ "&clientId="+clientId);
        //     String id = extractField(r, "id");
            
        //     r = getCall(BASE+"/admin/tenant-datasource/"+id);

        //     checkCode(r, 0);
        //     checkContains(r, "******");
        // });
        // test("DC-03-02", "分页查询-密码脱敏", () -> {
        //     String tenantCode = P + "DC0302";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0302";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");
        //     //创建数据源
        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=1&&tenantId=" +tenantId+ "&clientId="+clientId);
            
        //     checkCode(r, 0);
        //     checkContains(r, "******");
        // });
        // test("DC-04-01", "服务间获取-含解密密码", () -> {
        //     String tenantCode = P + "DC0401";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0401";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");
        //     //创建数据源
        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant-datasource/tenant/"+tenantId);
        //     System.out.print("ds ->" + r);
        //     String passwordEncrypted = extractField(r, "passwordEncrypted");

        //     checkCode(r, 0);
        //     checkTrue("001Sea001!".equals(passwordEncrypted), "错误密码");
        // });
        
        // test("DC-05-01", "测试连通性-成功", () -> {
        //     String tenantCode = P + "DC0501";
        //     String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
        //     checkCode(r, 0);

        //     String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
        //     String tenantId = extractNestedField(page, "records", "id");
            
        //     String clientId = "test_client_DC_0501";
        //     String clientName = "测试客户端";
        //     String clientSecret = "test_secret";
        //     String scope = "server";
        //     String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
        //     r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
        //     clientId = extractField(r, "id");
        //     String dbName = "pig_upms_testing";
        //     //创建数据源
        //     r = postJson(BASE+"/admin/tenant-datasource",
        //         "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=1&&tenantId=" +tenantId+ "&clientId="+clientId);
        //     String id = extractField(r, "id");

        //     r = getCall(BASE+"/admin/tenant-datasource/test/"+id);
        //     checkCode(r, 0);
        // });
        test("DC-05-02", "测试连通性-失败", () -> {
            String tenantCode = P + "DC0502";
            String r = postJson(BASE+"/admin/tenant", "{\"tenantCode\":\""+tenantCode+"\",\"name\":\"测试租户\"}");
            checkCode(r, 0);

            String page = getCall(BASE+"/admin/tenant/page?current=1&size=1");
            String tenantId = extractNestedField(page, "records", "id");
            
            String clientId = "test_client_DC_0502";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
            clientId = extractField(r, "id");
            String dbName = "pig_upms_testing";
            //创建数据源
            r = postJson(BASE+"/admin/tenant-datasource",
                "{\"tenantId\":"+tenantId+",\"clientId\":"+clientId+",\"dbType\":\"MYSQL\",\"host\":\"127.0.0.1\",\"port\":3306,\"dbName\":\""+dbName+"\",\"username\":\"root\",\"passwordEncrypted\":\"001Sea001!\"}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/tenant-datasource/page?current=1&size=1&&tenantId=" +tenantId+ "&clientId="+clientId);
            String id = extractField(r, "id");

            r = getCall(BASE+"/admin/tenant-datasource/test/"+999);
            checkCode(r, 1);
        });
        // ===== Report =====
        System.out.println("\n========== TEST REPORT ==========");
        System.out.println("Date: " + LocalDateTime.now());
        System.out.println("Environment: pig-gateway(9999) → pig-upms(4000) + pig-auth(3000)");
        System.out.println("Database: pig_upms @ 127.0.0.1:3306");
        System.out.println();
        int pass=0, fail=0, skip=0;
        System.out.printf("%-12s %-45s %-6s %s%n", "ID", "Name", "Result", "Detail");
        System.out.println("-".repeat(100));
        for (String[] r : results) {
            System.out.printf("%-12s %-45s %-6s %s%n", r[0], r[1], r[2], r[3]);
            if ("PASS".equals(r[2])) pass++; else if ("FAIL".equals(r[2])) fail++; else skip++;
        }
        System.out.println("-".repeat(100));
        System.out.printf("TOTAL: %d  PASS: %d  FAIL: %d  SKIP: %d  PassRate: %.1f%%%n",
            pass+fail+skip, pass, fail, skip, (pass+fail)==0?0:(pass*100.0/(pass+fail)));
        System.out.printf("Coverage: %d test plan items%n", pass+fail+skip);
    }

    static void test(String id, String name, Thunk action) {
        String detail = "";
        String status = "PASS";
        try { action.run(); }
        catch (AssertionError e) { status = "FAIL"; detail = e.getMessage(); }
        catch (Exception e) { status = "FAIL"; detail = e.getClass().getSimpleName() + ": " + e.getMessage(); if (detail.length()>80) detail=detail.substring(0,80); }
        results.add(new String[]{id, name, status, detail});
    }

    static void checkCode(String json, int expected) {
        String code = extractField(json, "code");
        if (code == null) throw new AssertionError("No code field: " + abbrev(json,100));
        if (Integer.parseInt(code) != expected) throw new AssertionError("code=" + code + " msg=" + extractField(json,"msg"));
    }
    static void checkContains(String json, String expected) {
        if (json == null || !json.contains(expected)) throw new AssertionError("Not contains '" + expected + "': " + abbrev(json,100));
    }
    static void checkTrue(boolean cond, String msg) { if (!cond) throw new AssertionError(msg); }
    static String abbrev(String s, int max) { return s==null?"null":(s.length()>max?s.substring(0,max)+"...":s); }

    static String extractField(String json, String field) {
        if (json == null) return null;
        String pattern = "\"" + field + "\":\"";
        int i = json.indexOf(pattern);
        if (i >= 0) { i += pattern.length(); int j = json.indexOf('"', i); return j>i?json.substring(i,j):null; }
        pattern = "\"" + field + "\":"; i = json.indexOf(pattern);
        if (i < 0) return null;
        i += pattern.length();
        if (i >= json.length()) return null;
        if (json.charAt(i) == '"') { int j = json.indexOf('"', i+1); return j>i?json.substring(i+1,j):null; }
        int j = i; while (j < json.length() && ",}]".indexOf(json.charAt(j)) < 0) j++;
        return json.substring(i, j);
    }

    static String extractNestedField(String json, String outer, String inner) {
        if (json == null) return null;
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\"" + inner + "\":\"?(\\d+)\"?").matcher(json);
        return m.find() ? m.group(1) : null;
    }

    static String getCall(String url) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+TOKEN).header("client_id",CLIENT_NAME).GET().build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
    static String getCall(String url, String token) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+token).header("client_id",CLIENT_NAME).GET().build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
    static String getCallNoToken(String url) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("client_id",CLIENT_NAME).GET().build();
        try { return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body(); }
        catch (Exception e) { return "401"; }
    }
    static String postJson(String url, String body) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+TOKEN).header("client_id",CLIENT_NAME).header("Content-Type","application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
    static String postForm(String url, String body, String auth, String client) throws Exception {
        HttpRequest.Builder b = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Content-Type","application/x-www-form-urlencoded").POST(HttpRequest.BodyPublishers.ofString(body));
        if (auth!=null) b.header("Authorization", auth);
        if (client!=null) b.header("client_id", client);
        return CLIENT.send(b.build(), HttpResponse.BodyHandlers.ofString()).body();
    }
    static String putCall(String url, String body) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+TOKEN).header("client_id",CLIENT_NAME).header("Content-Type","application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(body)).build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }    
    static String postCall(String url, String body) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+TOKEN).header("client_id",CLIENT_NAME).header("Content-Type","application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
    static String deleteCall(String url) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+TOKEN).header("client_id",CLIENT_NAME)
            .DELETE().build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
    static String deleteCallWithBody(String url, String body) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+TOKEN).header("client_id",CLIENT_NAME).header("Content-Type","application/json")
            .method("DELETE", HttpRequest.BodyPublishers.ofString(body)).build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
    static String encrypt(String password) throws Exception{        
        SecretKeySpec keySpec = new SecretKeySpec(ENCODE_KEY.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ENCODE_KEY.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    static String putCall(String url, String body, String token) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url))
            .header("Authorization", "Bearer "+token).header("client_id",CLIENT_NAME).header("Content-Type","application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(body)).build();
        return CLIENT.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
    static String login(String username, String password) throws Exception {
        String encrytPassword = encrypt(password);
        
        String tokenResp = postForm(BASE + "/auth/oauth2/token",
            "username="+username+"&password=" +encrytPassword+ "&grant_type=password&scope=server",
            "Basic cGlnLXVwbXM6cGln", CLIENT_NAME);
        String token = extractField(tokenResp, "access_token");
        System.out.println(String.format("当前用户->%s \r\n请求密码->%s  \r\n请求url->%s,  \r\n request body->%s \r\n response-> %s", username, password, BASE + "/auth/oauth2/token", "username="+username+"&password=" +encrytPassword+ "&grant_type=password&scope=server", tokenResp));
        return token;
    }
    @FunctionalInterface
    interface Thunk { void run() throws Exception; }
}
