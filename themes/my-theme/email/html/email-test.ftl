
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f4f4f4;
        line-height: 1.6;
    }
    .email-container {
        max-width: 600px;
        margin: 20px auto;
        background-color: #ffffff;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .header {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: #ffffff;
        padding: 30px 20px;
        text-align: center;
    }
    .header h1 {
        margin: 0;
        font-size: 28px;
        font-weight: 600;
    }
    .content {
        padding: 40px 30px;
        color: #333333;
    }
    .content h2 {
        color: #667eea;
        font-size: 22px;
        margin-top: 0;
    }
    .info-box {
        background-color: #f8f9fa;
        border-left: 4px solid #667eea;
        padding: 15px;
        margin: 20px 0;
        border-radius: 4px;
    }
    .info-box strong {
        color: #667eea;
    }
    .button {
        display: inline-block;
        padding: 14px 30px;
        margin: 20px 0;
        background-color: #667eea;
        color: #ffffff !important;
        text-decoration: none;
        border-radius: 5px;
        font-weight: 600;
        text-align: center;
    }
    .checklist {
        background-color: #e8f5e9;
        border-radius: 6px;
        padding: 20px;
        margin: 20px 0;
    }
    .checklist h3 {
        color: #2e7d32;
        margin-top: 0;
        font-size: 18px;
    }
    .checklist ul {
        margin: 10px 0;
        padding-left: 20px;
    }
    .checklist li {
        margin: 8px 0;
        color: #333;
    }
    .footer {
        background-color: #f8f9fa;
        padding: 20px;
        text-align: center;
        font-size: 13px;
        color: #666666;
        border-top: 1px solid #e0e0e0;
    }
    .footer p {
        margin: 5px 0;
    }
    .link-box {
        background-color: #fff3e0;
        border: 1px dashed #ff9800;
        padding: 15px;
        border-radius: 4px;
        margin: 20px 0;
        word-break: break-all;
    }
    .variable-table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px 0;
    }
    .variable-table th,
    .variable-table td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #e0e0e0;
    }
    .variable-table th {
        background-color: #f8f9fa;
        color: #667eea;
        font-weight: 600;
    }
    .variable-table code {
        background-color: #f5f5f5;
        padding: 2px 6px;
        border-radius: 3px;
        font-family: 'Courier New', monospace;
        color: #d32f2f;
        font-size: 12px;
    }
    </style>
</head>
<body>
    <div class="email-container">
         <img src="https://nestify-backet.s3.us-east-1.amazonaws.com/logo.png"
             style="width: 10em;
                    height: 8em;
                    mix-blend-mode: darken;
        "/>
        <!-- Header -->
        <div class="header">
            <h1>🎉 Keycloak Email Configuration Test</h1>
        </div>

        <!-- Main Content -->
        <div class="content">
            <h2>Congratulations! Your email is working! ✅</h2>

            <p>If you're seeing this email, it means your Keycloak SMTP configuration is correctly set up and emails are being delivered successfully.</p>

            <!-- Info Box -->
            <div class="info-box">
                <strong>Test Details:</strong><br>
                <strong>Realm:</strong> ${realmName}<br>
                <strong>User:</strong> ${user.username}<br>
                <strong>Email:</strong> ${user.email}<br>
                <strong>Date:</strong> ${.now?string('EEEE, MMMM dd, yyyy')} at ${.now?string('HH:mm:ss')}
            </div>

            <!-- Test Button -->
            <#if link??>
            <div style="text-align: center;">
                <a href="${link}" class="button">Test Action Button</a>
            </div>

            <!-- Link Box -->
            <div class="link-box">
                <strong>📎 Test Link:</strong><br>
                ${link}
            </div>
            <#else>
            <div class="info-box" style="border-left-color: #ff9800; background-color: #fff8e1;">
                <strong style="color: #ff9800;">ℹ️ Note:</strong><br>
                This is a simple test email. Action links are only available in specific email types like password reset or email verification.
            </div>
            </#if>

            <!-- Checklist -->
            <div class="checklist">
                <h3>✓ What This Test Confirms:</h3>
                <ul>
                    <li>✅ SMTP server connection is successful</li>
                    <li>✅ Authentication credentials are correct</li>
                    <li>✅ Email templates are rendering properly</li>
                    <li>✅ Variables are being substituted correctly</li>
                    <li>✅ Email styling (HTML/CSS) is working</li>
                    <li>✅ Your theme is properly configured</li>
                </ul>
            </div>

            <!-- Variable Reference -->
            <h3 style="color: #667eea;">Available Template Variables:</h3>
            <table class="variable-table">
                <thead>
                    <tr>
                        <th>Variable</th>
                        <th>Test Value</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><code>${"$"}{realmName}</code></td>
                        <td>${realmName}</td>
                    </tr>
                    <tr>
                        <td><code>${"$"}{user.username}</code></td>
                        <td>${user.username}</td>
                    </tr>
                    <tr>
                        <td><code>${"$"}{user.email}</code></td>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <td><code>${"$"}{user.firstName}</code></td>
                        <td><#if user.firstName??>${user.firstName}<#else>Not set</#if></td>
                    </tr>
                    <tr>
                        <td><code>${"$"}{user.lastName}</code></td>
                        <td><#if user.lastName??>${user.lastName}<#else>Not set</#if></td>
                    </tr>
                    <#if link??>
                    <tr>
                        <td><code>${"$"}{link}</code></td>
                        <td style="word-break: break-all; font-size: 11px;">${link}</td>
                    </tr>
                    </#if>
                </tbody>
            </table>

        <!-- Footer -->
        <div class="footer">
            <p><strong>${realmName}</strong></p>
            <p>This is an automated test email from Nestify</p>
            <p>© ${.now?string('yyyy')} - All rights reserved</p>
            <p style="margin-top: 10px; font-size: 11px;">
                Please do not reply to this email. This mailbox is not monitored.
            </p>
        </div>
    </div>
</body>
</html>
