package com.thesis.medicalapp.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mservice.allinone.models.CaptureMoMoRequest;
import com.mservice.allinone.models.CaptureMoMoResponse;
import com.mservice.shared.exception.MoMoException;
import com.mservice.shared.sharedmodels.AbstractProcess;
import com.thesis.medicalapp.exception.ApiRequestException;
import org.springframework.core.env.Environment;
import com.mservice.shared.sharedmodels.HttpResponse;
import com.mservice.shared.utils.Encoder;
import com.mservice.shared.utils.Execute;
import com.thesis.medicalapp.config.MomoConfig;
import com.thesis.medicalapp.payload.MomoRequest;
import com.thesis.medicalapp.payload.response.ApiResponse;

import com.thesis.medicalapp.payload.response.MomoResponse;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    Execute execute = new Execute();
    @Autowired
    private Environment env;

    @PostMapping("/momo")
    public ResponseEntity<Object> makeMomoPayment(@RequestBody MomoRequest momoRequest) {
        try {
            String requestId = String.valueOf(System.currentTimeMillis());
            String orderId = String.valueOf(System.currentTimeMillis());
            String storeId = env.getProperty("momo.partner_code");
            String partnerCode = env.getProperty("momo.partner_code");
            String partnerName = "Blouse Care";
            String amount = momoRequest.getAmount();
            String orderInfo = momoRequest.getOrderInfo();
            String inputUrl = momoRequest.getIpnUrl();
            String redirectUrl = momoRequest.getRedirectUrl();
            Boolean autoCapture = true;
            String lang = "vi";
            String extraData = momoRequest.getExtraData();
            String requestType = momoRequest.getRequestType();
            String accessKey = env.getProperty("momo.access_key");
            String secretKey = env.getProperty("momo.secret_key");

            String signature = "accessKey="+ accessKey +"&amount=" + amount+"&extraData=" + extraData+"&ipnUrl=" + inputUrl+"&orderId=" + orderId+"&orderInfo=" + orderInfo + "&partnerCode=" + partnerCode +"&redirectUrl=" + redirectUrl+"&requestId=" + requestId+"&requestType=" + requestType;
            String url = "https://test-payment.momo.vn/v2/gateway/api/create";
            String hash = Encoder.signHmacSHA256(signature, secretKey);

            JSONObject request = new JSONObject();
            request.put("requestId", requestId);
            request.put("orderId", orderId);
            request.put("storeId", storeId);
            request.put("partnerCode", partnerCode);
            request.put("partnerName", partnerName);
            request.put("amount", amount);
            request.put("orderInfo", orderInfo);
            request.put("ipnUrl", inputUrl);
            request.put("redirectUrl", redirectUrl);
            if (requestType == "captureWallet")
                request.put("autoCapture", autoCapture);
            request.put("lang", lang);
            request.put("extraData", extraData);
            request.put("signature", hash);
            request.put("requestType", requestType);

            HttpResponse response = this.execute.sendToMoMo(url, request.toString());
            JSONObject myObject = new JSONObject(response.getData());
            MomoResponse momoResponse = new Gson().fromJson(myObject.toString(), MomoResponse.class);
            if (response.getStatus() != 200) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ApiResponse<>(HttpStatus.BAD_REQUEST, "Request denied!")
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(momoResponse)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(HttpStatus.BAD_REQUEST, e.getMessage())
            );
        }
    }
}
