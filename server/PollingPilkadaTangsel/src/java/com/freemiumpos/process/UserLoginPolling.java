/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freemiumpos.process;

import com.freemium.database.DatabaseProcess;
import com.freemium.function.JsonProcess;
import com.freemium.parameter.FieldParameter;
import com.freemium.parameter.RuleNameParameter;
import java.util.HashMap;
//import javax.json.JsonArray;
import org.apache.log4j.Logger;

/**
 *
 * @author MATAJARI MITRA SOLUSI
 */
public class UserLoginPolling {

    DatabaseProcess dp = new DatabaseProcess();
    private static final Logger log = Logger.getLogger(UserLoginPolling.class);

    public HashMap process(HashMap inputMap) {
        try {
            HashMap result;
            HashMap checkPassword;
            if (dp.CheckPhoneNumberUserPolling(inputMap)) {
                checkPassword = dp.CheckPasswordUserPolling(inputMap);
                if (inputMap.get(FieldParameter.password).equals(checkPassword.get(FieldParameter.password))) {

                    HashMap resultGetSurveyorVotingPLace = dp.GetVotingPlaceSurceyorPolling(inputMap);
                    if (resultGetSurveyorVotingPLace.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                        if (resultGetSurveyorVotingPLace.get(FieldParameter.subdistrict).equals("-") || resultGetSurveyorVotingPLace.get(FieldParameter.voting_place).equals("-")) {
                            inputMap.put(RuleNameParameter.resp_code, "0004");
                            inputMap.put(RuleNameParameter.resp_desc, "Select your votingplace");
                        } else {
                            result = dp.GetUserDetailPolling(inputMap);
                            return result;
                        }
                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0003");
                        inputMap.put(RuleNameParameter.resp_code, "Failed.");
                    }
                    return inputMap;

                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0002");
                    inputMap.put(RuleNameParameter.resp_desc, "Invalid password");
                    return inputMap;
                }

            } else {
                if (dp.CheckNikUserPolling(inputMap)) {
                    checkPassword = dp.CheckPasswordUserPolling(inputMap);
                    if (inputMap.get(FieldParameter.password).equals(checkPassword.get(FieldParameter.password))) {

                        HashMap resultGetSurveyorVotingPLace = dp.GetVotingPlaceSurceyorPolling(inputMap);
                        if (resultGetSurveyorVotingPLace.get(RuleNameParameter.resp_code).equals(RuleNameParameter.respcodeSuccess)) {
                            if (resultGetSurveyorVotingPLace.get(FieldParameter.subdistrict).equals("-") || resultGetSurveyorVotingPLace.get(FieldParameter.voting_place).equals("-")) {
                                inputMap.put(RuleNameParameter.resp_code, "0004");
                                inputMap.put(RuleNameParameter.resp_desc, "Select your votingplace");
                            } else {
                                result = dp.GetUserDetailPolling(inputMap);
                                return result;
                            }
                        } else {
                            inputMap.put(RuleNameParameter.resp_code, "0003");
                            inputMap.put(RuleNameParameter.resp_code, "Failed.");
                        }
                        return inputMap;

                    } else {
                        inputMap.put(RuleNameParameter.resp_code, "0002");
                        inputMap.put(RuleNameParameter.resp_desc, "Invalid password");
                        return inputMap;
                    }
                } else {
                    inputMap.put(RuleNameParameter.resp_code, "0001");
                    inputMap.put(RuleNameParameter.resp_desc, "USER not Exist");

                }

            }

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
            inputMap.put(RuleNameParameter.resp_code, "1007");
            inputMap.put(RuleNameParameter.resp_code, "Failed.");
//            inputMap.put(RuleNameParameter.resp_code, "Message request tidak sesuai");
        }

        return inputMap;
    }
}
