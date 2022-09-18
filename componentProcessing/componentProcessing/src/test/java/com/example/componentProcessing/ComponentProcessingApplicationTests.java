package com.example.componentProcessing;

import com.example.componentProcessing.client.PackageAndDeliveryClient;
import com.example.componentProcessing.model.CompleteProcessing;
import com.example.componentProcessing.model.CompleteResponse;
import com.example.componentProcessing.model.ProcessRequest;
import com.example.componentProcessing.model.ProcessResponse;
import com.example.componentProcessing.repository.ProcessResponseRepo;
import com.example.componentProcessing.service.ComponentProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComponentProcessingApplication.class)
class ComponentProcessingApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PackageAndDeliveryClient pdC;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void detailsCheck(){
//		int val = pdC.getPackageAndDeliveryCharge("integral",1);
//		assertNotNull(val);
//	}
	@Test
	public void processDetailTest() {
		ProcessRequest pRequest = new ProcessRequest();
		pRequest.setName("John");
		pRequest.setContactNumber("987654321");
		pRequest.setComponentType("Integral");
		pRequest.setComponentName("Samsung");
		pRequest.setQuantity(1);
		ProcessResponse pRes = new ProcessResponse();
		if(pRequest.getComponentType().equalsIgnoreCase("integral")) {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			LocalDate localDate = LocalDate.now().plusDays(5);
			pRes.setDateOfDelivery(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
			pRes.setProcessingCharge(500);
		}
		if(pRequest.getComponentType().equalsIgnoreCase("accessory")) {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			LocalDate localDate = LocalDate.now().plusDays(2);
			pRes.setDateOfDelivery(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
			pRes.setProcessingCharge(300);
		}
		 assertNotNull(pRes);
	}

	@Test
	public void completeProcessingTest(CompleteProcessing cp, ProcessResponse pr) {
		cp.setRequestID(576);
		cp.setCreditCardNumber("ABCD-1987");
		cp.setCreditLimit(100000);
		cp.setDateOfDelivery("23/12/1997");
		pr.setRequestID(576);
		ComponentProcessingService cps;
		CompleteResponse cr=new CompleteResponse();
		long id=(cp.getRequestID());
		if(pr.getRequestID()==id) {
			cr.setFlag(1);
		} else {
			cr.setFlag(0);
		}

		assertNotNull(cr.getFlag());
	}


}
