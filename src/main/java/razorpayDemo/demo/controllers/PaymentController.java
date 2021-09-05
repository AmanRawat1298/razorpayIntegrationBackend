package razorpayDemo.demo.controllers;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

import razorpayDemo.demo.services.PaymentService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping(value="/payment/")
public class PaymentController {
	

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping(value="")
	@ResponseBody
	public String hello()
	{
		this.paymentService.hi();
		return "Hello!!";
	}
	
	@PostMapping("createOrder")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException
	{
		System.out.println(data);
		JSONObject object = this.paymentService.createOrder(data);
		return object.toString();
	}
	
	@PostMapping("paymentComplete")
	@ResponseBody
	public String paymentComplete(@RequestBody Map<String, Object> data)
	{
		//use service and make the status updated to payment successfull when this endpoint is triggered
		System.out.println("Data Received");
		System.out.println(data.get("razorpay_payment_id"));
		System.out.println(data.get("razorpay_order_id"));
		System.out.println(data.get("razorpay_signature"));
		
		JSONObject message = new JSONObject();
		message.put("value", "Payment Status updated from pending to complete");
		return message.toString();
	}
}
