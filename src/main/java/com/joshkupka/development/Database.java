package com.joshkupka.development;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Database {

    public JSONObject userData = new JSONObject();
    public void databaseInit(){
        File file = new File("UserData.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putData(String title, Object es) {
        userData.put(title, es);
        try (FileWriter file = new FileWriter("UserData.json")) {
            file.write(userData.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getData(String title) {

        String dataReturn = null;

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("UserData.json"));
            JSONObject jsonObject = (JSONObject) obj;

            dataReturn = (String) jsonObject.get(title);

            return dataReturn;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException exc) {
            exc.printStackTrace();
        } finally {
            return dataReturn;
        }
    }

    public boolean containsKey(String title){
        boolean doesKeyExist = false;

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("UserData.json"));
            JSONObject jsonObject = (JSONObject) obj;

            doesKeyExist = jsonObject.containsKey(title);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException exc) {
            exc.printStackTrace();
        } finally {
            return doesKeyExist;
        }
    }




    public String profileID(){
        String id;
        String rawUserData = getData("User-Data").toString();
        id = StringUtils.substringBetween(rawUserData, "=", ",");
        System.out.println(id);
        return id;
    }

    public String login(){
        String id;
        String rawUserData = getData("User-Data").toString();
        id = StringUtils.substringBetween(rawUserData, "login=", ",");
        System.out.println(id);
        return id;
    }

    public String displayName(){
        String id;
        String rawUserData = getData("User-Data").toString();
        id = StringUtils.substringBetween(rawUserData, "displayName=", ",");
        System.out.println(id);
        return id;
    }

    public String broadcasterType(){
        String id;
        String rawUserData = getData("User-Data").toString();
        id = StringUtils.substringBetween(rawUserData, "broadcasterType=", ",");
        System.out.println(id);
        return id;
    }
    public String profileImageURL(){
        String id;
        String rawUserData = getData("User-Data").toString();
        id = StringUtils.substringBetween(rawUserData, "profileImageUrl=", ",");
        System.out.println(id);
        return id;
    }
    public String profileImageFileName(){
        String profile = profileImageURL();
        String rawUserData = getData("User-Data").toString();
        String id;
        id = StringUtils.substring(profile, 47);
        System.out.println(id);
        return id;
    }
}
