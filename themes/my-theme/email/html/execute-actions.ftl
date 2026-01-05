<#import "template.ftl" as layout>
<@layout.emailLayout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${url.resourcesPath}/css/execute-actions.css" />
</head>
<body style="margin: 0;
    padding: 0;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f4f4f4;
    line-height: 1.6;
    color: #333;" >
<div style="max-width: 600px;
    margin: 20px auto;
    background-color: #ffffff;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
        <!-- Header -->
        <div style=" background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: #ffffff;
            padding: 40px 20px;
            text-align: center;">
            <h1 style=" margin: 0;
                        font-size: 28px;
                        font-weight: 600;">
                ⚡ Action Required
            </h1>
            <p style="margin: 10px 0 0 0;
                        opacity: 0.95;
                        font-size: 16px;">
                Complete the following actions for your account
            </p>
        </div>

        <!-- Main Content -->
        <div style="padding: 40px 30px;">
            <p style="font-size: 18px;
                margin-bottom: 20px;">
                Hello <#if user.firstName??><strong>${user.firstName}</strong><#else><strong>
                ${user.username}</strong></#if>,
            </p>

            <p>
                An administrator of <strong>${realmName}</strong> has
                requested that you perform the following action(s) on your account.
            </p>

            <!-- Alert Box -->
            <div style="background-color: #fff3cd;
                        border-left: 4px solid #ffc107;
                        padding: 15px;
                        margin: 25px 0;
                        border-radius: 4px;">
                <strong style="color: #856404;
                                display: block;
                                margin-bottom: 5px;">
                    Time Sensitive
                </strong>
                This link will expire in
                <strong style="color: #856404;
                        display: block;
                        margin-bottom: 5px;" >
                    ${linkExpirationFormatter(linkExpiration)}
                </strong>.
                Please complete the required actions as soon as possible.
            </div>

            <!-- Required Actions List -->
            <div style="background-color: #f8f9fa;
                    border-radius: 6px;
                    padding: 20px;
                    margin: 25px 0;">
                <h3 style="color: #f5576c;
                        margin-top: 0;
                        font-size: 18px;">
                    📋 Required Actions:
                </h3>
                <ul style="margin: 10px 0;
                            padding-left: 25px;">
                    <#list requiredActions as action>
                        <li style="margin: 10px 0;
                                    color: #333;
                                    font-size: 15px;">
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
            <div style="text-align: center;
                        margin: 35px 0;">
                <a href="${link}" style="display: inline-block;
                                padding: 16px 40px;
                                background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
                                color: #ffffff !important;
                                text-decoration: none;
                                border-radius: 6px;
                                font-weight: 600;
                                font-size: 16px;
                                box-shadow: 0 4px 6px rgba(0,0,0,0.1);">
                    Complete Required Actions
                </a>
            </div>

            <!-- Info Box -->
            <div style=" background-color: #e9ecef;
                        border: 1px dashed #6c757d;
                        padding: 15px;
                        border-radius: 4px;
                        margin: 20px 0;
                        word-break: break-all;
                        font-size: 12px;
                        color: #495057;">
                <strong>ℹ️ What happens next?</strong><br>
                When you click the button above, you'll be directed to a secure page where you can complete all required actions. 
                You must complete all actions in this session for the changes to take effect.
            </div>

            <!-- Link Box (fallback) -->
            <p style="margin-top: 30px; font-size: 14px; color: #666;">
                If the button doesn't work, copy and paste this link into your browser:
            </p>
            <div style="background-color: #e7f3ff;
                    border-left: 4px solid #2196F3;
                    padding: 15px;
                    margin: 25px 0;
                    border-radius: 4px;">
                ${link}
            </div>

            <!-- Admin Info (if available) -->
            <#if requester??>
            <div style="background-color: #f1f3f5;
                    padding: 12px;
                    border-radius: 4px;
                    margin: 15px 0;
                    font-size: 14px;">
                <strong style="color: #1976D2;">Requested by:</strong> ${requester.firstName!""} ${requester.lastName!""}
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
        <div style="background-color: #f8f9fa;
                    padding: 25px 20px;
                    text-align: center;
                    font-size: 13px;
                    color: #666;
                    border-top: 1px solid #e0e0e0;">
            <p style=" margin: 5px 0;"><strong>${realmName}</strong></p>
            <p style=" margin: 5px 0;">This is an automated administrative notification</p>
            <p style=" margin: 5px 0;">© ${.now?string('yyyy')} ${realmName}. All rights reserved.</p>
            <p style="margin-top: 15px; font-size: 11px;">
                Please do not reply to this email. This mailbox is not monitored.
            </p>
        </div>
    </div>
</body>
</html>
</@layout.emailLayout>