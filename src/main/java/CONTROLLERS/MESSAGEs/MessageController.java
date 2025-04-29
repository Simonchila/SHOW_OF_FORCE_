package CONTROLLERS.MESSAGEs;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.SmsSubmissionResponseMessage;
import com.vonage.client.sms.messages.TextMessage;

import javax.swing.*;
import java.awt.*;

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

        try {
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
            for (SmsSubmissionResponseMessage resp : response.getMessages()) {
                System.out.println("Status: " + resp.getStatus());
            }
        } catch (Exception e) {
            System.out.println("Error sending SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void showToast(JFrame parentFrame, String message, Color bgColor) {
        Toast toast = new Toast(parentFrame, message, bgColor);
        toast.showToast();
    }

}
