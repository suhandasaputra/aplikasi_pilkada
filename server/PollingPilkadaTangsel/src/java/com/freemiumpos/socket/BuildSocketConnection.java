/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.socket;

import com.freemium.database.DatabaseProcess;
import com.freemium.parameter.RuleNameParameter;
import com.freemium.singleton.InitParameterSingleton;
import com.freemium.singleton.SocketConnectionSingleton;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class BuildSocketConnection {

    private SocketConnectionSingleton sc = SocketConnectionSingleton.getInstance();

    public BuildSocketConnection() {
//        SocketDetail socketDetail;
        DatabaseProcess dp = new DatabaseProcess();
//        dp.setStatusSocketStart(false);
//
//        HashMap hmAbd = new HashMap();
//        List listOfAbdConnection = dp.getSocketConnectionList(RuleNameParamater.abd);
//        for (int i = 0; i < listOfAbdConnection.size(); i++) {
//            socketDetail = (SocketDetail) listOfAbdConnection.get(i);
//            SocketConnectionEntity sce = new SocketConnectionEntity();
//            sce.setIpAddress(socketDetail.getHost());
//            sce.setPort(socketDetail.getPort());
//            sce.setOpenSocket(socketDetail.isStatusOpen());
//            sce.setJenis(RuleNameParamater.abd);
//            sce.setUrutan(socketDetail.getUrutan());
//            sce.setJenismsg(socketDetail.getJenismsg());
//            sce.setType(socketDetail.getTypeapp());
//            sce.setPartner(RuleNameParamater.abd);
//            sce.setTermID(RuleNameParamater.abd + socketDetail.getUrutan());
//            sce.setHeaderMessageType(socketDetail.getHeaderType());
//            sce.setLengthIncl(socketDetail.isLengthIncl());
//            sce.setBankCode(socketDetail.getBankCode());
//            sce.setAutosignon(socketDetail.isAutosignon());
//            sce.setPackageName(socketDetail.getPackageName());
//            if (sce.isOpenSocket()) {
//                sce.setStatusStart(true);
//                sc.getListOfAbdConnection().add(sce.getTermID());
//                hmAbd.put(sce.getTermID(), sce);
//            }
//        }
//        sc.setAbdConnectionMap(hmAbd);
//
//        HashMap hmBank = new HashMap();
//        List listOfBankConnection = dp.getSocketConnectionList(RuleNameParamater.bank);
//        for (int i = 0; i < listOfBankConnection.size(); i++) {
//            socketDetail = (SocketDetail) listOfBankConnection.get(i);
//            SocketConnectionEntity sce = new SocketConnectionEntity();
//            sce.setIpAddress(socketDetail.getHost());
//            sce.setPort(socketDetail.getPort());
//            sce.setOpenSocket(socketDetail.isStatusOpen());
//            sce.setJenis(RuleNameParamater.bank);
//            sce.setUrutan(socketDetail.getUrutan());
//            sce.setJenismsg(socketDetail.getJenismsg());
//            sce.setType(socketDetail.getTypeapp());
//            sce.setPartner(RuleNameParamater.bank);
//            sce.setTermID(socketDetail.getBankCode());
//            sce.setHeaderMessageType(socketDetail.getHeaderType());
//            sce.setLengthIncl(socketDetail.isLengthIncl());
//            sce.setBankCode(socketDetail.getBankCode());
//            sce.setAutosignon(socketDetail.isAutosignon());
//            sce.setPackageName(socketDetail.getPackageName());
//            if (sce.isOpenSocket()) {
//                sce.setStatusStart(true);
//                sc.getListOfBankConnection().add(sce.getTermID());
//                hmBank.put(sce.getTermID(), sce);
//            }
//        }
//        sc.setBankConnectionMap(hmBank);

        sc.setWebConn(dp.getWebConnectionResource(RuleNameParameter.web));
        dp = null;
        InitParameterSingleton.getInstance().setStatusThread(true);
    }

//    public static void setupSocketConnection(SocketConnectionEntity sce) {
//        DatabaseProcess dp = new DatabaseProcess();
//        dp.setStatusSocketStart(true, sce.getJenis(), sce.getUrutan(), sce.getBankCode());
//        sce.setStatusStart(true);
//        dp = null;
//        if (sce.getType().equals(RuleNameParamater.serverType)) {
//            ConnectAsServer cas = new ConnectAsServer();
//            cas.setPartner(sce.getPartner());
//            cas.setTermID(sce.getTermID());
//            cas.setSce(sce);
//            cas.TurnOnConnection();
////            cas.start();
//        } else if (sce.getType().equals(RuleNameParamater.clientType)) {
//            ConnectAsClient cac = new ConnectAsClient();
//            cac.setPartner(sce.getPartner());
//            cac.setTermID(sce.getTermID());
//            cac.setSce(sce);
//            cac.TurnOnConnection();
////            cac.start();
//        }
//    }
}
