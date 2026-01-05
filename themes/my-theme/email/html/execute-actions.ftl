<#import "template.ftl" as layout>
<@layout.emailLayout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${url.resourcesPath}/css/execute-actions.css" />
</head>
<body>
<div class="email-container">
        <!-- Header -->
        <div class="header">
            <h1>⚡ Action Required</h1>
            <p>Complete the following actions for your account</p>
        </div>

        <!-- Main Content -->
        <div class="content">
            <p class="greeting">
                Hello <#if user.firstName??><strong>${user.firstName}</strong><#else><strong>
                ${user.username}</strong></#if>,
            </p>

            <p>
                An administrator of <strong>${realmName}</strong> has
                requested that you perform the following action(s) on your account.
            </p>

            <!-- Alert Box -->
            <div class="alert-box">
                <strong>⏰ Time Sensitive</strong>
                This link will expire in <strong>${linkExpirationFormatter(linkExpiration)}</strong>. 
                Please complete the required actions as soon as possible.
            </div>

            <!-- Required Actions List -->
            <div class="actions-list">
                <h3>📋 Required Actions:</h3>
                <ul>
                    <#list requiredActions as action>
                        <li>
                            <#if action == "UPDATE_PASSWORD">
                                🔑 <strong>Update Password</strong> - You need to create a new password
                            <#elseif action == "UPDATE_PROFILE">
                                👤 <strong>Update Profile</strong> - Please complete your profile information
                            <#elseif action == "VERIFY_EMAIL">
                                ✉️ <strong>Verify Email</strong> - Confirm your email address
                            <#elseif action == "CONFIGURE_TOTP">
                                🔐 <strong>Configure Two-Factor Authentication</strong> - Set up 2FA for enhanced security
                            <#elseif action == "UPDATE_EMAIL">
                                📧 <strong>Update Email</strong> - Update your email address
                            <#elseif action == "CONFIGURE_RECOVERY_AUTHN_CODES">
                                🔢 <strong>Configure Recovery Codes</strong> - Set up backup authentication codes
                            <#elseif action == "webauthn-register">
                                🔑 <strong>Register Security Key</strong> - Register a WebAuthn security key
                            <#elseif action == "TERMS_AND_CONDITIONS">
                                📄 <strong>Accept Terms and Conditions</strong> - Review and accept our terms
                            <#else>
                                ⚙️ <strong>${action}</strong>
                            </#if>
                        </li>
                    </#list>
                </ul>
            </div>

            <!-- Action Button -->
            <div class="button-container">
                <a href="${link}" class="button">
                    Complete Required Actions
                </a>
            </div>

            <!-- Info Box -->
            <div class="info-box">
                <strong>ℹ️ What happens next?</strong><br>
                When you click the button above, you'll be directed to a secure page where you can complete all required actions. 
                You must complete all actions in this session for the changes to take effect.
            </div>

            <!-- Link Box (fallback) -->
            <p style="margin-top: 30px; font-size: 14px; color: #666;">
                If the button doesn't work, copy and paste this link into your browser:
            </p>
            <div class="link-box">
                ${link}
            </div>

            <!-- Admin Info (if available) -->
            <#if requester??>
            <div class="admin-info">
                <strong>Requested by:</strong> ${requester.firstName!""} ${requester.lastName!""} 
                <#if requester.email??>(${requester.email})</#if>
            </div>
            </#if>

            <!-- Additional Information -->
            <p style="margin-top: 30px;">
                <strong>Important notes:</strong>
            </p>
            <ul style="color: #666; font-size: 14px;">
                <li>This link can only be used once</li>
                <li>The link will expire after ${linkExpirationFormatter(linkExpiration)}</li>
                <li>If you didn't expect this email, please contact your administrator</li>
                <li>For security, do not share this link with anyone</li>
            </ul>

            <p style="margin-top: 30px;">
                If you have any questions or need assistance, please contact your system administrator.
            </p>

            <p style="margin-top: 25px;">
                Best regards,<br>
                <strong>The ${realmName} Team</strong>
            </p>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p><strong>${realmName}</strong></p>
            <p>This is an automated administrative notification</p>
            <p>© ${.now?string('yyyy')} ${realmName}. All rights reserved.</p>
            <p style="margin-top: 15px; font-size: 11px;">
                Please do not reply to this email. This mailbox is not monitored.
            </p>
        </div>
    </div>
</body>
</html>
</@layout.emailLayout>