package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sendgrid.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailSender {
    public void sendEmail(String email, String subject, String header, String mainText, String subtext) throws IOException {
        String apiKey = System.getenv("SENDGRID_API_KEY");
        String templateId = System.getenv("SENDGRID_TEMPLATE_ID");

        Email from = new Email("fromzeronotificationservice@gmail.com");
        Email to = new Email(email);
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId(templateId); // Reemplaza con tu Template ID

        // Configurar datos dinámicos
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.addDynamicTemplateData("header", header);
        personalization.addDynamicTemplateData("text", mainText);
        personalization.addDynamicTemplateData("text2", subtext);
        personalization.setSubject(subject);
        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey); // Reemplaza con tu API Key
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
