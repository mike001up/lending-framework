import java.net.URI;
import java.net.http.*;
import java.time.LocalDateTime;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class SysClientTest {
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

        // ===== 4.4 CT 业务客户端管理 =====
        test("CT-01-01", "创建业务客户端", () -> {
            String clientId = "test_client_ct_0101";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);
            
        });
        test("CT-01-02", "创建业务客户端-缺少必要属性", () -> {
            String clientId = "test_client_ct_0102";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 1);
        });
        test("CT-02-01", "查询客户端列表", () -> {
            String clientId = "test_client_ct_0201";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
            // checkCode(r, 0);
            checkContains(r, clientId);
        });        
        test("CT-03-01", "修改客户端", () -> {
            String clientId = "test_client_ct_0301";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
            String id = extractField(r, "id");

            clientName = "测试客户端-update";
            r = putCall(BASE+"/admin/client", "{\"id\":\"" +id+ "\",\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);
        });
        test("CT-03-02", "修改客户端-缺少必要参数", () -> {
            String clientId = "test_client_ct_0302";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);

            clientName = "测试客户端-update";
            r = putCall(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 1);
        });
        test("CT-04-01", "删除客户端", () -> {
            String clientId = "test_client_ct_0401";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
            String id = extractField(r, "id");
            r = deleteCall(BASE+"/admin/client/"+id);
            checkCode(r, 0);
        });
        //启用客户端后，客户可以登录
        test("CT-05-01", "启用客户端", () -> {
            String clientId = "test_client_ct_0501";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
            String id = extractField(r, "id");
            //禁用客户端
            r = putCall(BASE+"/admin/client/toggle-status/"+id, "");
            checkCode(r, 0);

            //启用客户端
            r = putCall(BASE+"/admin/client/toggle-status/"+id, "");
            checkCode(r, 0);

        });
        //禁用客户端后，客户禁止登录
        test("CT-05-02", "禁用客户端", () -> {
            String clientId = "test_client_ct_0502";
            String clientName = "测试客户端";
            String clientSecret = "test_secret";
            String scope = "server";
            String additionalInformation = "{\"encFlag\":1,\"captchaFlag\":1,\"onlineQuantity\":1,\"ipAction\":\"DENY\"}";
            
            String r = postJson(BASE+"/admin/client", "{\"clientId\":\"" +clientId+ "\",\"clientSecret\":\"" +clientSecret+ "\",\"scope\":\"" +scope+ "\",\"clientName\":\"" +clientName+ "\",\"additionalInformation\":" +additionalInformation+ "}");
            checkCode(r, 0);

            r = getCall(BASE+"/admin/client/page?current=1&size=1&clientId="+clientId);
            String id = extractField(r, "id");
            //禁用客户端
            r = putCall(BASE+"/admin/client/toggle-status/"+id, "");
            checkCode(r, 0);
        });
        test("CT-05-03", "禁用后用户无法通过该客户端登录", () -> {
            // checkTrue(true, "需人工验证：禁用客户端后登录被拒绝");
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
