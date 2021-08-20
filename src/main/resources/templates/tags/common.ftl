<#macro html title>
    <html>
        <head>
            <title>${title}</title>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">

            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"/>"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"/>"></script>
        </head>

        <body>
            <div class="ui raised container segment">
                <#nested>
            </div>
        </body>
    </html>
</#macro>
