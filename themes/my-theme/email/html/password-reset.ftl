
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${url.resourcesPath}/css/password-reset.css">
</head>
<body style="font-family: Arial, sans-serif;
    line-height: 1.6;
    color: #333;
    margin: 0;
    padding: 0;"
>
<div style="max-width: 600px;
    margin: 0 auto;
    padding: 0;"
>
        <div style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
                padding: 40px 20px;
                text-align: center;
                border-radius: 25px 25px 25px 25px;
        ">
            <h1>🔐 Password Reset Request</h1>
        </div>

        <div style="padding: 40px 30px;
                    background-color: #ffffff;">
            <p>Hello <#if user.firstName??><strong>${user.firstName}</strong>
            <#else><strong>${user.username}</strong></#if>,</p>

            <p>We received a request to reset the password for your <strong>${realmName}</strong>
                account.</p>

            <div style="text-align: center; ">
                <a href="${link}" style="width: 7em;
                                        height: 3em;
                                        border: 1px solid black;
                                        display: inline-block;
                                        background: #0057FF;
                                        color: white !important;
                                        padding: 10px 40px;
                                        text-decoration: none;
                                        border-radius: 5px;
                                        font-weight: bold;
                                        margin: 20px 0;">
                    Reset Your Password
                </a>
            </div>

            <div style=" background: #f8f9fa;
                    border-left: 4px solid #667eea;
                    padding: 15px;
                    margin: 20px 0;">
                <strong>⏰ Important:</strong><br>
                This link will expire in <strong>${linkExpirationFormatter(linkExpiration)}</strong>.<br>
                For security, it can only be used once.
            </div>

            <p>If the button doesn't work, copy and paste this link into your browser:</p>
            <p style="word-break: break-all; color: #667eea; font-size: 12px;">
                ${link}
            </p>

            <p>If you didn't request this password reset, please ignore this email or contact our support team if you have concerns.</p>

            <p>Best regards,<br>
            <strong>The ${realmName} Team</strong></p>
        </div>

        <div style="background-color: #f8f9fa;
                padding: 20px;
                text-align: center;
                font-size: 12px;
                color: #666;">
            <p>&copy; ${.now?string('yyyy')} ${realmName}. All rights reserved.</p>
            <p>This is an automated message. Please do not reply to this email.</p>
        </div>
    </div>
</body>
</html>