package CONTROLLERS;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.SmsSubmissionResponseMessage;
import com.vonage.client.sms.messages.TextMessage;

import static Constants.CommonConstants.API_KEY;
import static Constants.CommonConstants.API_SECT;

public class MessageController {
    public static void sendBlackListedSMS(String licensePlate, String phoneNumber) {
        // Initialize the client
        VonageClient client = VonageClient.builder()
                .apiKey(API_KEY)
                .apiSecret(API_SECT)
                .build();

        TextMessage message = new TextMessage(
                "VehicleTrackerSystem",
                     phoneNumber,
                "The Vehicle " + licensePlate + " is BLACK LISTED !!!"
        );

        // Send the SMS
        try {
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
            for (SmsSubmissionResponseMessage resp : response.getMessages()) {
                System.out.println("Status: " + resp.getStatus());
                //System.out.println("Message ID: " + resp.getMessageId());
            }
        } catch (Exception e) {
            System.out.println("Error sending SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

