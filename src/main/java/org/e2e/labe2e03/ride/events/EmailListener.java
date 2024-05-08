package org.e2e.labe2e03.ride.events;

import org.e2e.labe2e03.ride.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    @Async
    public void handleHelloEmailEvent(HelloEmailEvent event) {
        String htmlBody = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Ride Created</title>\n" +
                "    <style>\n" +
                "        /* CSS Reset */\n" +
                "        body, h1, p {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        /* Email Body */\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        /* Email Container */\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0,0,0,0.1);\n" +
                "        }\n" +
                "        /* Heading */\n" +
                "        h1 {\n" +
                "            color: #333;\n" +
                "            text-align: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        /* Content */\n" +
                "        p {\n" +
                "            color: #666;\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.6;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        /* Button */\n" +
                "        .btn {\n" +
                "            display: inline-block;\n" +
                "            background-color: #007bff;\n" +
                "            color: #fff;\n" +
                "            text-decoration: none;\n" +
                "            padding: 10px 20px;\n" +
                "            border-radius: 5px;\n" +
                "            transition: background-color 0.3s ease;\n" +
                "        }\n" +
                "        .btn:hover {\n" +
                "            background-color: #0056b3;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Ride Created</h1>\n" +
                "        <p>Your ride has been successfully created.</p>\n" +
                "        <p>Details:</p>\n" +
                "        <ul>\n" +
                "            <li><strong>Date:</strong> April 28, 2024</li>\n" +
                "            <li><strong>Time:</strong> 10:00 AM</li>\n" +
                "            <li><strong>From:</strong> Your Location</li>\n" +
                "            <li><strong>To:</strong> Destination</li>\n" +
                "        </ul>\n" +
                "        <p>Thank you for using our service!</p>\n" +
                "        <a href=\"#\" class=\"btn\">View Ride</a>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        emailService.sendHtmlMessage(event.getEmail(), "Correo de Prueba", htmlBody);
    }
}