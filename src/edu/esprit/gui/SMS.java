package edu.esprit.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SMS {
     // Vos identifiants Twilio
    private static final String ACCOUNT_SID = "AC00b5227f6f1ec9b2920311995e0ea72f";
    private static final String AUTH_TOKEN = "c1482a390fd766a7cefb3f7379302ca5";

    // Numéro de téléphone Twilio
    private static final String TWILIO_PHONE_NUMBER = "+16672253956";

    public void send(String toPhoneNumber, String message) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message twilioMessage = Message.creator(
                    new PhoneNumber("+216"+toPhoneNumber+""),
                    new PhoneNumber(TWILIO_PHONE_NUMBER),
                    message).create();
            System.out.println(twilioMessage.getSid());
        } catch (Exception ex) {
            System.out.println("Error sending SMS: " + ex.getMessage());
        }
    
}
}