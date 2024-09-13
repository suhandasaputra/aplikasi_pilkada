/*
 * @author ADI WIBOWO <PT MATAJARI MITRA SOLUSI>
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.servlet;

import com.freemium.function.impl.MessageProcessImpl;
import com.freemiumpos.process.BillInquiry;
import com.freemiumpos.process.BillPayment;
import com.freemiumpos.process.CreatePin;
import com.freemiumpos.process.InquirySaldo;
import com.freemiumpos.process.Login;
import com.freemiumpos.process.RequestOTP;
import com.freemiumpos.process.NHistoryTransaction;
import com.freemiumpos.process.PrepaidReload;
import com.freemiumpos.process.Verifikasi;
import com.agentmanagement.database.AgentDBProc;
import com.freemium.database.DatabaseProcess;
import com.freemiumpos.process.CheckFirstTime;
import com.freemiumpos.process.CheckSecurityCode;
import com.freemiumpos.process.DetailTransactionByRRN;
import com.freemiumpos.process.ListMyAds;
import com.freemiumpos.process.ListAvailableProduct;
import com.freemiumpos.process.SendDetailTransactionByRRN;
import com.freemiumpos.process.ListMyVirtualAccount;
import com.freemiumpos.process.RequestOTP2;
import com.freemiumpos.process.ResetPin;
//import com.balimuda.process.RecordNIKKYC;
//import com.balimuda.process.RecordPengajuanAnggota;
import com.freemium.function.JsonProcess;
import com.freemium.function.MessageProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.ProcessingCode;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.parameter.StaticParameterCuso;
import com.freemiumpos.process.AddSurveyorPolling;
import com.freemiumpos.process.CancelOrderPOS;
import com.freemiumpos.process.ConfigureTax;
import com.freemiumpos.process.ConfigureTaxDetail;
import com.freemiumpos.process.ConfigureTaxUpdate;
import com.freemiumpos.process.CreateAgent;
import com.freemiumpos.process.CreateCategory;
import com.freemiumpos.process.CreateConditionReportPolling;
import com.freemiumpos.process.CreateCustomerPOS;
import com.freemiumpos.process.CreateDiscountOutlet;
import com.freemiumpos.process.CreateDiscountRules;
import com.freemiumpos.process.CreateEmployeeOutlet;
import com.freemiumpos.process.CreateForcePushDiscountRules;
import com.freemiumpos.process.CreateOutlet;
import com.freemiumpos.process.CreateOwners;
import com.freemiumpos.process.CreatePasswordPolling;
import com.freemiumpos.process.CreateProduct;
import com.freemiumpos.process.CreateReportToEmail;
import com.freemiumpos.process.CreateShoppingVoucher;
import com.freemiumpos.process.CreateTables;
import com.freemiumpos.process.CreateUserPolling;
import com.freemiumpos.process.DeleteCandidate;
import com.freemiumpos.process.DeleteCategory;
import com.freemiumpos.process.DeleteCustomerPOS;
import com.freemiumpos.process.DeleteDiscountRules;
import com.freemiumpos.process.DeleteEmployee;
import com.freemiumpos.process.DeleteProductItem;
import com.freemiumpos.process.DeleteShoppingVoucher;
import com.freemiumpos.process.DeleteTables;
import com.freemiumpos.process.DetailEmployeeOutlet;
import com.freemiumpos.process.DigitalItemPurchase;
import com.freemiumpos.process.GetAllVotePolling;
import com.freemiumpos.process.GetDetailCustomer;
import com.freemiumpos.process.GetDetailOrder;
import com.freemiumpos.process.GetDetailPOStrx;
import com.freemiumpos.process.GetBrandDetail;
import com.freemiumpos.process.GetDetailDiscountRules;
import com.freemiumpos.process.GetDetailOutlet;
import com.freemiumpos.process.GetDetailShoppingVoucher;
import com.freemiumpos.process.GetListCandidatePolling;
import com.freemiumpos.process.GetListConditionReportPolling;
import com.freemiumpos.process.GetListDistrictPolling;
import com.freemiumpos.process.GetListSubDistrictFromVotingPlaceActivePolling;
import com.freemiumpos.process.GetListSubDistrictPolling;
import com.freemiumpos.process.GetListVotingPlaceActivePolling;
import com.freemiumpos.process.GetSummaryOutlet;
import com.freemiumpos.process.GetVoteReportByDistrict;
import com.freemiumpos.process.GetVoteReportBySubDistrict;
import com.freemiumpos.process.GetVoteReportBySubDistrictVotingPlace;
import com.freemiumpos.process.InputCandidate;
import com.freemiumpos.process.InputVotePolling;
import com.freemiumpos.process.ListAllDiscountOutlet;
import com.freemiumpos.process.ListAllOutlet;
import com.freemiumpos.process.ListAllVoucher;
import com.freemiumpos.process.ListDetailLogStock;
import com.freemiumpos.process.ListEmployeeOutlet;
import com.freemiumpos.process.ListLogStock;
import com.freemiumpos.process.ListOrderTransactionPOS;
import com.freemiumpos.process.ListOutletCategory;
import com.freemiumpos.process.ListOutletCustomer;
import com.freemiumpos.process.ListOutletProduct;
import com.freemiumpos.process.ListOutletProductManage;
import com.freemiumpos.process.ListProduct;
import com.freemiumpos.process.ListProviderPrefix;
import com.freemiumpos.process.ListTableAvailable;
import com.freemiumpos.process.ListTransactionPOS;
//import com.freemiumpos.process.PaymentPOS;
import com.freemiumpos.process.PaymentPOSOrder;
import com.freemiumpos.process.PaymentQRIS;
import com.freemiumpos.process.PinChange;
import com.freemiumpos.process.PushEOD;
import com.freemiumpos.process.ResetPinProc;
import com.freemiumpos.process.SaveOrderPOS;
import com.freemiumpos.process.ShowImageProfile;
import com.freemiumpos.process.SubmitKYC;
import com.freemiumpos.process.UpdateCategory;
import com.freemiumpos.process.UpdateEmployee;
import com.freemiumpos.process.UpdateName;
import com.freemiumpos.process.UpdateOrderPOS;
import com.freemiumpos.process.UpdateOutletProfile;
import com.freemiumpos.process.UpdateOutletReceipt;
import com.freemiumpos.process.UpdateProduct;
import com.freemiumpos.process.ReceiveStockProcces;
import com.freemiumpos.process.ReturnStockProcces;
import com.freemiumpos.process.UpdateCandidate;
import com.freemiumpos.process.UpdateClosingOutlet;
import com.freemiumpos.process.UpdateCustomerPOS;
import com.freemiumpos.process.UpdateDiscountRules;
import com.freemiumpos.process.UpdateEmployeePassword;
import com.freemiumpos.process.UpdateOpeningOutlet;
import com.freemiumpos.process.UpdatePushDiscountRules;
import com.freemiumpos.process.UpdateShoppingVoucher;
import com.freemiumpos.process.UpdateSurveyorPolling;
import com.freemiumpos.process.UpdateTables;
import com.freemiumpos.process.UpdateTaxBrand;
import com.freemiumpos.process.UserLoginPolling;
import com.freemiumpos.process.addItemFavorite;
import com.freemiumpos.process.removeFromItemFavorite;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
@WebServlet(name = "ProcessRequest", urlPatterns = {"/ProcessRequest"})
public class ProcessRequest extends HttpServlet {

    private static Logger log = Logger.getLogger(ProcessRequest.class);
    private DatabaseProcess dp = new DatabaseProcess();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MessageProcess mp = new MessageProcessImpl();
        HashMap hmMsg = new HashMap();
        try {
            String reqMsg = request.getParameter("req");

            hmMsg = mp.decryptMessage(reqMsg);
//            System.out.println("PPOB Message incoming : " + reqMsg);
            HashMap hashMapLog = (HashMap) hmMsg.clone();
            hashMapLog.remove(FieldParameter.pin);
            hashMapLog.remove("image");
            hashMapLog.remove(FieldParameter.password);
            hashMapLog.remove(StaticParameterCuso.password_cuso);
            hashMapLog.remove(FieldParameter.img_ktp);
            hashMapLog.remove(FieldParameter.img_profile);
            hashMapLog.remove(FieldParameter.img_self);
            hashMapLog.remove("header_receipt");
            hashMapLog.remove("agent_pass");
            System.out.println("PPOB Message incoming : " + reqMsg);

            log.info("\n" + "PPOB Message incoming : " + JsonProcess.generateJson(hashMapLog) + "\n");

            if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.login)) {
                hmMsg = new CheckSecurityCode().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("01")) {
                hmMsg = new ListOutletCategory().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("02")) {
                hmMsg = new ListOutletProduct().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("03")) {
//                hmMsg = new PaymentPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("04")) {
                hmMsg = new ListTransactionPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("05")) {
                hmMsg = new SaveOrderPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("06")) {
                hmMsg = new ListOrderTransactionPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("07")) {
                hmMsg = new GetDetailOrder().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("08")) {
                hmMsg = new GetDetailPOStrx().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("09")) {
                hmMsg = new UpdateOrderPOS().process(hmMsg);

            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("10")) {
                hmMsg = new PaymentPOSOrder().process(hmMsg);

            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("11")) {
                hmMsg = new CancelOrderPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("12")) {
                hmMsg = new CreateCustomerPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("13")) {
                hmMsg = new ListOutletCustomer().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("14")) {
                hmMsg = new GetDetailCustomer().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("15")) {
                hmMsg = new CreateOwners().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("16")) {
                hmMsg = new CreateCategory().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("17")) {
                hmMsg = new UpdateCategory().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("18")) {
                hmMsg = new CreateProduct().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("19")) {
                hmMsg = new UpdateProduct().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("20")) {
                hmMsg = new ReceiveStockProcces().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("21")) {
                hmMsg = new ListProduct().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("22")) {
                hmMsg = new ListOutletProductManage().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("23")) {
                hmMsg = new UpdateOutletProfile().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("24")) {
                hmMsg = new UpdateOutletReceipt().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("25")) {
                hmMsg = new ResetPinProc().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("26")) {
                hmMsg = new PinChange().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("27")) {
                hmMsg = new ListTableAvailable().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("28")) {
                hmMsg = new CreateTables().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("29")) {
                hmMsg = new UpdateTables().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("30")) {
                hmMsg = new DeleteTables().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("31")) {
                hmMsg = new CreateOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("32")) {
                hmMsg = new CreateEmployeeOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("33")) {
                hmMsg = new ListAllOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("34")) {
                hmMsg = new ListAllDiscountOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("35")) {
                hmMsg = new CreateDiscountRules().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("36")) {
                hmMsg = new addItemFavorite().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("37")) {
                hmMsg = new removeFromItemFavorite().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("38")) {
                hmMsg = new ListEmployeeOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("39")) {
                hmMsg = new UpdateEmployee().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("40")) {
                hmMsg = new DeleteEmployee().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("41")) {
                hmMsg = new DeleteCategory().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("42")) {
                hmMsg = new ReturnStockProcces().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("43")) {
                hmMsg = new ListLogStock().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("44")) {
                hmMsg = new GetBrandDetail().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("45")) {
                hmMsg = new UpdateTaxBrand().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("46")) {
                hmMsg = new GetSummaryOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("47")) {
                hmMsg = new UpdateOpeningOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("48")) {
                hmMsg = new UpdateClosingOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("49")) {
                hmMsg = new UpdateCustomerPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("50")) {
                hmMsg = new DeleteCustomerPOS().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("51")) {
                hmMsg = new ListDetailLogStock().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("52")) {
                hmMsg = new DeleteProductItem().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("53")) {
                hmMsg = new GetDetailDiscountRules().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("54")) {
                hmMsg = new DeleteDiscountRules().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("55")) {
                hmMsg = new CreateForcePushDiscountRules().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("56")) {
                hmMsg = new UpdateDiscountRules().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("57")) {
                hmMsg = new UpdatePushDiscountRules().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("58")) {
                hmMsg = new CreateShoppingVoucher().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("59")) {
                hmMsg = new GetDetailShoppingVoucher().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("60")) {
                hmMsg = new ListAllVoucher().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("61")) {
                hmMsg = new DeleteShoppingVoucher().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("62")) {
                hmMsg = new UpdateShoppingVoucher().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("63")) {
                hmMsg = new ConfigureTax().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("64")) {
                hmMsg = new ConfigureTaxDetail().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("65")) {
                hmMsg = new ConfigureTaxUpdate().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("66")) {
                hmMsg = new UpdateEmployeePassword().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("67")) {
                hmMsg = new DetailEmployeeOutlet().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("68")) {
                hmMsg = new GetDetailOutlet().process(hmMsg);
                //

                //ppob
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("9994")) {
                hmMsg = new NHistoryTransaction().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("9995")) {
                hmMsg = new DetailTransactionByRRN().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("9996")) {
                hmMsg = new DigitalItemPurchase().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("9997")) {
                hmMsg = new ListProviderPrefix().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("9998")) {
                hmMsg = new CreateReportToEmail().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("9999")) {
                hmMsg = new PaymentQRIS().process(hmMsg);
            } //POLLING
            //polling pilkada
            else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingCreateUser)) {
                hmMsg = new CreateUserPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingAddSurveyor)) {
                hmMsg = new AddSurveyorPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingListDistrict)) {
                hmMsg = new GetListDistrictPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingListSubDistrict)) {
                hmMsg = new GetListSubDistrictPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingListCandidate)) {
                hmMsg = new GetListCandidatePolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingListSubDistrictFromVotingPlaceActive)) {
                hmMsg = new GetListSubDistrictFromVotingPlaceActivePolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingListVotingPlaceActive)) {
                hmMsg = new GetListVotingPlaceActivePolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingInputVote)) {
                hmMsg = new InputVotePolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingUserLogin)) {
                hmMsg = new UserLoginPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingCreatePassword)) {
                hmMsg = new CreatePasswordPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingGetAllVote)) {
                hmMsg = new GetAllVotePolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingGetVoteReportByDistrict)) {
                hmMsg = new GetVoteReportByDistrict().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingGetVoteReportBySubDistrict)) {
                hmMsg = new GetVoteReportBySubDistrict().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingGetVoteReportBySubDistrictVotingPlace)) {
                hmMsg = new GetVoteReportBySubDistrictVotingPlace().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingInputCandidate)) {
                hmMsg = new InputCandidate().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingUpdateCandidate)) {
                hmMsg = new UpdateCandidate().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingDeleteCandidate)) {
                hmMsg = new DeleteCandidate().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingUpdateSurveyor)) {
                hmMsg = new UpdateSurveyorPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingCreateConditionReport)) {
                hmMsg = new CreateConditionReportPolling().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pollingGetListConditionReport)) {
                hmMsg = new GetListConditionReportPolling().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.createPin)) {
//                hmMsg = new CreatePin().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.resetPin)) {
//                hmMsg = new ResetPin().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.sendDetailTransaction)) {
//                hmMsg = new SendDetailTransactionByRRN().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.listAds)) {
//                hmMsg = new ListMyAds().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.verificationProccode)) {
//                hmMsg = new Verifikasi().process(hmMsg);
//
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.listMyVirtualAccount)) {
                hmMsg = new ListMyVirtualAccount().process(hmMsg);

            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.listAvailableProduct)) {
                hmMsg = new ListAvailableProduct().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.inquirySaldo)) {
                hmMsg = new InquirySaldo().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.billInquiryProccode)) {
                hmMsg = new BillInquiry().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pccreateAgent)) {
//                hmMsg = new CreateAgent().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.updateName)) {
//                hmMsg = new UpdateName().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.pinChange)) {
//                hmMsg = new PinChange().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.showImgprofile)) {//request img_profile
//                hmMsg = new ShowImageProfile().process(hmMsg);
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.resetPinProc)) {
//                hmMsg = new ResetPinProc().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals("896311")) {
                hmMsg = new PushEOD().process(hmMsg);
//                
//                      //process dengan PIN
//                
//                
            } else if (hmMsg.get(FieldParameter.pin) == null || "01".equals(dp.agentCheckPin(hmMsg.get(FieldParameter.refer).toString(), hmMsg.get(FieldParameter.pin).toString()))) {
                hmMsg.put(RuleNameParameter.resp_code, RuleNameParameter.respcodePinFailed);
                hmMsg.put(RuleNameParameter.resp_desc, "Salah pin");
            } else if ("02".equals(dp.agentCheckPin(hmMsg.get(FieldParameter.refer).toString(), hmMsg.get(FieldParameter.pin).toString()))) {
                hmMsg.put(RuleNameParameter.resp_code, RuleNameParameter.respcodePinFailed);
                hmMsg.put(RuleNameParameter.resp_desc, "Salah pin terlalu sering, sementara account tidak bisa transaksi harap hubungi call center");
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.submitKYC)) {
//                hmMsg = new SubmitKYC().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.billPaymentProccode)) {
                hmMsg = new BillPayment().process(hmMsg);
            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.prepaidReload)) {
                hmMsg = new PrepaidReload().process(hmMsg);
            }

            hmMsg.remove(FieldParameter.password);
            hmMsg.remove(FieldParameter.pin);
            hmMsg.remove("agent_pass");
            hmMsg.remove("image");
            hmMsg.remove(FieldParameter.img_ktp);
            hmMsg.remove(FieldParameter.img_profile);
            hmMsg.remove(FieldParameter.img_self);
            hmMsg.remove("img_report");
//            hmMsg.remove(FieldParameter.header_receipt);
//            hmMsg.remove(FieldParameter.img_self);

            hashMapLog = (HashMap) hmMsg.clone();
            hashMapLog.remove("SecretCode");
            hashMapLog.remove(FieldParameter.header_receipt);

            log.info("\n" + "PPOB Message outgoing : " + JsonProcess.generateJson(hashMapLog) + "\n");
            System.out.println("PPOB Message outgoing : " + JsonProcess.generateJson(hashMapLog) + "\n");

            response.getOutputStream().write(mp.encryptMessage(hmMsg).getBytes());
            response.getOutputStream().flush();
//            if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.billPaymentProccode) && hmMsg.get(FieldParameter.resp_code).toString().equals("0000")) {
//                log.info("try to create notif ");
//                SendMailNotification sendmail = new SendMailNotification();
//                sendmail.sendNotifikasi(hmMsg.get(FieldParameter.userlogin).toString());
//            } else if (hmMsg.get(FieldParameter.procCode).toString().equals(ProcessingCode.prepaidReload) && hmMsg.get(FieldParameter.resp_code).toString().equals("0000")) {
//                log.info("try to create notif ");
//                SendMailNotification sendmail = new SendMailNotification();
//                sendmail.sendNotifikasi(hmMsg.get(FieldParameter.userlogin).toString());
//            }

        } catch (Exception e) {
            e.printStackTrace();
            hmMsg.put(RuleNameParameter.resp_code, RuleNameParameter.respcodeLoginFailed);
            hmMsg.put(RuleNameParameter.resp_desc, "Uknown Error");
            hmMsg.remove(FieldParameter.password);
            hmMsg.remove(FieldParameter.pin);
            hmMsg.remove("img_report");
            hmMsg.remove("agent_pass");

            HashMap hashMapLog = hmMsg;
            hashMapLog.remove(FieldParameter.password);
            hashMapLog.remove(FieldParameter.pin);
            hashMapLog.remove(FieldParameter.img_ktp);
            hashMapLog.remove(FieldParameter.img_profile);
            hashMapLog.remove(FieldParameter.img_self);
            hashMapLog.remove(FieldParameter.data);
            hashMapLog.remove("agent_pass");
            hmMsg.remove("img_report");

            log.info("\n" + "PPOB Message outgoing : " + JsonProcess.generateJson(hashMapLog) + "\n");
            response.getOutputStream().write(mp.encryptMessage(hmMsg).getBytes());
            response.getOutputStream().flush();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
