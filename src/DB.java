import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;

import org.json.*;

public class DB {
    private static final DB db = new DB();
    private final static String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c04/selectuser");

    public static String makeGETRequest(String urlName) {
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        try {         //THIS CONNECTION HAS POTENTIAL FOR ERRORS
            URL url = new URL(urlName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();  //open the connection
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));//get the output of GET request (from above)
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null) //take all the data from website to a big string
            {
                sb.append(line + '\n'); //add each line to StringBuilder
            }
            conn.disconnect(); //no more lines, disconnect
            return sb.toString(); //return the big string
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    public boolean checkIfRemember(String jsonString) {
        boolean flag = false;
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                if (curObject.getInt("lastRemember") == 1)
                    flag = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean checkUser(String jsonString, String userName, String password) {
        boolean flag = false;
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                if (curObject.getString("NAME").equals(userName)) {
                    if (curObject.getString("PASSWORD").equals(password)) {
                        flag = true;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public int getUserID(String jsonString, String name) {
        int ID = 0;
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                if (curObject.getString("NAME").equals(name))
                    ID = Integer.parseInt(curObject.getString("ID"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ID;
    }

    public static String getPassword() {
        String jsonString = makeGETRequest("https://studev.groept.be/api/a21ib2c04/select_certain_user/"+App.ID);
        String password = null;
        try {
            JSONArray array = new JSONArray(jsonString);
            JSONObject curObject = array.getJSONObject(0);
            password = curObject.getString("PASSWORD");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static void updatePassword(String newPassword){
        makeGETRequest("https://studev.groept.be/api/a21ib2c04/update_password/" + newPassword + "/" + App.ID);
    }

    public String getRememberedUserName(String jsonString) {
        String remembereduserName = null;
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                if (curObject.getInt("lastRemember") == 1)
                    remembereduserName = curObject.getString("NAME");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return remembereduserName;
    }

    public String getRememberedPassword(String jsonString) {
        String rememberedpassword = null;
        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                if (curObject.getInt("lastRemember") == 1)
                    rememberedpassword = curObject.getString("PASSWORD");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rememberedpassword;
    }

    public void rememberLastUser(String jsonString, String user, String password) {
        try {
            JSONArray array = new JSONArray(jsonString);
            db.makeGETRequest("https://studev.groept.be/api/a21ib2c04/reset_lastRemember");
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                if (checkUser(response, user, password))
                    db.makeGETRequest(
                            "https://studev.groept.be/api/a21ib2c04/update_lastRemember/" + getUserID(response, user));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String urlName, String expectedColName) {
        String jsonString = makeGETRequest(urlName);
        String result = null;
        try {
            JSONArray array = new JSONArray(jsonString);
            JSONObject curObject = array.getJSONObject(0);
            result = curObject.getString(expectedColName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getName() {
        return getString("https://studev.groept.be/api/a21ib2c04/select_username/" + App.ID, "NAME");
    }

    public static float[] getLampInfo() {
        String jsonString = makeGETRequest("https://studev.groept.be/api/a21ib2c04/select_real_time_control/" + App.ID);
        float[] info = new float[2];
        try {
            JSONArray array = new JSONArray(jsonString);
            JSONObject curObject = array.getJSONObject(0);
            info[0] = curObject.getFloat("lampState");
            info[1] = curObject.getFloat("brightness");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info;
    }

    public static int getCurtainState() {
        String jsonString = makeGETRequest("https://studev.groept.be/api/a21ib2c04/select_real_time_control/" + App.ID);
        int result = 0;
        try {
            JSONArray array = new JSONArray(jsonString);
            JSONObject curObject = array.getJSONObject(0);
            result = curObject.getInt("curtainState");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void updateBrightness(float newBrightness){
        makeGETRequest("https://studev.groept.be/api/a21ib2c04/update_brightness/" + newBrightness + "/" + App.ID);
    }

    public static void updateLampState(int newState) {
        makeGETRequest("https://studev.groept.be/api/a21ib2c04/update_real_time_control_ls/" + newState + "/" + App.ID);
    }

    public static void updateCurtainState(int newState) {
        makeGETRequest("https://studev.groept.be/api/a21ib2c04/update_real_time_control_cs/" + newState + "/" + App.ID);
    }

    public static void updateCeilingLightState(int newState){
        makeGETRequest("https://studev.groept.be/api/a21ib2c04/update_real_time_control_ceiling/" + newState + "/" + App.ID);
    }

    public static String[] getSystemsStates() {
        String jsonString = makeGETRequest("https://studev.groept.be/api/a21ib2c04/select_real_time_control/" + App.ID);
        String[] result = new String[4];
        try {
            JSONArray array = new JSONArray(jsonString);
            JSONObject curObject = array.getJSONObject(0);
            result[0] = curObject.getString("enableLock");
            result[1] = curObject.getString("enableLamp");
            result[2] = curObject.getString("enableCurtain");
            result[3] = curObject.getString("ceilingLightState");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void updateRealTimeControl(String type, String newState) {
        makeGETRequest("https://studev.groept.be/api/a21ib2c04/update_real_time_control_" + type + "/" + newState + "/" + App.ID);
    }

    public static void updateUserinfo(String newName, String newMail) {
        makeGETRequest("https://studev.groept.be/api/a21ib2c04/update_userinfo/"+
                newName + "/" + newMail + "/" + App.ID);
    }

    public static String[] getUserInfo() {
        String jsonString = makeGETRequest("https://studev.groept.be/api/a21ib2c04/select_certain_user/"+App.ID);
        String[] result = new String[2];
        try {
            JSONArray array = new JSONArray(jsonString);
            JSONObject curObject = array.getJSONObject(0);
            result[0] = curObject.getString("NAME");
            result[1] = curObject.getString("Email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}


