package razorpayDemo.demo.services;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {

	
	@Value(value="${keyId}")
	private String keyId;
	
	@Value(value="${keySecret}")
	private String keySecret;
	
	public void hi()
	{
		System.out.println(keyId);
		System.out.println(keySecret);
		System.out.println("Hi from service");
	}

	public JSONObject createOrder(Map<String, Object> data) throws RazorpayException {
		// TODO Auto-generated method stub
		
		RazorpayClient razorpayClient = new RazorpayClient(this.keyId, this.keySecret);
		JSONObject options = new JSONObject();
		
		//it takes amount in paisa so its required to multiply with 100
		Double amount =  Double.parseDouble(data.get("amount").toString())*100;
		options.put("amount",amount);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = razorpayClient.Orders.create(options);
	    //save this order in a repository
		
		
		JSONObject orderData = new JSONObject();
		orderData.put("key", this.keyId);
		orderData.put("amount", order.get("amount").toString());
		orderData.put("id", order.get("id").toString()); //this is the order id 
		
		
		
		return orderData;
	}
	
	
	
}
