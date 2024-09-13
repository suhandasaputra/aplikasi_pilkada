/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bo.parameter;

/**
 *
 * @author herrysuganda
 */
public class ProcessingCode {

    //BackendProcess
    public static String loginbackend = "000001";
    public static String signupowner = "000002";
    public static String getlistcategory = "000003";
    public static String addcategory = "000004";
    public static String changestatusreceive = "000005";
    public static String changestatusavailable = "000006";
    public static String getoptioncategory = "000007";
    public static String additem = "000008";
    public static String getoptionoutlet = "000009";
    public static String detailreport = "000010";
    public static String detailtrx = "000011";
    public static String itemreport = "000012";
    public static String edit_biodata = "000013";
    public static String edit_email = "000014";
    public static String edit_phonenumber = "000015";
    public static String edit_password = "000016";
    public static String add_outlet = "000017";
    public static String getlistoutlet = "000018";
    public static String edit_outlet = "000019";
    public static String getlistuser = "000020";
    public static String changestatususer = "000021";
    public static String adduser = "000022";
    public static String edit_user = "000023";
    public static String delete_user = "000024";

    public static String getlistcustomer = "000025";
    public static String addcustomer = "000026";
    public static String edit_customer = "000027";
    public static String delete_customer = "000028";

    public static String getlistdiscount = "000029";
    public static String adddiscount = "000030";
    public static String edit_discount = "000031";
    public static String delete_discount = "000032";
    public static String get_log = "000033";
    public static String getlistoutletsales = "000034";
    public static String summaryreport = "000035";
    public static String grap = "000036";

    public static String edit_category = "000037";
    public static String delete_category = "000038";
    public static String edit_item = "000039";
    public static String delete_item = "000040";
    public static String getdashboard = "000041";

//    
//    public static String listTransactionBackend = "880012";
//    public static String listAgentBackend = "880013";
//    public static String listAgentLimitBackend = "880014";
//    public static String addAgentBackend = "880015";
//    public static String getAgentByAgentidBackend = "880016";
//    public static String deleteAgentBackend = "880017";
//    public static String updateAgentBackend = "880018";
//    public static String listNotifUserBackend = "880019";
//    public static String getAgentLimitByAgentidBackend = "880020";
//    public static String resetPassAgentBackend = "880021";
//    public static String resetPinAgentBackend = "880022";
//    public static String updateAgentLimit = "880023";
//    public static String listConnectionBackend = "880024";
//    public static String addConnectionBiller = "880025";
//    public static String getConnectionByBillercode = "880026";
//    public static String verifikasi = "880027";
//    public static String listVerifikasi = "880028";
//    public static String updateConnection = "880029";
//    public static String optionbiller = "880030";
//    public static String optionproductbiller = "880031";
//    public static String addProductBiller = "880032";
//    public static String listBillerProduct = "880033";
//    public static String requestOTPDorman = "880034";
//    public static String confirmOTPDorman = "880035";
//    public static String inquiryWithdrawalCode = "880036";
//    public static String approveWithdrawalCode = "880037";
//
//    public static String topupMerchant = "880080";
//    public static String listMerchant = "880038";
//    public static String addMerchant = "880039";
//    public static String updateMerchant = "880040";
//    public static String getMerchantByMerchantId = "880041";
//    public static String loginMerchant = "880042";
//    public static String nLastTransactionMerchant = "880043";
//    public static String listSettlement = "880085";
//
//    public static String listHutangTerbayar = "880086";
//    public static String listPiutang = "880087";
//    public static String listPiutangTerbayar = "880088";
//    public static String bayarHutang = "880089";
//    public static String piutangDibayar = "880090";
//    //BackendProcess
//
//    public static String checkFirsttime = "100000";
//    public static String login = "100001";
//    public static String changePassword = "100002";
//    public static String registration = "100003";
////    public static String requestTin = "100004";
////    public static String listAccountOverbooking = "100005";
////    public static String listAccountOtherBank = "100006";
//    public static String listBankCode = "100007";
//    public static String listVoucher = "100008";
//    public static String listMerchantBiller = "100009";
//    public static String listMerchantPrepaid = "100010";
//    public static String listRedeemItem = "100011";
////    public static String createMBO = "100013";
////    public static String createAgent = "100014";
////    public static String createSuperuser = "100015";
////    public static String billPaymentTest = "100016";
//    public static String receiveMoney = "100017";
//    public static String cbc = "100018";
//    public static String createCustomer = "100019";
//    public static String backendInput = "100020";
//    public static String backendDelete = "100021";
//    public static String backendUpdate = "100022";
//    public static String loginAgent = "100023";
////    public static String loginMBO = "100024";
//    public static String loginCustomer = "100025";
//    public static String branchInput = "100026";
//    public static String branchUpdate = "100027";
//    public static String branchDelete = "100028";
//    public static String inputPin = "100029";
//    public static String changePin = "100030";
//    public static String createCustomerDummy = "100031";
//    public static String requestOTP = "100032";
//    public static String checkSecurityCode = "100033";
//    public static String createPin = "100034";
//
//    public static String inquirySaldo = "300001";
//    public static String inquiryToAccount = "300002";
//    public static String nLastTransaction = "300003";
//    public static String inquiryToAccountNonBank = "300004";
//    public static String inquiryToAccountBank = "300005";
//    public static String detailTransaction = "300006";
//    public static String customerDeposit = "300007";
//    public static String fundTransfer = "300008";
//    public static String agentCommission = "300009";
//    public static String activeOverDraftForAgent = "300010";
//    public static String deactiveOverDraftForAgent = "300011";
//    public static String billPaymentOverDraftInquiry = "300012";
//    public static String nLastPoin = "300013";
//    public static String scanItemDetail = "300014";
//
//    //emoney
//    public static String deposit = "400000";
//    public static String overBooking = "400001";
//    public static String debetTransfer = "400002";
//    public static String withdrawal = "400003";
////    public static String inquiryWithdrawal = "400004";
////    public static String withdrawalAgent = "400005";
////    public static String deposit = "400006";
////    public static String inquiryDeposit = "400007";
////    public static String depositAgent = "400008";
//
//    public static String kurs = "600001";
//    public static String marker = "600002";
//
//    public static String billInquiry = "700001";
//    public static String billPayment = "700002";
//    public static String prepaidReloadInquiry = "700003";
//    public static String prepaidReloadPayment = "700004";
//
//    public static String billInquiryProccode = "380000";
//    public static String billPaymentProccode = "280000";
//    public static String prepaidReload = "290000";
//    public static String redeemPoin = "270000";
//    public static String scanBayar = "260000";
//    public static String buyerProccode = "180000";
//    public static String sellerProccode = "100000";
//    public static String inquiryBuyerProccode = "120000";
//    public static String dpuRegisterProccode = "110000";
//    public static String dpuHistoryProccode = "130000";
//    public static String inquirySeller = "140000";
//
//    public static String billPaymentOverDraftTransaction = "300013";
//    public static String moneySend = "300014";
//    public static String customerDepositOverDraft = "300015";
//    public static String createCustomerUSD = "300016";
//    public static String customerDepositInquiry = "300017";
//
//    public static String noteInput = "100032";
//    public static String noteUpdate = "100033";
//    public static String noteDelete = "100034";
//
//    public static String versionInput = "100035";
//    public static String versionUpdate = "100036";
//    public static String versionDelete = "100037";
//
//    public static String officeInput = "100038";
//    public static String officeUpdate = "100039";
//    public static String officeDelete = "100040";
//    public static String authProccode = "100041";
//    public static String verificationProccode = "100062";
//
//    //update suhanda
//    public static String getProductbillerBybillercodeAndtcbiller = "880044";
//    public static String updateProductBiller = "880045";
//    public static String deleteProductBiller = "880046";
//    public static String listLog = "880047";
//    public static String listCorp = "880048";
//    public static String addCorp = "880049";
//    public static String updateCorp = "880050";
//    public static String deleteCorp = "880051";
//    public static String getCorpByCuidAndPhonenumber = "880052";
//    public static String option_cuid = "880053";
//
//    public static String listKop = "880054";
//    public static String addKop = "880055";
//    public static String updateKop = "880056";
//    public static String deleteKop = "880057";
//    public static String getKopByCuid = "880058";
//
//    public static String listproduk = "880059";
//    public static String addproduk = "880060";
//    public static String updateproduk = "880061";
//    public static String deleteproduk = "880062";
//    public static String getProdukById = "880063";
//
//    public static String listuserproduk = "880064";
//    public static String adduserproduk = "880065";
//    public static String updateuserproduk = "880066";
//    public static String deleteuserproduk = "880067";
//    public static String getAmTrancodeByTrancodeid = "880068";
//    public static String deleteConnection = "880069";
////    public static String listNotifUserBackendadministrator = "880070";
//    public static String listLogselfuser = "880071";
//    public static String optionagent = "880072";
//
//    public static String listAds = "880073";
//    public static String addAds = "880074";
//    public static String updateAds = "880075";
//    public static String deleteAds = "880076";
//    public static String getAdsByAdsid = "880077";
//
//    public static String listVa = "880078";
//
//    public static String listQr = "880079";
//    public static String addQr = "880080";
//    public static String updateQr = "880081";
//    public static String deleteQr = "880082";
//    public static String getQrByMerchantid = "880083";
//
//    public static String getPhotoByAgentid = "880084";
//    public static String rejectVerifikasi = "880091";
//    public static String hystori_trf_corp = "880092";
//    public static String addAgentKopBackend = "880093";
//    public static String ListVersionUser = "880094";
//    public static String ListVersionAgent = "880095";
//    public static String GetVersion = "880096";
//    public static String getUserVersionByVersionCode = "880097";
//    public static String updateVersionUser = "880098";
//    public static String getAgentVersionByVersionCode = "880099";
//    public static String updateVersionAgent = "880100";
//    public static String activeproduct = "880101";
//    public static String deactiveproduct = "880102";
////        public static String optioncuid = "880103"
//
//    //awal;
//    public static String listMitraBackend = "880104";
//    public static String listUserBO = "880105";
//    public static String getupdateUserBO = "880106";
//    public static String executeUpdateUserBO = "880107";
//    public static String addUserBO = "880108";
//    public static String executeDeleteUserBO = "880109";
//    public static String MitraShowMore = "880110";
//    public static String listMerchantMitra = "880111";
//    public static String listTerminal = "880112";
//    public static String logTransaction = "880113";
//    public static String addMitra = "880114";
//    public static String addCabang = "880115";
//    public static String MitraShowMoreWBranch = "880116";
//    public static String addTerminal = "880117";
}
