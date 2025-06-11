package com.fromzero.notificationservice.notifications.infrastructure.email;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sendgrid.*;
import java.io.IOException;


public class EmailSender {
    public void sendEmail(Email email) throws IOException {
        String apiKey = System.getenv("SENDGRID_API_KEY");
        String templateId = System.getenv("SENDGRID_TEMPLATE_ID");

        Email from = new Email("fromzeronotificationservice@gmail.com");
        Email to = new Email("jairvelazpizar@gmail.com");
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId(templateId); // Reemplaza con tu Template ID

        // Configurar datos dinámicos
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.addDynamicTemplateData("header", "Hola Papu");
        personalization.addDynamicTemplateData("text", "Apreta el boton para baile sensual");
        personalization.addDynamicTemplateData("text2", "Osi q rico");
        personalization.setSubject("Ataque Balatrero");
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
