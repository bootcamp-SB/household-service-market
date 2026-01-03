
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${url.resourcesPath}/css/email-styles.css">
</head>
<body>
    <div class="email-container">
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
