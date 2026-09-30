import java.net.URI;
import java.net.http.*;
import java.time.LocalDateTime;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class SysPermTest {
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

        // ===== 4.3 PM 权限资源管理 =====
        // test("PM-01-01", "创建权限点", () -> {
        //     String permissionCode = "sys_test_perm_check_0101";
        //     String permission = "sys:test:perm:check:0101";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);            
        // });
        // test("PM-01-02", "权限编码唯一", () -> {
        //     String permissionCode = "sys_test_perm_check_0102";
        //     String permission = "sys:test:perm:check:0102";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);  

        //     r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 1);
        // });
        // test("PM-01-03", "权限必要信息验证", () -> {
        //     String permissionCode = "sys_test_perm_check_0103";
        //     String permission = "sys:test:perm:check:0103";
        //     String r = postJson(BASE+"/admin/permission", "{\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 1); 
        // });
        // test("PM-02-01", "删除权限点-无角色绑定", () -> {
        //     String permissionCode = "sys_test_perm_check_0201";
        //     String permission = "sys:test:perm:check:0201";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);  

        //     r = getCall(BASE+"/admin/permission/page?current=1&size=10&permCode="+permissionCode);
        //     checkContains(r, permissionCode);
        //     String permssionId = extractField(r, "permissionId");

        //     r = deleteCall(BASE+"/admin/permission/"+permssionId);
        //     checkCode(r, 0);

        // });
        // test("PM-02-02", "删除权限点-有角色绑定", () -> {
        //     String permissionCode = "sys_test_perm_check_0202";
        //     String permission = "sys:test:perm:check:0202";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);  

        //     r = getCall(BASE+"/admin/permission/page?current=1&size=10&permCode="+permissionCode);
        //     checkContains(r, permissionCode);
        //     String permssionId = extractField(r, "permissionId");

        //     String roleCode = "ROLE_PM_0202";
        //     r = postJson(BASE+"/admin/role", "{\"roleCode\":\""+roleCode+"\",\"roleName\":\"测试角色\",\"permissionIds\":[\""+permssionId+"\"]}");
        //     checkCode(r, 0);  

        //     r = deleteCall(BASE+"/admin/permission/"+permssionId);
        //     checkCode(r, 0);
        // });
        // test("PM-03-01", "查询权限树", () -> {
        //     String permissionCode = "sys_test_perm_check_0301";
        //     String permission = "sys:test:perm:check:0301";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);  

        //     r = getCall(BASE+"/admin/permission/tree");
        //     checkCode(r, 0);
        //     System.out.println("permission tree:"+r);
        //     checkContains(r, permissionCode);
        // });
        // test("PM-03-02", "查询权限详情", () -> {
        //     String permissionCode = "sys_test_perm_check_0302";
        //     String permission = "sys:test:perm:check:0302";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);  

        //     r = getCall(BASE+"/admin/permission/page?current=1&size=10&permCode="+permissionCode);
        //     checkContains(r, permissionCode);
        //     String permssionId = extractField(r, "permissionId");

        //     r = getCall(BASE+"/admin/permission/"+permssionId);
        //     checkCode(r, 0);
        // });
        // test("PM-03-03", "分页查询权限", () -> {
        //     String permissionCode = "sys_test_perm_check_0303";
        //     String permission = "sys:test:perm:check:0303";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);  

        //     r = getCall(BASE+"/admin/permission/page?current=1&size=1&permCode="+permissionCode);
        //     checkContains(r, permissionCode);
        //     // checkCode(r, 0);
        // });
        // test("PM-04-01", "权限修改", () -> {
        //     String permissionCode = "sys_test_perm_check_0401";
        //     String permission = "sys:test:perm:check:0401";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);

        //     r = getCall(BASE+"/admin/permission/page?current=1&size=10&permCode="+permissionCode);
        //     checkContains(r, permissionCode);
        //     String permssionId = extractField(r, "permissionId");

        //     r = putCall(BASE+"/admin/permission","{\"permissionId\":\""+permssionId+"\",\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限update\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);
        // });
        // test("PM-04-02", "权限修改缺少参数", () -> {
        //     String permissionCode = "sys_test_perm_check_0402";
        //     String permission = "sys:test:perm:check:0402";
        //     String r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 0);

        //     r = putCall(BASE+"/admin/permission","{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限update\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
        //     checkCode(r, 1);
        // });
        test("AC-01-01", "单权限校验-有权限", () -> {
            //创建用户
            String phone = "+8613801030101";
            String username = "TU"+ P + "ac0101";
            String email = username+"@gmail.com";
            String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试01\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"roles\":[1],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\"" +email+ "\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
            checkCode(r, 0);
            String userId = extractNestedField(r, "records", "userId");
            //创建角色
            String roleCode = "ROLE_ac0101";
            r = postJson(BASE+"/admin/role", "{\"roleCode\":\""+roleCode+"\",\"roleName\":\"测试角色\"}");
            checkCode(r, 0); 

            r = getCall(BASE+"/admin/role/page?current=1&size=10&roleCode="+roleCode);
            checkContains(r, "roleId");
            String roleId = extractField(r, "roleId");
            //创建权限
            String permissionCode = "sys_test_perm_ac_0101";
            String permission = "sys:test:perm:ac:0101";
            r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/permission/page?current=1&size=10&permCode="+permissionCode);
            checkContains(r, permissionCode);
            String permssionId = extractField(r, "permissionId");
            //角色绑定权限
            r = putCall(BASE+"/admin/role/permission", "{\"roleId\":\""+roleId+"\",\"permissionIds\":[\""+permssionId+"\"]}");
            checkCode(r, 0);
            //角色赋予用户
            Long[] roleIdList = {Long.valueOf(roleId)};
            r = putCall(BASE+"/admin/role/grant/" + userId, "["+roleIdList[0]+"]");
            checkCode(r, 0);
            String permCode = "sys:permission:check";
            r = getCall(BASE+"/admin/permission/check/"+userId+"/"+permCode);
            checkCode(r, 0);
        });
        test("AC-01-02", "单权限校验-无权限", () -> {
            //创建用户
            String phone = "+8613801030102";
            String username = "TU"+ P + "ac0102";
            String email = username+"@gmail.com";
            String r = postJson(BASE+"/admin/user", "{\"username\":\""+username+"\",\"name\":\"测试01\",\"phone\":\"" +phone+ "\",\"password\":\"" +password+ "\",\"roles\":[1],\"clientIds\":[\"" +CLIENT_ID+ "\"], \"email\":\"" +email+ "\",\"avatar\":\"https://duie.com/11.png\",\"nickname\":\"测试01\"}");
            checkCode(r, 0);
            String userId = extractNestedField(r, "records", "userId");
            //创建角色
            String roleCode = "ROLE_ac0102";
            r = postJson(BASE+"/admin/role", "{\"roleCode\":\""+roleCode+"\",\"roleName\":\"测试角色\"}");
            checkCode(r, 0); 

            r = getCall(BASE+"/admin/role/page?current=1&size=10&roleCode="+roleCode);
            checkContains(r, "roleId");
            System.out.println("role info:"+r);
            String roleId = extractField(r, "roleId");
            //创建权限
            String permissionCode = "sys_test_perm_ac_0102";
            String permission = "sys:test:perm:ac:0102";
            r = postJson(BASE+"/admin/permission", "{\"permission\":\""+permission+"\",\"permCode\":\""+permissionCode+"\",\"permName\":\"测试权限\",\"resourceType\":\"API\",\"path\":\"https://test.com/admin/permission/test\", \"method\":\"GET\", \"sortOrder\":0}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/permission/page?current=1&size=10&permCode="+permissionCode);
            checkContains(r, permissionCode);
            String permssionId = extractField(r, "permissionId");
            //角色绑定权限
            r = putCall(BASE+"/admin/role/permission", "{\"roleId\":\""+roleId+"\",\"permissionIds\":[\""+permssionId+"\"]}");
            checkCode(r, 0);
            //角色赋予用户
            Long[] roleIdList = {Long.valueOf(roleId)};
            r = putCall(BASE+"/admin/role/grant/" + userId, "["+roleIdList[0]+"]");
            checkCode(r, 0);
            String permCode = "sys:permission:check";
            r = getCall(BASE+"/admin/permission/check/"+userId+"/9999");
            checkCode(r, 0);
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
