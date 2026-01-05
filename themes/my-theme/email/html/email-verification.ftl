<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${url.resourcesPath}/css/email-verification.css">
</head>
<body style="width:100vw;
             height:100vh;
             margin: 0;
             padding: 0;
             display:grid;
             align-items:center;
             justify-content: center;
             font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
">
    <div style="display: grid;
                width: 400px;
                padding: 5px;
                border-radius: 25px 25px 25px 25px;
                box-shadow: 2px 2px 50px 2px #ADAAA8;
                justify-items: center;
                background-color: #FAFFFA;
                gap: 5px;
    ">
        <img src="${url.resourcesPath}/img/logo.png" style="width: 10em;
                                                            height: 8em;
                                                            mix-blend-mode: darken;"/>
        <h1 style="font-size: 20px;
                   font-weight: bold;
                   color: #111111;">
            Please verify your email ${user.username}
        </h1>
        <h2 style="font-size: 15px;
                    color: #424040;
                    text-align: center;">
            To use Nestify, click the verification button. This helps keep your account secure.
        </h2>
        <a href="${link}" style=" display: inline-block;
                                background: #0057FF;
                                color: white !important;
                                padding: 10px 30px;
                                text-decoration: none;
                                border-radius: 5px;
                                font-weight: bold;
                                margin: 25px 0 25px 0;">
            Verify Email Address
        </a>
        <p style="font-size: 15px;
                    color: #424040;
                    text-align: center;">
            You're receiving this email because you have an account in Nestify.
            If you are not sure why you're receiving this, please contact us by
            replying to this email.
        </p>
        </div>
</body>
</html>

