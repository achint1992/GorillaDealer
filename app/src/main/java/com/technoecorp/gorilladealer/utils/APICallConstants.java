package com.technoecorp.gorilladealer.utils;

public class APICallConstants {
    public static String verifyOTP = Constants.apiURL + "auth/verifyOtp";
    public static String stateApiRequest = Constants.apiURL + "auth/getStateByCountryId";
    public static String cityApiRequest = Constants.apiURL + "auth/getAllCities";
    public static String countryApiRequest = Constants.apiURL + "auth/getCountryList";
    public static String signInApiRequest = Constants.apiURL + "auth/login";
    public static String signUpApiRequest = Constants.apiURL + "auth/signupDealer";
    public static String editDealerApiRequest = Constants.apiURL + "dealer/upateProfiledetails";
    public static String kycDealerApiRequest = Constants.apiURL + "dealer/createKycInfo";

    public static String dealerDashboard = Constants.apiURL + "dealer/getDealerCountById";
    public static String dealerFilter = Constants.apiURL + "dealer/getFilterDealer";
    public static String getStateCountForDealer = Constants.apiURL + "dealer/getStateWiseCount";
    public static String getCityCountForDealer = Constants.apiURL + "dealer/getCityCountByStateId";
    public static String getAllPackages = Constants.apiURL + "dealer/getAllProductWithPackage";
    public static String createPaymentLink = Constants.apiURL + "dealer/createPaymentRequest";
    public static String galleryRequest = Constants.apiURL + "auth/getRecentMedia";
    public static String withdrawalMoney = Constants.apiURL + "dealer/createWidthralRequest";

    public static String dealerCard = Constants.baseURL + "/dealerCard/1/";
    public static String certificate = Constants.baseURL + "/certificate/";


}
