package com.vmotors.navigation;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigationTarget {

    public static final String OTP = "otp";
    public static final String REGISTER = "register";
    public static final String FORGOT_PASSWORD = "forgot_password";

    public static final String KEY_MAINTENANCE_TYPE = "maintenance_type";

    public static final String PARAM_IS_COMING_FROM_ROOTED_FLOW = "param_is_coming_from_rooted_flow";

    public static final String PARAM_IS_ATM_VISIBLE = "param_atm_and_branch_screen";

    public static final String PARAM_FOREX_BRANCH_TAB_SELECT = "param_forex_branch_tab_select";

    public static final String PARAM_NEW_USERNAME = "param_new_username";

    public static final String PARAM_COUNTRY_NAME = "country_name";

    public static final String PARAM_COUNTRY_IMAGE_NAME = "country_image_name";

    public static final String HOME = "home";

    public static final String REINVEST_FIAS = "reinvest_fias_disclaimer";

    public static final String SHARE_ACC_INFO = "share_acc_info";

    public static final String ENROLMENT_OPTIONS = "enrolment_options";

    public static final String PARAM_BIRTH_DATE = "param_birth_date";

    public static final String GO_TO_DASHBOARD = "go_to_dashboard";

    public static final String CATEGORY = "category";

    public static final String TOP_UP_LANDING = "topup_landing";

    public static final String LOAD_TRIP = "load_trip";

    public static final String LOAD_TRIP_SUCCESS = "load_trip_success";

    public static final String TRIP_ERROR = "trip_error";

    public static final String SHOW_COUNTRIES = "show_countries";

    public static final String PAY = "pay";

    public static final String PAY_LANDING = "pay_landing";

    public static final String GOVT_PAYMENT = "govt_payment";

    public static final String GOVT_PAYMENT_TRANSACTION_DETAILS = "govt_payment_transaction_details";

    public static final String TRANSFER = "transfer";

    public static final String BUY_LANDING = "buy_landing";

    public static final String ADD_RECIPIENT = "add_recipient";

    public static final String RECIPIENT_DETAIL = "recipient_detail";

    public static final String EDIT_RECIPIENT = "edit_recipient";

    public static final String BUY = "buy";

    public static final String CONTACT_US = "contact_us";

    public static final String CONTACT_US_ITEM = "contact_us_item";

    public static final String GET_IN_TOUCH = "get_in_touch";

    public static final String ATM_AND_BRANCHES = "atm_and_branches";

    public static final String ATM_AND_BRANCHES_PROFILE = "atm_and_branches_profile";

    public static final String LOGIN = "login";

    public static final String LOGIN_MORE = "login_more";

    public static final String IS_DEVICE_ROOTED_PARAM = "is_device_rooted";

    public static final String CARD_FOR_DASHBOARD = "card_for_dashboard";

    public static final String SPLASH_SCREEN = "splash_screen";

    public static final String PARAM_SKIP_LANDING = "skip_landing";

    public static final String TERMS_AND_CONDITIONS = "terms_and_conditions_sections";

    public static final String TERMS_AND_CONDITIONS_DETAILS = "terms_and_conditions_details";

    public static final String TERMS_AND_CONDITIONS_NOTICE_TYPE_PARAM = "terms_and_conditions_notice_type";

    public static final String TERMS_AND_CONDITIONS_NAME_PARAM = "terms_and_conditions_name";

    public static final String TERMS_AND_CONDITIONS_NEED_ACCEPTANCE_PARAM = "terms_and_conditions_need_acceptance_param";

    public static final String TERMS_AND_CONDITIONS_SCAN_DETAILS = "terms_and_conditions_scan_details";

    public static final String BRANCH_SELECT = "branch_select";

    public static final String BRANCH_PROFILE_SELECT = "branch_profile_select";

    public static final String RESULT_BRANCH_ID = "branch_id";

    public static final String RESULT_BRANCH_NAME = "branch_name";

    public static final String RESULT_BRANCH_ADDRESS = "branch_address";

    public static final String RESULT_BRANCH_SUBURB = "branch_suburb";

    public static final String RESULT_BRANCH_TOWN = "branch_town";

    public static final String RESULT_BRANCH_POSTAL_CODE = "branch_postal_code";

    public static final String DATA_USAGE_WARNING = "data_usage_warning";

    public static final String TRIP_SELECT_CARD = "trip_select_card";

    public static final String RESULT_DATA_USAGE_WARNING_ACCEPT = "data_warning_accept";

    public static final String ACCOUNT_MANAGEMENT = "account_management";

    public static final String COMING_SOON = "coming_soon";

    public static final String SCAN_TO_PAY_FLOW_TYPE_PARAM = "scan_to_pay_flow_type";

    public static final String SCAN_TO_PAYMENT = "scan_to_payment";

    public static final String SCAN_PAYMENT_DONE_SCREEN = "scan_payment_done_screen";

    public static final String SCAN_TERM_AND_CONDITION_SCREEN = "scan_term_and_condition_activity";

    public static final String SCAN_TERM_AND_CONDITION_UPDATED_SCREEN = "scan_term_and_condition_updated_activity";

    public static final String SCAN_NOT_ENROLLED_SCREEN = "scan_not_enrolled_screen";

    public static final String NOCARDS_AVAILABLE = "no_cards_available";

    public static final String SCAN_PAY_CARDS_BLOCK = "scan_payment_cards_block";

    public static final String WORKING_HOURS = "working_hour";

    public static final String Travel_CardPocket_Trips_screen = "travel_cardpocket_trips_screen";

    public static final String TRAVEL_CARD_BUY_CURRENCY_INFO_SCREEN = "travel_card_buy_currency_info_screen";

    public static final String Travel_Card_Sell_Info_screen = "travel_card_sell_info_screen";

    public static final String TRAVEL_CARD_VISIT_BRANCH_SCREEN = "travel_card_visit_branch_screen";

    public static final String SCAN_TO_PAY_DETAIL = "scan_to_pay_detail";

    public static final String SCAN_TO_PAY_CAMERA_PERMISSION_PARAM = "scan_to_pay_camera_permission_param";

    public static final String QR_CODE_PARAM = "qr_code_string";

    public static final String MASTERPASS_PURCHASE_CODE_PARAM = "masterpass_purchase_code_string";

    public static final String MASTERPASS_RETURN_URL_PARAM = "masterpass_return_url_string";

    public static final String MASTERPASS_ISFROM_MOBI_PARAM = "masterpass_isfrom_mobi_string";

    public static final String APP_BLOCK = "app_block";

    public static final String SET_DEFAULT_ACCOUNT = "SET_DEFAULT_ACCOUNT";

    public static final String ERROR_MESSAGE_NO_ENTRY = "ERROR_MESSAGE_NO_ENTRY";

    public static final String MAINTENANCE_PAGE = "MAINTENANCE_PAGE";

    public static final String APP_UPDATE_NEEDED = "app_update_needed";

    public static final String APP_UPDATE_AVAILABLE = "app_update_available";

    public static final String APP_ROOTED = "app_rooted";

    public static final String CURRENCY_ACTIVITY = "currency_activity";

    public static final String TRAVEL_CARD_REVIEW_SELL_ACTIVITY = "travel_card_review_sell_activity";

    public static final String FROM_SECOND_LOGIN_SCREEN = "from_second_login_screen";

    public static final String IS_FROM_APP_SHORTCUT = "is_from_app_shortcut";

    public static final String IS_FROM_THIRD_PARTY_APP = "is_from_third_party_app";

    public static final String PARAM_RECEIVER_MOBILE_NUMBER = "param_receiver_mobile_number";

    public static final String PARAM_RECEIVER_DESCRIPTION = "param_receiver_description";

    public static final String PARAM_RECEIVER_ACCOUNT_NUMBER = "param_receiver_account_number";

    public static final String MONEY_REQUEST_SUCCESS = "param_money_request_success";
    public static final String IS_FROM_REPORT        = "is_from_report";

    public static final String INSURANCE_QUOTE_CLAIM_SCREEN = "select_quote_claim_screen";

    public static final String PARAM_PAYMENT_MODEL    = "param_payment_model";
    public static final String MFC_SETTLEMENT_QUOTE   = "mfc_settlement_quote";
    public static final String RECIPIENT_PHONE_NUMBER = "param_recipient_phone_number";
    public static final String PARAM_RECIPIENT_NAME   = "param_recipient_name";
    public static final String VIEW_MONEY_REQUESTS    = "param_view_money_requests";

    public static final String CHAT_SCREEN          = "chat_screen";
    public static final String INITIATE_CHAT_SCREEN = "initiate_chat_screen";
    public static final String PARAM_NAME           = "name";
    public static final String PARAM_EMAIL          = "email";

    public static final String CROSS_BORDER_LANDING           = "cross_border_landing";
    public static final String CROSS_BORDER_ACCOUNT_SELCTION  = "cross_border_account_selection";
    public static final String CREATE_PASSWORD_WARNING_SCREEN = "create_password_warning_screen";
    public static final String KEY_AUTH_REFERENCE             = "auth_reference";
    public static final String KEY_LOGIN_FLOW_TYPE            = "login_flow_type";
    public static final String KEY_USERNAME                   = "key_username";
    public static final String SCAN_PAY_AUTHENTICATION        = "scan_pay_authentication";
    public static final String TERMS_AND_CONDITION_LOGIN      = "terms_and_condition";
    public static final String SELECT_CARD                    = "select_card";

    public static final String PARAM_SETTLEMENT_AMOUNT       = "settlement_amount";
    public static final String PARAM_ACCOUNT_NUMBER          = "account_number";
    public static final String VIEW_MONEY_RESPONSE           = "param_view_money_response";
    public static final String PARAM_RECEIVER_NAME           = "param_receiver_name";
    public static final String BECOME_CLIENT                 = "become_client";
    public static final String FICA_SELECT_INTENT            = "fica_select_intent";
    public static final String FICA_ACCOUNT_BENEFITS         = "fica_account_benefits";
    public static final String FICA_EXISTING_CLIENT_CONTINUE = "fica_existing_client_continue";
    public static final String FICA_AVAILABLE_PRODUCT_SUGGESTION = "fica_available_product_suggestion";

    public static final String PARAM_RECEIVER_ACCOUNT_TYPE = "param_receiver_account_type";
    public static final String PARAM_REQUESTED_AMOUNT      = "param_requested_amount";
    public static final String MONEY_RESPONSE_REVIEW       = "param_money_response_review";

    public static final String MONEY_RESPONSE_SUCCESS           = "param_money_response_success";
    public static final String PARAM_PAYMENT_REQUEST_ID         = "param_payment_request_id";
    public static final String PARAM_NOTIFICATION_MOBILE_NUMBER = "param_notification_mobile_number";
    public static final String PARAM_NOTIFICATION_EMAIL_ADDRESS = "param_notification_email_address";
    public static final String PARAM_REFERENCE_NUMBER = "param_reference_number";

    public static final String FEEDBACK_SUCCESS = "feedback_success";
    public static final String PARAM_QUOTATION = "param_quotation";
    public static final String PARAM_CURRENCIES = "param_currencies";
    public static final String PARAM_CURRENCY = "param_currency";
    public static final String PARAM_CUT_OFF_TIME = "param_cut_off_time";
    public static final String PARAM_EXCHANGE_AMOUNT = "param_exchange_amount";
    public static final String PARAM_YOUR_REF = "param_your_ref";
    public static final String PARAM_FROM_ACCOUNT = "param_from_account";
    public static final String PARAM_TRAVEL_ACCOUNT = "param_travel_account";
    public static final String PARAM_TRAVEL_ACCOUNT_HOLDER_NAME = "param_travel_account_holder_name";
    public static final String PARAM_TRAVEL_TO_ACCOUNT_NAME = "param_travel_to_account_name";
    public static final String PARAM_TRAVEL_ACCOUNT_ID = "param_travel_account_id";
    public static final String PARAM_TRAVEL_SELL_FLOW = "param_travel_sell_flow";
    public static final String PARAM_TRAVEL_ACCOUNT_EMAIL_ID = "param_travel_account_email_id";
    public static final String PARAM_TRIP_INFO = "param_trip_info";

    public static final String PARAM_TOP_UP_SUCCESS_INFO = "param_top_up_success_info";
    public static final String PARAM_ERROR_CODE          = "param_error_code";
    public static final String PARAM_GOVT_PENDING_COUNT  = "param_govt_pending_count";


    public static final String VIEW_BANKER            = "view_banker_details";
    public static final String PARAM_BANKER_RETRY     = "banker_retry";
    public static final String PARAM_BANKER_FIRSTNAME = "banker_first_name";
    public static final String PARAM_BANKER_LASTNAME  = "banker_last_name";
    public static final String PARAM_IS_BABKER        = "is_banker";
    public static final String PARAM_BANKER_EMAIL     = "banker_email";
    public static final String PARAM_BANKER_PHONE     = "banker_phone";
    public static final String PARAM_BANKER_IMAGE     = "banker_image";
    public static final String PARAM_BANKER_DIVISION  = "banker_divison";

    public static final String PARAM_LIFESTYLE_CARD_IMAGE_ASSOCIATION = "lifestyle_card_image";
    public static final String PARAM_LIFESTYLE_CARD_NUMBER            = "lifestyle_card_number";
    public static final String PARAM_LIFESTYLE_CARD_DESCRIPTION       = "lifestyle_card_description";
    public static final String PARAM_LIFESTYLE_CARD_EXPIRY_DATE       = "lifestyle_card_expiry_date";
    public static final String PARAM_LIFESTYLE_CARD_OWNER             = "lifestyle_card_owner";


    public static final String TEL = "tel:";

    public static final String MONEY_REQUEST_DETAIL           = "money_request_detail";
    public static final String PHONE_CONTACT_BENEFICIARY_LIST = "phone_contact_beneficiary_list";
    public static final String SEND_MONEY_REQUESTS            = "param_send_money_requests";

    public static final String ENROLL_V2_LANDING = "enroll_v2_landing";

    public static final String PIN_BIOMETRIC_DECISION   = "pin_biometric_decision";
    public static final String SET_APP_PIN = "set_app_pin";
    public static final String KEY_PASSWORD             = "password";

    //TODO
    public static final String PARAM_IS_SECOND_LOGIN    = "param_is_second_login";
    public static final String YOUR_BANKER              = "YOUR_BANKER";
    public static final String SHOW_FEEDBACK            = "SHOW_FEEDBACK";
    public static final String REPORT_FRAUD             = "REPORT_FRAUD";
    public static final String PARAM_CONTACT_US_FEATURE = "CONTACT US FEATURE";


    public static final String INVONLINE_NOW_ACTIVITY                         = "investment_online_now_activity";
    public static final String TARGET_INVONLINE_PAYOUT_DETAIL                 = "investment_online_payout_detail";
    public static final String TARGET_INVONLINE_MATURING_DEPOSIT              = "investment_online_maturing_detail";
    public static final String TARGET_INVONLINE_RECURRING_DETAILS             = "investment_online_recurring_detail";
    public static final String TARGET_INVONLINE_MANAGE_INVESTMENTS            = "investment_online_manage_investments";
    public static final String TARGET_INVONLINE_DELETE_ENTRY                  = "investment_online_delete_flow_detail";
    public static final String NOW_DETAIL_ACTIVITY                            = "now_detail_activity";
    public static final String NOW_DONE_ACTIVITY                              = "now_done_activity";
    public static final String NOW_CANCEL_ACTIVITY                            = "now_cancel_activity";
    public static final String TARGET_ONIA_FIAS_DISCLAIMER                    = "inv_online_fias_disclaimer_activity";
    public static final String ONIA_PRODUCT_TYPE                              = "InvestmentProductType";
    public static final String TARGET_ONIA_INVESTMENT_ERROR                   = "onia_investment_error";
    public static final String KEY_ONIA_ERROR_TYPE                            = "onia_invalid_client_type_key";
    public static final String KEY_RECIPIENT_SELECTED                         = "key_recipient_selected";
    public static final String KEY_IS_FROM_NOTICE_OF_WITHDRAWAL               = "key_is_from_notice_of_widrawal";
    public static final String KEY_IS_ON_EDIT_SUCCESS_NOTICE                  = "key_is_on_edit_success_notice";
    public static final String KEY_INVESTMENT_ONLINE_IS_DELETE_NOTICE_ENABLED = "key_investment_online_is_delete_enabled";
    public static final String KEY_ITEM_ACCOUNT_ID                            = "key_item_account_id";
    public static final String NOW_DELETE_ACTIVITY                            = "now_delete_activity";
    public static final String PARAM_ONIA_CLIENT_TYPE                         = "client_type_for_open_new_inv_account";
    public static final String PARAM_ONIA_SEC_OFFICER_CD                      = "sec_officer_cd_open_new_inv_account";
    public static final String PARAM_ONIA_CIS_NUMBER                          = "cis_number_open_new_inv_account";
    public static final String PARAM_ONIA_BIRTH_DATE                          = "birth_date_for_open_new_inv_account";
    public static final String POSITON                                        = "position";
    public static final String SCALE                                          = "scale";
    public static final String LOGIN_DATA                                     = "login_data";
    public static final String DISABLE_BANK_CARD                              = "disable_bank_card";
    public static final String POSITION                                       = "position";
    public static final String IS_ENABLED                                     = "is_enabled";
    public static final String PAY_ACCOUNTS                                   = "pay_accounts";
    public static final String IS_FROM_PAY_ME                                 = "is_from_pay_me";
    public static final String TARGET_MATURING_INVESTMENTS                    = "target_maturing_investments";
    public static final String TARGET_MANAGE_INVESTMENT_VIEW                  = "target_manage_my_investments";
    public static final String TARGET_RIGHT_OPTIONS                           = "inv_online_right_options";
    public static final String KEY_IS_FROM_UPDATE_NOTICE_OF_WITHDRAWAL        = "key_is_from_update_notice_of_widrawal";

    public static final String IS_FROM_ADAPTER           = "is_from_adapter";
    public static final String IS_FROM_APO               = "is_from_apo";
    public static final String IS_FROM_INVESTMENT_ONLINE = "is_from_investment_online";

    public static final String PARAM_IS_HL_REQUEST               = "hl_request";
    public static final String KEY_MOBILE_NUMBER                 = "mobile_number";
    public static final String HOME_LOAN_TOOLKIT                 = "home_loan_toolkit";
    public static final String FINANCIAL_PLANNER_HOME            = "financial_planner_home";
    public static final String FINANCIAL_PLANNER_FORM            = "financial_planner_form";
    public static final String PERSONAL_LOAN_HOME                = "personal_loan_home";
    public static final String CHAT_OPTION_SCREEN                = "chat_option_screen";
    public static final String TARGET_LIFESTYLE_SIGNIN           = "lifestyle_signin";
    public static final String TARGET_LIFESTYLE_HEYNED_VIDEO     = "lifestyle_heyned_view";
    public static final String TARGET_LIFESTYLE_CHAT_SETTINGS    = "lifestyle_chat_settings";
    public static final String CARD_ENTRY                        = "card_entry";
    public static final String CLIENT_TYPE_SELECTION             = "client_type_selection";
    public static final String PRE_APPROVED_OFFERS_NOTIFICATIONS = "pre_approved_offers_notifications";

    public static final String TARGET_NOTIFICATION_CENTER           = "notification_center";
    public static final String TARGET_NOTIFICATION_DETAILS          = "notification_details";
    public static final String PARAM_LIFESTYLE_IS_FROM_CHAT_SETTING = "lifestyle_is_from_chat_setting";
    public static final String APPROVE_IT_SCREEN = "approve_it";
    public static final String PROMOTE_NID_V3 = "create_nid_v3";
    public static final String TARGET_NOTIFICATION_MESSAGES = "notification_messages";
    public static final String TARGET_NOTIFICATION_TRANSACTION      = "notification_transaction";
    public static final String TARGET_NOTIFICATION_PREFRENCES       = "notification_prefrences";
    public static final String NID_FORGOT_COUNTRY_SELECTION = "nid_forgot_country_selection";
    public static final String LINKING_IMPLICATION = "linking_implication";

    public static final String TARGET_TRANSACTION_NOTIFICATION_REPORT_FRAUD = "transaction_notification_report_fraud";
    public static final String TARGET_TRANSACTION_NOTIFICATION_REPORT_FROZEN = "transaction_notification_report_frozen";


    public static final String PROFILE_ALREADY_LINKED = "profile_already_linked";
    public static final String SET_DEFAULT_PROFILE    = "set_default_profile";
    public static final String PROFILE_SELECTION      = "profile_selection";

    public static final String KEY_APPROVE_IT_VERIFICATION_ID = "approve_it_verification_id";

    public static final String EDIT_PROFILE_LIMIT     = "edit_profile_limit";
    public static final String EDIT_LIMIT_GP_FLOW     = "edit_limit_gp_flow";
    public static final String PARAM_PROFILE_LIMIT_ID = "profile_limit_id";
    public static final String KEY_APPROVE_IT_TYPE    = "key_approve_it_type";

    public static final String IS_SECOND_LOGIN               = "is_second_login";
    public static final String IS_SKIP_LANDING               = "is_skip_landing";
    public static final String IS_FROM_NFP                   = "is_from_nfp";
    public static final String PROFILE_LOCKED                = "profile_locked";
    public static final String PARAM_LOCKED_PROFILE_USERNAME = "lockedUserName";
    public static final String CHANGE_PASSWORD_SUCCESS = "change_password_success";
    public static final String AGENT_DESK = "agent_desk";
    public static final String ADDITIONAL_INFORMATION = "additional_information";
    public static final String AGENT_CONNECTED_MESSAGE="agent_connected_message";
    public static final String MORE_REPORT_FRAUD = "more_report_fraud";

    public static final String MANUAL_ACCOUNT_LINKING = "manual_account_linking";
    public static final String PARAM_PRE_LOGIN               = "is_pre_login";
    public static final String CMS_MEDIA_CONTENT = "cms_media_content";
    public static final String SHARE_PROFILE_IMPLICATION = "share_profile_implication";
    public static final String CONFIRM_SECONDARY_USER_DETAIL = "confirm_secondary_user_details";
    public static final String ACCESS_SECONDARY_USER = "access_secondary_user";
    public static final String USER_MANAGEMENT = "user_management";


    public interface EXTRAS {
        String BANK_SELECTED   = "bank_selected";
        String BRANCH_SELECTED = "branch_selected";
    }

    public static final String REWARDS_TNC     = "rewards_tnc";
    public static final String REWARDS_LANDING = "rewards_landing";
    private final String target;
    private final Map<String, Object> params = new HashMap<>();
    private Long id;
    private boolean clearStack = false;
    private boolean intentFlagClearTopWithSingleTop;
    private boolean intentFlagClearTop;
    private boolean passAllData;
    private boolean intentFlagNewTask;

    protected NavigationTarget(final String target) {
        this.target = target;
    }

    public static NavigationTarget to(final String target) {
        return new NavigationTarget(target);
    }

    public NavigationTarget withId(final long id) {
        this.id = id;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public NavigationTarget withParam(final String name, final String value) {
        params.put(name, value);
        return this;
    }

    public NavigationTarget withParam(final String name, final boolean value) {
        params.put(name, value);
        return this;
    }

    public NavigationTarget withParam(final String name, final long value) {
        params.put(name, value);
        return this;
    }

    public NavigationTarget withParam(final String name, final int value) {
        params.put(name, value);
        return this;
    }

    public NavigationTarget withParam(final String name, final Parcelable value) {
        params.put(name, value);
        return this;
    }

    public NavigationTarget withParam(final String name, final Serializable value) {
        params.put(name, value);
        return this;
    }

    public NavigationTarget withParam(final String name, final List<? extends Parcelable> value) {
        params.put(name, value);
        return this;
    }

    public NavigationTarget withClearStack(final boolean clearStack) {
        this.clearStack = clearStack;
        return this;
    }

    public NavigationTarget withIntentFlagClearTopSingleTop(final boolean intentFlagClearTopSingleTop) {
        this.intentFlagClearTopWithSingleTop = intentFlagClearTopSingleTop;
        return this;
    }

    public NavigationTarget withIntentFlagClearTop(final boolean intentFlagClearTop) {
        this.intentFlagClearTop = intentFlagClearTop;
        return this;
    }

    public NavigationTarget withIntentFlagNewTask(final boolean intentFlagNewTask) {
        this.intentFlagNewTask = intentFlagNewTask;
        return this;
    }

    public NavigationTarget withAllData(final boolean withAllData) {
        this.passAllData = withAllData;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getStringParam(final String name) {
        return getTypedValue(name, String.class);
    }

    public Long getLongParam(final String name) {
        return getTypedValue(name, Long.class);
    }

    public Integer getIntParam(final String name) {
        return getTypedValue(name, Integer.class);
    }

    public Boolean getBooleanParam(final String name) {
        return getTypedValue(name, Boolean.class);
    }

    public Parcelable getParcelableParam(final String name) {
        return getTypedValue(name, Parcelable.class);
    }

    public Object getSerializableParam(final String name) {
        return getTypedValue(name, Object.class);
    }

    public ParceableList getParceableListParam(final String name) {
        return getTypedValue(name, ParceableList.class);
    }

    public Bundle getParams() {
        Bundle bundle = new Bundle();
        putParams(bundle);
        return bundle;
    }

    boolean shouldClearStack() {
        return clearStack;
    }

    boolean shouldClearTopWithSingleTop() {
        return intentFlagClearTopWithSingleTop;
    }

    boolean shouldClearTop() {
        return intentFlagClearTop;
    }

    boolean shouldNewTask() {
        return intentFlagNewTask;
    }

    boolean shouldPassAllData() {
        return passAllData;
    }

    private void putParams(Bundle bundle) {
        for (String key : params.keySet()) {
            Object obj = params.get(key);
            if (obj instanceof String) {
                bundle.putString(key, (String) obj);
            } else if (obj instanceof Long) {
                bundle.putLong(key, (Long) obj);
            } else if (obj instanceof Integer) {
                bundle.putInt(key, (Integer) obj);
            } else if (obj instanceof ParceableList) {
                //noinspection unchecked
                bundle.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) obj);
            } else if (obj instanceof Parcelable) {
                bundle.putParcelable(key, (Parcelable) obj);
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(key, (Boolean) obj);
            } else if (obj instanceof Serializable) {
                bundle.putSerializable(key, (Serializable) obj);
            }
        }
    }

    private <T> T getTypedValue(String key, Class<T> tClass) {
        Object value = params.get(key);
        if (tClass.isAssignableFrom(value.getClass())) {
            //noinspection unchecked
            return (T) value;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NavigationTarget that = (NavigationTarget) o;
        return target.equals(that.target);
    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }

    private static class ParceableList extends ArrayList<Parcelable> {
    }
}
