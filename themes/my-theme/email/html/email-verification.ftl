<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${url.resourcesPath}/css/email-verification.css">
</head>
<body>
    <div class="mail-body">
        <img src="${url.resourcesPath}/img/logo.png" class="logo"/>
        <h1 class="header"> Please verify your email ${user.username} </h1>
        <h2 class="sub-header">To use Nestify, click the verification button. This helps keep your account secure.</h2>
        <a href="${link}" class="button">Verify Email Address</a>
        <p class="sub-header">
            You're receiving this email because you have an account in Nestify.
            If you are not sure why you're receiving this, please contact us by
            replying to this email.
        </p>
        </div>
</body>
</html>

