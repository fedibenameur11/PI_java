/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;



import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsApi {

    // Vos identifiants Twilio
    private static final String ACCOUNT_SID = "AC3dcac380e1fbb675f3d2e176455f1729";
    private static final String AUTH_TOKEN = "0683b355761ace035214de4fef4d7653";

    // Numéro de téléphone Twilio
    private static final String TWILIO_PHONE_NUMBER = "+13157911937";

    public void send(String toPhoneNumber, String message) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message twilioMessage = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(TWILIO_PHONE_NUMBER),
                    message).create();
            System.out.println(twilioMessage.getSid());
        } catch (Exception ex) {
            System.out.println("Error sending SMS: " + ex.getMessage());
        }
    }
}

