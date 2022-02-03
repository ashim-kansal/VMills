package com.vmotors.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.vmotors.R;

public class PrefManager {
    private static PrefManager instance;
    public   Context context;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    //preferences variables
    private static final String KEY_IS_LOGIN = "KEY_IS_LOGIN";
    public static final String KEY_USERID = "KEY_USERID";
    public static final String KEY_ISSCHEDULED = "KEY_ISSCHEDULED";
    public static final String KEY_PASSWORD = "KEY_PASSWORD";
    public static final String KEY_USER_EMAIL = "KEY_USER_EMAIL";
    public static final String KEY_PROFILEPIC = "KEY_PROFILEPIC";
    public static final String KEY_NAME= "KEY_NAME";
    public static final String KEY_PHONE = "KEY_PHONE";
    public static final String KEY_TOPIC = "KEY_TOPIC";
    public static final String KEY_SUBJECT_NAME = "KEY_SUBJECT_NAME";
    public static final String KEY_SUBJECT_ID = "KEY_SUBJECT_ID";
    public static final String KEY_QUALIFICATION = "KEY_QUALIFICATION";
    public static final String KEY_CLASS_SELECT = "KEY_CLASS_SELECT";
    public static final String KEY_LOCATION = "KEY_LOCATION";
    public static final String KEY_EXPERIENCE = "KEY_EXPERIENCE";
    public static final String KEY_TOTIME = "KEY_TOTIME";
    public static final String KEY_FROMTIME = "KEY_FROMTIME";
    public static final String KEY_FROMDATE = "KEY_FROMDATE";
    public static final String KEY_TODATE= "KEY_TODATE";
    public static final String KEY_SUBSCRIPTION_ID= "KEY_SUBSCRIPTION_ID";
    public static final String KEY_SUBSCRIPTION_NAME= "KEY_SUBSCRIPTION_NAME";
    public static final String KEY_NOTIID= "KEY_NOTIID";
    public static final String KEY_DEVICETOKEN= "KEY_DEVICETOKEN";

    public PrefManager(Context context) {
        super();
        this.context = context;
        this.preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        this.editor = this.preferences.edit();
    }

    public static PrefManager getInstance(Context context){
        if (instance == null){
            instance = new PrefManager(context);
        }
        return instance;
    }
    public  String getKeyProfilePic() {
        return getStringValue(KEY_PROFILEPIC);
    }
    public  void setKeyProfilePic(String value) {
        setStringValue(KEY_PROFILEPIC,value);
    }
    public  int getKeyNotificationid() {
        return getIntValue(KEY_NOTIID);
    }
    public  void setKeyNotificationid(int value) {
        setIntValue(KEY_NOTIID,value);
    }
    public  String getKeyDevicetoken() {
        return getStringValue(KEY_DEVICETOKEN);
    }
    public  void setKeyDevicetoken(String value) {
        setStringValue(KEY_DEVICETOKEN,value);
    }
    public  String getKeyQualification() {
        return getStringValue(KEY_QUALIFICATION);
    }
    public  void setKeyQualification(String value) {
        setStringValue(KEY_QUALIFICATION,value);
    }

    public  String getKeyClassSelect() {
        return getStringValue(KEY_CLASS_SELECT);
    }
    public  void setKeyClassSelect(String value) {
        setStringValue(KEY_CLASS_SELECT,value);
    }

    public  String getKeyLocation() {
        return getStringValue(KEY_LOCATION);
    }
    public  void setKeyLocation(String value) {
        setStringValue(KEY_LOCATION,value);
    }

    public  String getKeyExperience() {
        return getStringValue(KEY_EXPERIENCE);
    }
    public  void setKeyExperience(String value) {
        setStringValue(KEY_EXPERIENCE,value);
    }

    public  String getKeyUserId() {
        return getStringValue(KEY_USERID);
    }
    public  void setKeyUserId(String value) {
        setStringValue(KEY_USERID,value);
    }


    public  String getKeyToTime() {
        return getStringValue(KEY_TOTIME);
    }
    public  void setKeyToTime(String value) {
        setStringValue(KEY_TOTIME,value);
    }

    public  String getKeyTodate() {
        return getStringValue(KEY_TODATE);
    }
    public  void setKeyTodate(String value) {
        setStringValue(KEY_TODATE,value);
    }

    public  String getKeyFromTime() {
        return getStringValue(KEY_FROMTIME);
    }
    public  void setKeyFromTime(String value) {
        setStringValue(KEY_FROMTIME,value);
    }

    public  String getKeyFromdate() {
        return getStringValue(KEY_FROMDATE);
    }
    public  void setKeyFromdate(String value) {
        setStringValue(KEY_FROMDATE,value);
    }

    public  String getKeyPhone() {
        return getStringValue(KEY_PHONE);
    }

    public  void setKeyPhone(String phone) {
        setStringValue(KEY_PHONE,phone);
    }

    public  String getKeyName() {
        return getStringValue(KEY_NAME);
    }

    public  void setKeyName(String name)
    {
        setStringValue(KEY_NAME,name);
    }

    public void setKeyIsLogin(boolean value){
        setBooleanValue(KEY_IS_LOGIN, value);
    }
    public boolean getKeyIsLogin(){
        return getBooleanValue(KEY_IS_LOGIN);
    }
    public void setKeyIsScheduled(boolean value){
        setBooleanValue(KEY_ISSCHEDULED, value);
    }
    public boolean getKeyIsScheduled(){
        return getBooleanValue(KEY_ISSCHEDULED);
    }


    /**
     * Retrieving the value from the preference for the respective key.
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as boolean.
     */
    private boolean getBooleanValue(String key) {
        return this.preferences.getBoolean(key, false);
    }

    /**
     * Saving the preference
     * @param key : Key of the preference.
     * @param value : Value of the preference.
     */
    private void setBooleanValue(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.commit();
    }

    /**
     * Retrieving the value from the preference for the respective key.
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as string.
     */
    private String getStringValue(String key) {
        return this.preferences.getString(key,"");
    }

    private String getStringValue(String key, String def) {
        return this.preferences.getString(key,def);
    }

    /**
     * Saving the preference
     * @param key : Key of the preference.
     * @param value : Value of the preference.
     */
    private void setStringValue(String key, String value) {
        this.editor.putString(key, value);
        this.editor.commit();
    }

    /**
     * Retrieving the value from the preference for the respective key.
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as string.
     */
    private int getIntValue(String key) {
        return this.preferences.getInt(key, 0);
    }

    /**
     * Saving the preference
     * @param key : Key of the preference.
     * @param value : Value of the preference.
     */
    private void setIntValue(String key, int value) {
        this.editor.putInt(key, value);
        this.editor.commit();
    }

    /**
     * Retrieving the value from the preference for the respective key.
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as string.
     */
    public long getLongValue(String key) {
        return this.preferences.getLong(key, 0L);
    }

    /**
     * Saving the preference
     * @param key : Key of the preference.
     * @param value : Value of the preference.
     */
    public void setLongValue(String key, long value) {
        this.editor.putLong(key, value);
        this.editor.commit();
    }

    /**
     * Removes all the fields from SharedPrefs
     */
    public void clearPrefs() {
        this.editor.clear();
        this.editor.commit();

    }

    /**
     *Remove the preference for the particular key
     * @param key : Key for which the preference to be cleared.
     */
    public void removeFromPreference(String key){
        this.editor.remove(key);
        this.editor.commit();
    }


    public void setKeyPassword(String value){
        setStringValue(KEY_PASSWORD, value);
    }

    public String getKeyPassword(){
        return getStringValue(KEY_PASSWORD);
    }
    public void setUserEmail(String value){
        setStringValue(KEY_USER_EMAIL, value);
    }

    public String getUserEmail(){
        return getStringValue(KEY_USER_EMAIL);
    }

    public String getKeySubjectId(){
        return getStringValue(KEY_SUBJECT_ID);
    }

    public String getKeySubjectNAME(){
        return getStringValue(KEY_SUBJECT_NAME);
    }

    public void setKeySubjectId(String value) {
        setStringValue(KEY_SUBJECT_ID, value);
    }
    public void setKeySubjectName(String value) {
        setStringValue(KEY_SUBJECT_NAME, value);
    }
    public String getKeyTopic(){
        return getStringValue(KEY_TOPIC);
    }

    public void setKeyTopic(String value) {
        setStringValue(KEY_TOPIC, value);
    }

    public void setKeySubscriptionId(String id) {
        setStringValue(KEY_SUBSCRIPTION_ID, id);
    }

    public String getKeySubscriptionId(){
        return getStringValue(KEY_SUBSCRIPTION_ID);
    }

    public void setKeySubscriptionName(String packageName) {
        setStringValue(KEY_SUBSCRIPTION_NAME, packageName);
    }

    public String getKeySubscriptionName(){
        return getStringValue(KEY_SUBSCRIPTION_NAME);
    }

}
