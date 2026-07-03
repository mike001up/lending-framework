import java.net.URI;
import java.net.http.*;
import java.time.LocalDateTime;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class SysUserTest {
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

        // ===== 4.1 UM 用户管理 =====
        // test("UM-01-01", "创建用户-正常-无层级关系", () -> {
        //     String phone = "+8613800000101";
        //     String username = "TU"+ P + "u0101";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试01\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"roles\":[1],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\"" +email+ "\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractNestedField(r, "records", "userId");
            
        //     checkTrue(userId != null && !userId.trim().isEmpty(), "创建新用户失败，不能获取用户ID");
        // });
        // test("UM-01-02", "创建用户-用户名/手机/邮箱重复", () -> {
        //     String phone = "+8613800000102";
        //     String username = "TU"+ P + "u0102";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试01\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"roles\":[1],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\"11111111112d33d@gmail.com\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试01\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"roles\":[1],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\"11111111112d33d@gmail.com\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 1);
        // });
        // test("UM-01-02-01", "创建用户-错误角色", () -> {
        //     String phone = "+8613800010201";
        //     String username = "TU"+ P + "u010201";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试01\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"roles\":[9999],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\"" +email+ "\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 1);
        // });
        // test("UM-01-02-02", "创建用户-错误终端", () -> {
        //     String phone = "+8613800010202";
        //     String username = "TU"+ P + "u010202";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试01\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"roles\":[9999],\"clientIds\":[999], \"email\":\"" +email+ "\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 1);
        // });
        // test("UM-01-03", "创建用户-正常-只含租户", () -> {
        //     String phone = "+8613800000103";
        //     String username = "TU"+ P + "u0103";
        //     String email = username+"@gmail.com";
        //     String reqBody = "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}";
        //     System.out.println("UM-01-03----" + reqBody);
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        // });
        // test("UM-01-03-01", "创建用户-正常-租户错误", () -> {
        //     String phone = "+8613800010301";
        //     String username = "TU"+ P + "u010301";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":999,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 1);
        // });
        // test("UM-01-04", "创建用户-正常-含代理", () -> {
        //     String phone = "+8613800000104";
        //     String username = "TU"+ P + "u0104";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String agencyId = extractField(r, "userId");
        //     phone = "+8613800010401";
        //     username = "TU"+ P + "u010401";
        //     email = username+"@gmail.com";
        //     r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\",\"agencyId\":\""+agencyId+ "\"}");
        //     checkCode(r, 0);
        // });
        
        // test("UM-01-06", "创建用户-密码bcrypt", () -> {
        //     String phone = "+8613800000106";
        //     String username = "TU"+ P + "u0106";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);

        //     String clientToken = login(username, password);
        //     checkTrue(clientToken != null && !clientToken.trim().isEmpty(), "新建用户应该可以登录,但是没有获取登陆后的token");
        // });
        // test("UM-02-01", "修改用户基本信息", () -> {
        //     String phone = "+8613800000201";
        //     String username = "TU"+ P + "u0201";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");
        //     String username1 = "TU"+ P + "u020101";
        //     String phone1 = "+8613800020101";
        //     String email1 = username+"@gmail.com";
        //     r = putCall(BASE+"/admin/user", "{\"userId\":\""+userId+"\",\"username\":\""+username1+"\",\"name\":\"测试0201\",\"phone\":\"" +phone1+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[1],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email1+"\",\"avatar\":\"https://duie.com/1122.png\",\"nickname\":\"测试020101\"}");
        //     checkCode(r, 0);
        //     r = getCall(BASE+"/admin/user/details/"+userId);
        //     checkCode(r, 0);
        //     String newUsername = extractField(r, "username");
        //     String newPhone = extractField(r, "phone");
        //     String newEmail = extractField(r, "email");
        //     checkTrue(newUsername.equals(username) && newEmail.equals(email) && newPhone.equals(phone), "用户的用户名/电话号码/邮箱地址 不能被修改");
        // });
        // test("UM-02-02", "用户修改自己密码", () -> {
        //     String phone = "+8613800000202";
        //     String username = "TU"+ P + "u0202";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");
            
        //     String clientToken = login(username, password);
        //     checkTrue(clientToken != null && !clientToken.trim().isEmpty(), "新建用户登录失败");

        //     String oldPassword = password;
        //     String newPassword = "lengleng2";
        //     r = putCall(BASE+"/admin/user/password", "{\"oldpassword\":\"" +oldPassword+ "\",\"newpassword\":\"" +newPassword+ "\"}", clientToken);
        //     checkCode(r, 0);
            
        // });
        // test("UM-02-03", "修改密码后原Token失效", () -> {
        //     String phone = "+8613800000203";
        //     String username = "TU"+ P + "u0203";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");
            
        //     String clientToken = login(username, password);
        //     // String clientToken = extractField(clientTokenResp, "access_token");
        //     checkTrue(clientToken != null && !clientToken.trim().isEmpty(), "新建用户不能登录");

        //     String oldPassword = password;
        //     String newPassword = "lengleng2";
        //     r = putCall(BASE+"/admin/user/password", "{\"oldpassword\":\"" +oldPassword+ "\",\"newpassword\":\"" +newPassword+ "\"}", clientToken);
        //     checkCode(r, 0);
            
        //     r = getCall(BASE+"/admin/user/info", clientToken);
        //     checkCode(r, 401);
        // });
        // test("UM-02-04", "管理员重置他人密码", () -> {
        //     String phone = "+8613800000204";
        //     String username = "TU"+ P + "u0204";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     //用户登录
        //     String clientToken = login(username, password);
        //     checkTrue(clientToken != null && !clientToken.trim().isEmpty(), "新建用户不能登录");
        //     //管理员重置密码
        //     String userId = extractField(r, "userId");
        //     String newPassword = "lengleng2";
        //     r = putCall(BASE+"/admin/user/password/reset", "{\"userId\":"+userId+",\"newPassword\":\"" +newPassword+ "\"}");
        //     checkCode(r, 0);

        //     //修改密码后，token 失效
        //     r = getCall(BASE+"/admin/user/info", clientToken);
        //     checkCode(r, 401);
        // });
        // test("UM-02-05", "修改密码-原密码错误", () -> {
        //     String phone = "+8613800000205";
        //     String username = "TU"+ P + "u0205";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);

        //     String clientToken = login(username, password);
        //     checkTrue(clientToken != null && !clientToken.trim().isEmpty(), "新建用户不能登录");

        //     String oldPassword = "wrong";
        //     String newPassword = "lengleng3";
        //     r = putCall(BASE+"/admin/user/password", "{\"oldpassword\":\"" +oldPassword+ "\",\"newpassword\":\"" +newPassword+ "\"}", clientToken);
        //     checkCode(r, 1);
        // });
        // test("UM-03-01", "禁用用户", () -> {
        //     String phone = "+8613800000301";
        //     String username = "TU"+ P + "u0301";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");

        //     r = putCall(BASE+"/admin/user/"+userId+"/disable", "");
        //     checkCode(r, 0);

        // });
        // test("UM-03-02", "禁用后无法登录", () -> {
        //     String phone = "+8613800000302";
        //     String username = "TU"+ P + "u0302";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");

        //     r = putCall(BASE+"/admin/user/"+userId+"/disable", "");
        //     checkCode(r, 0);

        //     String clientToken = login(username, password);
        //     checkTrue(clientToken == null || clientToken.trim().isEmpty(), "用户已经被禁用，应该禁止登录");
        // });
        // test("UM-03-03", "启用用户", () -> {
        //     String phone = "+8613800000303";
        //     String username = "TU"+ P + "u0303";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");

        //     r = putCall(BASE+"/admin/user/"+userId+"/disable", "");
        //     checkCode(r, 0);            

        //     r = putCall(BASE+"/admin/user/"+userId+"/enable", "");
        //     checkCode(r, 0);
        // });
        // test("UM-03-04", "启用后可登录", () -> {
        //     String phone = "+8613800000304";
        //     String username = "TU"+ P + "u0304";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");

        //     r = putCall(BASE+"/admin/user/"+userId+"/disable", "");
        //     checkCode(r, 0);            

        //     r = putCall(BASE+"/admin/user/"+userId+"/enable", "");
        //     checkCode(r, 0);

        //     // String clientPassword = encrypt(password);
        //     String clientToken = login(username, password);
        //     checkTrue(clientToken != null && !clientToken.trim().isEmpty(), "用户已经启用，用户应该可以登录");
        // });
        // test("UM-04-01", "分页查询-默认", () -> {
        //     String phone = "+8613800000401";
        //     String username = "TU"+ P + "u0401";
        //     String email = username+"@gmail.com";
        //     String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
        //     checkCode(r, 0);
        //     String userId = extractField(r, "userId");

        //     r = getCall(BASE+"/admin/user/page");
        //     checkCode(r, 0);
        // });
        test("UM-04-02", "分页查询-按条件", () -> {
            String phone = "+8613800000402";
            String username = "TU"+ P + "u0402";
            String email = username+"@gmail.com";
            String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试02\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"tenantId\":1,\"roles\":[],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\""+email+"\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/user/page?username="+username+"&phone="+phone+"&email="+email+"&status=ENABLED&createTimeStart=1783008000000");
            checkCode(r, 0);
            checkContains(r, username);
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
