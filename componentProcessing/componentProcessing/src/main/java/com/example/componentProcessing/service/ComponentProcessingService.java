package com.example.componentProcessing.service;

import com.example.componentProcessing.client.PackageAndDeliveryClient;
import com.example.componentProcessing.model.CompleteProcessing;
import com.example.componentProcessing.model.CompleteResponse;
import com.example.componentProcessing.model.ProcessRequest;
import com.example.componentProcessing.model.ProcessResponse;
import com.example.componentProcessing.repository.ProcessRequestRepo;
import com.example.componentProcessing.repository.ProcessResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Service
public class ComponentProcessingService {

    private final ProcessResponseRepo pResponseRepo;

    private final ProcessRequestRepo pRequestRepo;

    @Autowired
    public ComponentProcessingService(ProcessResponseRepo pResponseRepo, ProcessRequestRepo pRequestRepo) {
        this.pResponseRepo = pResponseRepo;
        this.pRequestRepo = pRequestRepo;
    }

    public Date dateOfDelivery(int days){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now().plusDays(days);
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    public int processingCharge(int value) {
        return value;
    }

    public ProcessResponse processDetail(ProcessRequest pRequest) {

        ProcessResponse pRes = new ProcessResponse();
        Random rnd  = new Random();
        int number = rnd.nextInt(999);
        pRes.setRequestID(Long.valueOf(number));
        if(pRequest.getComponentType().equalsIgnoreCase("integral")) {
            pRes.setDateOfDelivery(dateOfDelivery(5));
            pRes.setProcessingCharge(processingCharge(500));
        }
        if(pRequest.getComponentType().equalsIgnoreCase("accessory")) {
            pRes.setDateOfDelivery(dateOfDelivery(2));
            pRes.setProcessingCharge(processingCharge(300));
        }

        return pRes;
    }
    public CompleteResponse message(CompleteProcessing cp) {
//        String sDate1="31/12/1998";
//        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//        pResponse.setDateOfDelivery(date1);
        CompleteResponse cr= new CompleteResponse();
        cr.setFlag(0);
        if(pResponseRepo.existsById(cp.getRequestID())) {
            cr.setFlag(1);
        }
        return cr;
    }
}
